package com.example.forum4all_1;

public class Chat {

    private String Sender;
    private String Receiver;
    private String Message;
    private Object Timestamp;


    public Chat() {
    }

    public Chat(String Sender, String Receiver, String Message, Object Timestamp) {
        this.Sender = Sender;
        this.Receiver = Receiver;
        this.Message = Message;
        this.Timestamp = Timestamp;

    }

    public Object getTimeStamp() {
        return Timestamp;
    }

    public void setTimeStamp(Object timeStamp) {
        Timestamp = timeStamp;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String Sender) {
        this.Sender = Sender;
    }

    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String Receiver) {
        this.Receiver = Receiver;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }


}
