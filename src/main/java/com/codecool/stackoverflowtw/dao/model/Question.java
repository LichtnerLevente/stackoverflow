package com.codecool.stackoverflowtw.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Question {
    private final int id;

    private String questionTitle;

    private  String questionDescription;

    private final Date date;
    private final List<Answer> answers = new ArrayList<>();
    private final Integer userId ; // CHANGE
    public Question(int id, String questionTitle, String questionDescription, Date date, Integer userId) {
        this.id = id;
        this.questionTitle = questionTitle;
        this.questionDescription = questionDescription;
        this.date = date;
        this.userId = userId;
    }
    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }
    public int getId() {
        return id;
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

    public List<Answer> getAnswers() {
        return answers;
    }

    public Integer getUserId() {
        return userId;
    }


}
