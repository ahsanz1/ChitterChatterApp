package com.example.ahsanz.chitterchatterapp;

public class Message {

    private String senderId;
    private String recieverId;
    private String messageText;
    private String messageTime;
    private String photoUri;
    private boolean seenByReciever;

    public Message(String senderId, String recieverId, String messageText, String messageTime, String photoUri, boolean seenByReciever) {
        this.senderId = senderId;
        this.recieverId = recieverId;
        this.messageText = messageText;
        this.messageTime = messageTime;
        this.photoUri = photoUri;
        this.seenByReciever = seenByReciever;
    }

    public Message() {
    }

    public String getSenderId() {
        return senderId;
    }

    public String getRecieverId() {
        return recieverId;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public boolean isSeenByReciever() {
        return seenByReciever;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public void setRecieverId(String recieverId) {
        this.recieverId = recieverId;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public void setSeenByReciever(boolean seenByReciever) {
        this.seenByReciever = seenByReciever;
    }
}
