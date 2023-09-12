package com.codecool.stackoverflowtw.dao.model;

public class Answer {
    private final int id;
    private String answer;
    private final User user;
    private final Question question;

    public Answer(int id, String answer, User user, Question question) {
        this.id = id;
        this.answer = answer;
        this.user = user;
        this.question = question;
    }
}
