package com.example.landmarkbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.landmarkbook.databinding.ActivityDetailsBinding;
import com.example.landmarkbook.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;// Sürekli findviewbyid diyerek uygulamayı yormamak için kulanılan bir jetpack eklentisi.
    ArrayList<landMark> landMarkArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        landMarkArrayList = new ArrayList<>();
        //DATA
        landMark pisa = new landMark("Pisa","Italy",R.drawable.pisatower);
        landMark eiffel = new landMark("Eiggel","France",R.drawable.eyfeltower);
        landMark colesseum = new landMark("Colesseum","Italy",R.drawable.colosseom);

        landMarkArrayList.add(pisa);
        landMarkArrayList.add(eiffel);
        landMarkArrayList.add(colesseum);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        landmarkAdapter landmarkAdapter = new landmarkAdapter(landMarkArrayList);
        binding.recyclerView.setAdapter(landmarkAdapter);

        /*
        //Adaptar
        //Listview
        //Maping
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                landMarkArrayList.stream().map(landMark -> landMark.name).collect(Collectors.toList())
        );
        binding.listView.setAdapter(arrayAdapter);
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                intent.putExtra("landmark",landMarkArrayList.get(i));
                startActivity(intent);
            }
        });
        */

    }
}