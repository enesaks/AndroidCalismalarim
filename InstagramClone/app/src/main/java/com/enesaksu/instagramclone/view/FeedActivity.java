package com.enesaksu.instagramclone.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.enesaksu.instagramclone.R;
import com.enesaksu.instagramclone.adapter.PostAdaptor;
import com.enesaksu.instagramclone.databinding.ActivityFeedBinding;
import com.enesaksu.instagramclone.model.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class FeedActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    private ActivityFeedBinding binding;

    ArrayList<Post> postArrayList;
    PostAdaptor postAdaptor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeedBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        getData();
        postArrayList = new ArrayList<>();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        postAdaptor = new PostAdaptor(postArrayList);
        binding.recyclerView.setAdapter(postAdaptor);

    }

    public void getData(){

        firebaseFirestore.collection("Posts").orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null){
                    Toast.makeText(FeedActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }

                if (value != null) {
                    for (DocumentSnapshot snapshot : value.getDocuments()){
                        Map<String,Object> data = snapshot.getData();

                        String userEmail = (String) data.get("useremail");
                        String comment = (String)  data.get("comment");
                        String dowloandUrl = (String) data.get("downloadurl");

                        Post post = new Post(userEmail,comment,dowloandUrl);
                        postArrayList.add(post);
                    }
                    postAdaptor.notifyDataSetChanged();

                }

            }
        });

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