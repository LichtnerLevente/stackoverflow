package com.codecool.stackoverflowtw.dao.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class User {
    private final int id;
    private String userName;
    private String password;
    private final List<Question> questions;
    private final List<Answer> answers;

    public User(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.questions = new ArrayList<>();
        this.answers = new ArrayList<>();
    }

    public User(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.questions = user.getQuestions();
        this.answers = user.getAnswers();
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

    public List<Question> getQuestions() {
        return new ArrayList<Question>(questions);
    }

    public List<Answer> getAnswers() {
        return new ArrayList<Answer>(answers);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
