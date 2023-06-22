package com.example.keniyiyakalamaoyunu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView skor,time;
    ImageView kenny;
    int skorInt = 0;
    Random random = new Random();
    int x,y;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);


        skor=findViewById(R.id.tvSkor);
        time=findViewById(R.id.tvTime);
        kenny = findViewById(R.id.imageView);


        new CountDownTimer(15000,500){

            @Override
            public void onTick(long l) {
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) kenny.getLayoutParams();
                layoutParams.leftMargin = x; // Yeni sol kenar boşluğu
                layoutParams.topMargin = y; // Yeni üst kenar boşluğu
                kenny.setLayoutParams(layoutParams);

                x = random.nextInt(2001)-1000;
                y = random.nextInt(2001)-1000;

            }

            @Override
            public void onFinish() {

            }
        }.start();

        new CountDownTimer(15000,1000){

            @Override
            public void onTick(long l) {
                time.setText("Time : "+l/1000);

            }

            @Override
            public void onFinish() {

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Zaman Bitti "+skor.getText().toString());
                alert.setMessage("Tekrar Oynamak Ister misiniz?");
                alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivities(new Intent[]{intent});
                    }
                });
                alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                AlertDialog alertDialog = alert.create();
                alertDialog.show();


            }
        }.start();




    }

    public void kennyClick(View view) {
        skorInt++;
        skor.setText("Skor : "+skorInt);
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) kenny.getLayoutParams();
        layoutParams.leftMargin = x; // Yeni sol kenar boşluğu
        layoutParams.topMargin = y; // Yeni üst kenar boşluğu
        kenny.setLayoutParams(layoutParams);

        x = random.nextInt(2001)-1000;
        y = random.nextInt(2001)-1000;

    }
}