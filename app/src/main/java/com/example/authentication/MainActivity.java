package com.example.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView signUP;
    private TextView forgetPassword;
    private EditText editTextEmail ,editTextPassword ;
    private Button signIn ;


    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();
        signUP=(TextView)findViewById(R.id.signUP);
        signUP.setOnClickListener(this);

        forgetPassword=(TextView)findViewById(R.id.forgotPassword);
        forgetPassword.setOnClickListener(this);

        signIn=(Button) findViewById(R.id.signIn);
        signIn.setOnClickListener(this);

        editTextEmail=(EditText) findViewById(R.id.email);
        editTextPassword=(EditText) findViewById(R.id.password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signUP:
                startActivity(new Intent(this,signUp.class));
                break;
            case R.id.signIn:
                userLogin();
                break;

            case R.id.forgotPassword:
                startActivity(new Intent(this, ForgotPassword.class));
                break;
        }
    }

    private void userLogin() {
        String email= editTextEmail.getText().toString().trim();
        String password= editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("password is required");
            editTextPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if (task.isSuccessful()){
                   FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                   if(user.isEmailVerified()){
                       startActivity(new Intent(MainActivity.this,Profil.class));
                   }else{
                       user.sendEmailVerification();
                       Toast.makeText(MainActivity.this,"verify your account ",Toast.LENGTH_LONG).show();
                   }

               }else {
                   Toast.makeText(MainActivity.this,"Failed to login",Toast.LENGTH_LONG).show();
               }
            }
        });
    }
}