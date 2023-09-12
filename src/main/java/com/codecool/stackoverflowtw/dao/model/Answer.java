package com.codecool.stackoverflowtw.dao.model;

import java.util.Date;

public class Answer {
    private final int id;
    private String answer;
    private  String username;
    private  int questionId;
    private final Date date;

    public int getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    public int getQuestion() {
        return questionId;
    }

    public Date getDate() {
        return date;
    }

    public Answer(int id, String answer, String username, int questionId, Date date) {
        this.id = id;
        this.answer = answer;
        this.username = username;
        this.questionId = questionId;
        this.date = date;
    }
}
