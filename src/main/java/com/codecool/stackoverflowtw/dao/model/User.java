package com.codecool.stackoverflowtw.dao.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final int id;
    private String userName;
    private String password;
    private List<Question> questions = new ArrayList<>();

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    private List<Answer> answers = new ArrayList<>();

    public User(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }
}
