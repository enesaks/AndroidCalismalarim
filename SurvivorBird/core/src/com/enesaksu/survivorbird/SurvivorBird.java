package com.enesaksu.survivorbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

import java.util.Random;

public class SurvivorBird extends ApplicationAdapter {

	SpriteBatch batch;
	Texture background;
	Texture bird;
	Texture bee1;
	Texture bee2;
	Texture bee3;
	float birdx = 0, birdy = 0;
	int gameState = 0;
	float velocity = 0;
	float enemyValocity = 4;
	float gravity = 0.2f;
	Random random;
	int score = 0;
	int scoreEnemy = 0;

	Circle birdCircle;
    ShapeRenderer shapeRenderer;
	BitmapFont fontScore;
	BitmapFont fontGameOver;

	int numberOfenemies = 4;
	float [] enemyX = new float[numberOfenemies];
	float [] enemyOffSet = new float[numberOfenemies];
	float [] enemyOffSet2 = new float[numberOfenemies];
	float [] enemyOffSet3 = new float[numberOfenemies];
	float distance = 0;

	Circle[] enemyCircles;
	Circle[] enemyCircles2;
	Circle[] enemyCircles3;



	
	@Override
	public void create () {//OnCreatin aynısı başlayınca nolucapını yazıyoruz.
		batch = new SpriteBatch();
		background = new Texture("background.png");
		bird = new Texture("bird.png");
		bee1 = new Texture("bee.png");
		bee2 = new Texture("bee.png");
		bee3 = new Texture("bee.png");

		distance = Gdx.graphics.getWidth()/2;
		random = new Random();

		birdx = Gdx.graphics.getWidth()/5;
		birdy = Gdx.graphics.getHeight()/2;

		shapeRenderer = new ShapeRenderer();

		birdCircle = new Circle();
		enemyCircles = new Circle[numberOfenemies];
		enemyCircles2 = new Circle[numberOfenemies];
		enemyCircles3 = new Circle[numberOfenemies];

		fontScore = new BitmapFont();
		fontScore.setColor(Color.GRAY);
		fontScore.getData().setScale(5);

		fontGameOver = new BitmapFont();
		fontGameOver.setColor(Color.GRAY);
		fontGameOver.getData().setScale(7);

		for (int i = 0; i<numberOfenemies; i++){
			enemyOffSet[i] = (random.nextFloat()- 0.5f) * (Gdx.graphics.getHeight()- 200);
			enemyOffSet2[i] = (random.nextFloat()- 0.5f) * (Gdx.graphics.getHeight()- 200);
			enemyOffSet3[i] = (random.nextFloat()- 0.5f) * (Gdx.graphics.getHeight()- 200);


			enemyX[i] = Gdx.graphics.getWidth() - bee1.getWidth() / 2 + i * distance ;

			enemyCircles[i] = new Circle();
			enemyCircles2[i] = new Circle();
			enemyCircles3[i] = new Circle();
		}


	}

