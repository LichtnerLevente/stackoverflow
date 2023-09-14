package com.codecool.stackoverflowtw.dao.model;

import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;

import java.util.Date;

public class Question {
    private final int id;

    private String questionTitle;

    private String questionDescription;

    private final Date date;
    private final Integer userId;

    public Question(int id, String questionTitle, String questionDescription, Date date, Integer userId) {
        this.id = id;
        this.questionTitle = questionTitle;
        this.questionDescription = questionDescription;
        this.date = date;
        this.userId = userId;
    }
    public Question(QuestionDTO questionDTO) {
        this.id = questionDTO.id();
        this.questionTitle = questionDTO.title();
        this.questionDescription = questionDTO.description();
        this.date = questionDTO.created();
        this.userId = questionDTO.userId();
    }

    public int getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public Date getDate() {
        return date;
    }
}
