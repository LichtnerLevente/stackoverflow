package com.codecool.stackoverflowtw.dao.model;

import java.util.Date;

public class Answer {
    private final int id;
    private String answer;
    private final User user;
    private final int questionId;
    private final Date date;

    public Answer(int id, String answer, User user, int questionId, Date date) {
        this.id = id;
        this.answer = answer;
        this.user = user;
        this.questionId = questionId;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public int getQuestionId() {
        return questionId;
    }

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


}
