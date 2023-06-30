package com.example.hostelgomadproject;

public class userType {
    String Category;
    String EmailID;
    String userID;

    public userType() {
    }

    public userType(String category, String emailID, String userID) {
        Category = category;
        EmailID = emailID;
        this.userID = userID;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getEmailID() {
        return EmailID;
    }

    public void setEmailID(String emailID) {
        EmailID = emailID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
