package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Profil extends AppCompatActivity {

    private FirebaseAuth user;

    private String userID ;
    private Button logout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        logout=(Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Profil.this,MainActivity.class));
            }
        });



        userID=user.getUid();

        final TextView fullNameTextView =(TextView)findViewById(R.id.fullName);
        final TextView emailTextView =(TextView)findViewById(R.id.fullName);
        final TextView ageTextView =(TextView)findViewById(R.id.age);



    }
}