package com.codecool.stackoverflowtw.dao.model;

import com.codecool.stackoverflowtw.controller.dto.UserDTO;

import java.util.Date;

public class User {
    private final int id;
    private String userName;
    private String password;
    private final Date date;

    public User(int id, String userName, String password, Date date) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.date = date;
    }

    public User(UserDTO user) {
        this.id = user.id();
        this.userName = user.username();
        this.password = user.password();
        this.date = user.created();
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Date getDate() {
        return date;
    }
}
