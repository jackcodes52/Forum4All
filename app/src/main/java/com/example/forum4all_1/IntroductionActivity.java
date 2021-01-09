package com.example.forum4all_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class IntroductionActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 4200;
    ImageView logo, bg;
    TextView appName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        logo = findViewById(R.id.logoimg);
        appName = findViewById(R.id.titleimg);
        bg = findViewById(R.id.img);

        logo.animate().translationY(-1600).setDuration(1000).setStartDelay(3000);
        appName.animate().translationY(1400).setDuration(1000).setStartDelay(3000);
        bg.animate().translationY(3000).setDuration(1000).setStartDelay(3000);

        Toast.makeText(IntroductionActivity.this,"Hello", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent (getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);



    }
}