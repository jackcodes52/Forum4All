package com.example.forum4all_1.homebuttons;

import com.google.firebase.database.ServerValue;

public class Foodreportreport {
    private String postKey;
    private String title;
    private String description;
    private String userID;
    private String username;
    private Object timeStamp;

    public Foodreportreport(String title, String description, String userID, String username) {
        this.title = title;
        this.description = description;
        this.userID = userID;
        this.username = username;
        this.timeStamp = ServerValue.TIMESTAMP;
    }

    public Foodreportreport(){

    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPostKey() {
        return postKey;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }



    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }
}
