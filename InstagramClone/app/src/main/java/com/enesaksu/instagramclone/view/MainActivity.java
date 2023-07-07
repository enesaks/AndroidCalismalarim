    package com.enesaksu.instagramclone.view;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Toast;

    import com.enesaksu.instagramclone.databinding.ActivityMainBinding;
    import com.google.android.gms.tasks.OnCompleteListener;
    import com.google.android.gms.tasks.OnFailureListener;
    import com.google.android.gms.tasks.OnSuccessListener;
    import com.google.android.gms.tasks.Task;
    import com.google.firebase.auth.AuthResult;
    import com.google.firebase.auth.FirebaseAuth;
    import com.google.firebase.auth.FirebaseUser;

    public class MainActivity extends AppCompatActivity {

        private ActivityMainBinding binding;

        private FirebaseAuth mAuth;
        private String email,passaword;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityMainBinding.inflate(getLayoutInflater());
            View view = binding.getRoot();
            setContentView(view);

            mAuth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = mAuth.getCurrentUser();

            if(currentUser != null){
                currentUser.reload();
                Intent intent = new Intent(MainActivity.this,FeedActivity.class);
                startActivity(intent);
                finish();
            }



        }

        public void signin(View view){
            email = binding.edtEmail.getText().toString();
            passaword = binding.edtPassaword.getText().toString();

            mAuth.signInWithEmailAndPassword(email,passaword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Intent intent = new Intent(MainActivity.this,FeedActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });




        }
        public void signup(View view){

            email = binding.edtEmail.getText().toString();
            passaword = binding.edtPassaword.getText().toString();


            mAuth.createUserWithEmailAndPassword(email,passaword).addOnSuccessListener(this,new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(MainActivity.this,FeedActivity.class);
                    startActivity(intent);
                    finish();

                }
            }).addOnFailureListener(this,new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });

        }
    }