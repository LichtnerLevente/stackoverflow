package com.codecool.stackoverflowtw.dao.model;

import java.util.Date;

public class Answer {
    private final int id;
    private String answer;
    private final int questionId;
    private final Date date;
    private final Integer userId;

    public Answer(int id, String answer, int questionId, Date date, Integer userId) {
        this.id = id;
        this.answer = answer;
        this.questionId = questionId;
        this.date = date;
        this.userId = userId;
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

    public Date getDate() {
        return date;
    }

    public Integer getUserId() {
        return userId;
    }
}
