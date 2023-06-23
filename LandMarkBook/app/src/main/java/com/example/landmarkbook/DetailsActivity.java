package com.example.landmarkbook;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.landmarkbook.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        //Intent intent = getIntent();
        //landMark selectedLandmark = (landMark) intent.getSerializableExtra("landmark");

        Singleton singleton = Singleton.getSingleton();
        landMark selectedLandmark = singleton.getSentLendMark();

        binding.name.setText(selectedLandmark.name);
        binding.country.setText(selectedLandmark.country);
        binding.imageView.setImageResource(selectedLandmark.image);





    }
}