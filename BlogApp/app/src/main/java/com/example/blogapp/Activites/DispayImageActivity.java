package com.example.blogapp.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.blogapp.R;

public class DispayImageActivity extends AppCompatActivity {

    static String imagediaplay;
    static String UserName;

    ImageView postImage,back;
    TextView PostUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispay_image);


        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar().hide();

        postImage= findViewById(R.id.img);
        PostUserName=findViewById(R.id.uname);
        back=findViewById(R.id.backToHome);

        PostUserName.setText(UserName);
        Glide.with(this).load(imagediaplay).into(postImage);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(DispayImageActivity.this,HomeScreen.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
