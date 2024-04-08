package com.example.goodwillapp.common;

public class RegisterationModel {
    String userName;
    String emailID;
    String password;

    public RegisterationModel() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RegisterationModel(String userName, String emailID, String password) {
        this.userName = userName;
        this.emailID = emailID;
        this.password = password;
    }
}
