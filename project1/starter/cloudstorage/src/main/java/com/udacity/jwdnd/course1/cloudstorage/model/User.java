package com.udacity.jwdnd.course1.cloudstorage.model;

public class User {
    private Integer userID;
    private String username;
    private String password;
    private String salt;


    public User(Integer userID, String username, String password, String salt) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.salt = salt;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
