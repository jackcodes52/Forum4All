package com.example.forum4all_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    Dialog readmepopup;

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;
    private Button logout;
    private ImageView chat, readmee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        chat = (ImageView) findViewById(R.id.imageChat);
        readmee = (ImageView) findViewById(R.id.readme);
        readmee.setOnClickListener(this);


        chat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(ProfileActivity.this, MainChatActivity.class));
            }

        });



        logout = (Button) findViewById(R.id.logoutbtn);

        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            }

        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView UsernameProfile = (TextView) findViewById(R.id.usernameprofile);
        final TextView EmailProfile = (TextView) findViewById(R.id.emailprofile);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    String fullName = userProfile.username;
                    String email = userProfile.email;

                    UsernameProfile.setText(fullName);
                    EmailProfile.setText(email);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ProfileActivity.this,"Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onClick(View v) {
        iniPopup();
        readmepopup.show();

    }
    private void iniPopup() {
        readmepopup = new Dialog(this);
        readmepopup.setContentView(R.layout.activity_readme);
        readmepopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        readmepopup.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT,Toolbar.LayoutParams.WRAP_CONTENT);
        readmepopup.getWindow().getAttributes().gravity = Gravity.CENTER;
    }
}