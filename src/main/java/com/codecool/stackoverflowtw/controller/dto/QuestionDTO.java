package com.codecool.stackoverflowtw.controller.dto;

import com.codecool.stackoverflowtw.dao.model.Question;

import java.util.Date;

public record QuestionDTO(int id, String title, String description, Date created, int userId) {
    public QuestionDTO(Question question) {
        this(
                question.getId(),
                question.getQuestionTitle(),
                question.getQuestionDescription(),
                question.getDate(),
                question.getUserId()
        );
    }
}

