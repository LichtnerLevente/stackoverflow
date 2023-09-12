package com.codecool.stackoverflowtw.dao.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Question {
    private final int id;

    private String question;

    private final LocalDate date;
    private final List<Answer> answers = new ArrayList<>();
    private final User user;
    public Question(int id, String question, LocalDate date, User user) {
        this.id = id;
        this.question = question;
        this.date = date;
        this.user = user;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public User getUser() {
        return user;
    }


}
