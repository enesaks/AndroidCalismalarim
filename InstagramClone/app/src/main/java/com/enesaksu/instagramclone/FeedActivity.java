package com.enesaksu.instagramclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class FeedActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.addPost){
            Intent uploadIntent = new Intent(FeedActivity.this,UploadActivity.class);
            startActivity(uploadIntent);

        } else if (item.getItemId() == R.id.exit) {

            mAuth.signOut();

            Intent exitIntent = new Intent(FeedActivity.this,MainActivity.class);
            finish();
            startActivity(exitIntent);

        }


        return super.onOptionsItemSelected(item);
    }
}