	@Override
	public void render () {//Oyun devam ederken yapılıcak şeyler.
		batch.begin();
		batch.draw(background,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		if (gameState == 1)
		{

			if (enemyX[scoreEnemy] < birdx){
				score ++;
				if (scoreEnemy < numberOfenemies - 1){
					scoreEnemy++;
					enemyValocity += 0.15;
				}else {
					scoreEnemy = 0;
				}
			}

			if (Gdx.input.justTouched()){
				velocity = -7;
			}

			for (int i = 0; i < numberOfenemies; i++){

				if (enemyX[i] < -Gdx.graphics.getWidth()/18) {
					enemyX[i] = enemyX[i] + numberOfenemies * distance;

					enemyOffSet[i] = (random.nextFloat()- 0.5f) * (Gdx.graphics.getHeight()- 200);
					enemyOffSet2[i] = (random.nextFloat()- 0.5f) * (Gdx.graphics.getHeight()- 200);
					enemyOffSet3[i] = (random.nextFloat()- 0.5f) * (Gdx.graphics.getHeight()- 200);
				}else {
					enemyX[i] = enemyX[i] - enemyValocity;
				}

				batch.draw(bee1,enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffSet[i],Gdx.graphics.getWidth()/18,Gdx.graphics.getHeight()/10);
				batch.draw(bee2,enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffSet2[i],Gdx.graphics.getWidth()/18,Gdx.graphics.getHeight()/10);
				batch.draw(bee3,enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffSet3[i],Gdx.graphics.getWidth()/18,Gdx.graphics.getHeight()/10);

				enemyCircles[i] = new Circle(enemyX[i] +Gdx.graphics.getWidth()/36, Gdx.graphics.getHeight()/2 + enemyOffSet[i] + Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/36);
				enemyCircles2[i] = new Circle(enemyX[i] +Gdx.graphics.getWidth()/36, Gdx.graphics.getHeight()/2 + enemyOffSet2[i] + Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/36);
				enemyCircles3[i] = new Circle(enemyX[i] +Gdx.graphics.getWidth()/36, Gdx.graphics.getHeight()/2 + enemyOffSet3[i] + Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/36);

			}


			if (birdy > 0 ) {
				velocity = velocity + gravity;
				birdy = birdy - velocity;// Yerçekimi.
			}else{
				gameState = 2;
			}

		}
		else if(gameState == 0)
		{
			if (Gdx.input.justTouched()){
				gameState = 1;
			}
		}
		else if(gameState == 2)
		{
			fontGameOver.draw(batch,"Game Over! Tap To PLay Again  Score: "+score,Gdx.graphics.getWidth()/8,Gdx.graphics.getHeight()/2);

			if (Gdx.input.justTouched()){
				gameState = 1;
				birdy = Gdx.graphics.getHeight()/2;

				for (int i = 0; i<numberOfenemies; i++){
					enemyOffSet[i] = (random.nextFloat()- 0.5f) * (Gdx.graphics.getHeight()- 200);
					enemyOffSet2[i] = (random.nextFloat()- 0.5f) * (Gdx.graphics.getHeight()- 200);
					enemyOffSet3[i] = (random.nextFloat()- 0.5f) * (Gdx.graphics.getHeight()- 200);


					enemyX[i] = Gdx.graphics.getWidth() - bee1.getWidth() / 2 + i * distance ;

					enemyCircles[i] = new Circle();
					enemyCircles2[i] = new Circle();
					enemyCircles3[i] = new Circle();
				}

				velocity = 0;
				score = 0;
				scoreEnemy = 0;

			}


		}



		batch.draw(bird,birdx,birdy,Gdx.graphics.getWidth()/18,Gdx.graphics.getHeight()/10);

		fontScore.draw(batch,"Score :"+score,100,200);

		batch.end();

		birdCircle.set(birdx+Gdx.graphics.getWidth()/36,birdy+ Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth() /36);
		//shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		//shapeRenderer.setColor(Color.BLACK);
		//shapeRenderer.circle(birdCircle.x,birdCircle.y,birdCircle.radius);


		for (int i = 0; i< numberOfenemies; i++){
			//shapeRenderer.circle(enemyX[i] +Gdx.graphics.getWidth()/36, Gdx.graphics.getHeight()/2 + enemyOffSet[i] + Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/36);
			//shapeRenderer.circle(enemyX[i] +Gdx.graphics.getWidth()/36, Gdx.graphics.getHeight()/2 + enemyOffSet2[i] + Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/36);
			//shapeRenderer.circle(enemyX[i] +Gdx.graphics.getWidth()/36, Gdx.graphics.getHeight()/2 + enemyOffSet3[i] + Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/36);

			if (Intersector.overlaps(birdCircle,enemyCircles[i]) || Intersector.overlaps(birdCircle,enemyCircles2[i]) || Intersector.overlaps(birdCircle,enemyCircles3[i])){
				gameState = 2;
			}
		}
		//shapeRenderer.end();

	}
	
	@Override
	public void dispose () {

	}
}
