package com.group0201.motobikelicenseexam.model;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String userName;
    private String password;
    private boolean isActive;
    private int gender;
    private String dob;
    private String phone;

    public User() {

    }

    public User(int id, String name, String username, String password, boolean isActive, int gender, String dob, String phone) {
        this.id = id;
        this.name = name;
        this.userName = username;
        this.password = password;
        this.isActive = isActive;
        this.gender = gender;
        this.dob = dob;
        this.phone = phone;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
