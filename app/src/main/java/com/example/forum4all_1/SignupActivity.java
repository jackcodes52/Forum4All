package com.example.forum4all_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private Button registeruser;
    private EditText Username, Useremail, Userpassword;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        registeruser = (Button) findViewById(R.id.signup_btn);
        registeruser.setOnClickListener(this);

        Username = (EditText) findViewById(R.id.username);
        Useremail = (EditText) findViewById(R.id.email_edt_text);
        Userpassword = (EditText) findViewById(R.id.pass_edt_text);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.signup_btn:
                registeruser();
                break;
        }
    }

    private void registeruser() {

        final String email = Useremail.getText().toString().trim();
        final String password = Userpassword.getText().toString().trim();
        final String username = Username.getText().toString().trim();

        if(username.isEmpty()){
            Username.setError("Username is required");
            Username.requestFocus();
            return;
        }

        if(email.isEmpty()){
            Useremail.setError("Email is required");
            Useremail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Useremail.setError("Please provide valid email!");
            Useremail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            Userpassword.setError("Password is required");
            Userpassword.requestFocus();
            return;
        }

        if(password.length()<5){
            Userpassword.setError("Min password length should be at least 5 characters");
            Userpassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            User user = new User(username, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(SignupActivity.this,"User has been registered!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);

                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(username).build();

                                        user.updateProfile(profileUpdates);

                                        startActivity(new Intent(SignupActivity.this, HomeActivity.class));
                                    }else{
                                        Toast.makeText(SignupActivity.this, "Failed to register!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });


                        }else{
                            Toast.makeText(SignupActivity.this, "Failed to register!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });



    }

}