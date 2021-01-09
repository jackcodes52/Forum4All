package com.example.forum4all_1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forum4all_1.homebuttons.EducationActivity;
import com.example.forum4all_1.homebuttons.FoodActivity;
import com.example.forum4all_1.homebuttons.GamesActivity;
import com.example.forum4all_1.homebuttons.MusicActivity;
import com.example.forum4all_1.homebuttons.NewsActivity;
import com.example.forum4all_1.homebuttons.TechnologyActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    Dialog countdownpopup;

    private ImageView profile, logout;
    private Button games, music, food, education, news, tech, takeabreak;

    private TextView textViewCountDown;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textViewCountDown = findViewById(R.id.view_countdown);

       profile = (ImageView) findViewById(R.id.profilebtn);
       profile.setOnClickListener(this);

       logout = (ImageView) findViewById(R.id.logoutbtn2);
       logout.setOnClickListener(this);

       games = (Button) findViewById(R.id.gamesbt);
       games.setOnClickListener(this);

       music = (Button) findViewById(R.id.musicbt);
       music.setOnClickListener(this);

       food = (Button) findViewById(R.id.foodbt);
       food.setOnClickListener(this);

       education = (Button) findViewById(R.id.educationbt);
       education.setOnClickListener(this);

       news = (Button) findViewById(R.id.newsbt);
       news.setOnClickListener(this);

       tech = (Button) findViewById(R.id.techbt);
       tech.setOnClickListener(this);


       startCountDown();

       iniPopup();




    }

    private void startCountDown() {
        timeLeftInMillis = 900000;
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {
                countdownpopup.show();
                startCountDown();
            }
        }.start();

    }

    private void iniPopup() {
        countdownpopup = new Dialog(this);
        countdownpopup.setContentView(R.layout.activity_countdown);
        countdownpopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        countdownpopup.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT,Toolbar.LayoutParams.WRAP_CONTENT);
        countdownpopup.getWindow().getAttributes().gravity = Gravity.CENTER;
        takeabreak = countdownpopup.findViewById(R.id.takeabreak);
        takeabreak.setOnClickListener(this);

        }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes, seconds);

        textViewCountDown.setText(timeFormatted);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.profilebtn:
                startActivity(new Intent(this,ProfileActivity.class));
                break;
            case R.id.logoutbtn2:
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setMessage("Do you want to exit?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finishAffinity();
                                System.exit(0);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                break;
            case R.id.gamesbt:
                startActivity(new Intent(this, GamesActivity.class));
                break;
            case R.id.musicbt:
                startActivity(new Intent(this, MusicActivity.class));
                break;
            case R.id.foodbt:
                startActivity(new Intent(this, FoodActivity.class));
                break;
            case R.id.educationbt:
                startActivity(new Intent(this, EducationActivity.class));
                break;
            case R.id.newsbt:
                startActivity(new Intent(this, NewsActivity.class));
                break;
            case R.id.techbt:
                startActivity(new Intent(this, TechnologyActivity.class));
                break;
            case R.id.takeabreak:
                finishAffinity();
                System.exit(0);
                break;
        }

    }

    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle("Forum 4 All");
        builder.setIcon(R.drawable.logo);
        builder.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }



}