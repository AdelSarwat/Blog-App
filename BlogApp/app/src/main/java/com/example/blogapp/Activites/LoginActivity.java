package com.example.blogapp.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.blogapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText userMail,userPassword;
    private Button btnLogin;
    private ProgressBar loadingProgress;
    private FirebaseAuth mAuth;
    private Intent HomeActivity;
    private ImageView LoginPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userMail = findViewById(R.id.login_email);
        userPassword = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.loginBtn);
        loadingProgress = findViewById(R.id.login_progress);
        LoginPhoto = findViewById(R.id.login_photo);


        // open Register To Create Account
        LoginPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent registerActivity = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(registerActivity);
                LoginActivity.this.finish();
            }
        });
        loadingProgress.setVisibility(View.INVISIBLE);
        mAuth= FirebaseAuth.getInstance();

        HomeActivity = new Intent(LoginActivity.this, com.example.blogapp.Activites.HomeScreen.class);
        // Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final  String mail= userMail.getText().toString();
                final  String password = userPassword.getText().toString();

                if (mail.isEmpty()||password.isEmpty()){

                    ShowMassage(" Please Verify All Filed ");
                    btnLogin.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility(View.INVISIBLE);
                }
                else {

                    SignIn( mail , password );
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null) {
            //user is already connected  so we need to redirect him to home page
            UpdateUI();

        }
    }

    private void SignIn(String mail, String password) {

        mAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    loadingProgress.setVisibility(View.VISIBLE);
                    btnLogin.setVisibility(View.INVISIBLE);

                    UpdateUI();

                }
                else{
                    ShowMassage(task.getException().getMessage());
                     btnLogin.setVisibility(View.VISIBLE);
                     loadingProgress.setVisibility(View.INVISIBLE);
            }
            }
        });


    }

    private void UpdateUI() {

        startActivity(HomeActivity);
        LoginActivity.this.finish();
    }

    private void ShowMassage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();

    }

}
