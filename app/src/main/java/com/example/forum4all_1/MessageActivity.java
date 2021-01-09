package com.example.forum4all_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forum4all_1.adapters.ChatAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageActivity extends AppCompatActivity {


    TextView username;
    ImageButton btn_send;
    EditText text_send;

    String useridname, userrrr;
    FirebaseUser fuser;
    DatabaseReference reference;


    ChatAdapter adapterChat;
    List<Chat> mChat;

    RecyclerView recyclerView;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btn_send = findViewById(R.id.btn_send);
        text_send = findViewById(R.id.text_send);
        username = findViewById(R.id.username);


        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterChat);


        intent = getIntent();
        useridname = intent.getStringExtra("userID");

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");

        userrrr = fuser.getDisplayName();

        Query userQuery = reference.orderByChild("username").equalTo(useridname);


        readMessages(userrrr, useridname);

        userQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()){
                    String name = "" + ds.child("username").getValue();
                    username.setText(name);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

        //Button to send message
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //Get Text
                String message = text_send.getText().toString().trim();
                //Check if Text is empty or not
                if (TextUtils.isEmpty(message)){
                    Toast.makeText(MessageActivity.this,"Cannot send empty message", Toast.LENGTH_SHORT).show();
                }
                else{
                    sendMessage(fuser.getDisplayName(), useridname, message);
                }

            }
        });


    }


    private void sendMessage(String sender, String receiver, String message) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        String timestamp = String.valueOf(System.currentTimeMillis());

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("Sender", sender);
        hashMap.put("Receiver", receiver);
        hashMap.put("Timestamp", timestamp);
        hashMap.put("Message", message);
        databaseReference.child("Chats").push().setValue(hashMap);

        //Reset textbox after sending message

        text_send.setText("");


    }

    private void readMessages(final String sender, final String receiver) {

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Chats");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mChat = new ArrayList<>();
                mChat.clear();

                for (DataSnapshot ds: snapshot.getChildren()){
                 Chat chat = ds.getValue(Chat.class);
                 if (chat.getReceiver() != null && chat.getReceiver().equals(sender) && chat.getSender() != null && chat.getSender().equals(receiver) ||
                         chat.getReceiver() != null && chat.getReceiver().equals(receiver) && chat.getSender() != null && chat.getSender().equals(sender)){
                     mChat.add(chat);
                    }
                    adapterChat = new ChatAdapter(MessageActivity.this, mChat);
                 adapterChat.notifyDataSetChanged();
                    recyclerView.setAdapter(adapterChat);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}