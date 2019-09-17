package com.example.ahsanz.chitterchatterapp;

public class User {

    private String userId;
    private String name;
    private String userName;
    private String phoneNo;
    private String bio;
    private String profilePicUri;

    public User(String userId, String name, String userName, String phoneNo, String bio, String profilePicUri) {
        this.userId = userId;
        this.name = name;
        this.userName = userName;
        this.phoneNo = phoneNo;
        this.bio = bio;
        this.profilePicUri = profilePicUri;
    }

    public User(String userId) {
        this.userId = userId;
    }

    public User() {
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setProfilePicUri(String profilePicUri) {
        this.profilePicUri = profilePicUri;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getBio() {
        return bio;
    }

    public String getProfilePicUri() {
        return profilePicUri;
    }
}
