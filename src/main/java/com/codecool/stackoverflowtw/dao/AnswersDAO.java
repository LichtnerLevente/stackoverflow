package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.dao.model.Answer;

import java.util.List;

public interface AnswersDAO {
    int addAnswer(NewAnswerDTO newAnswer);
    Answer getAnswer(int id);
    List<Answer> getAllAnswersForQuestions(int questionId);
    Answer updateAnswer(Answer answer);
    boolean deleteAnswerById(int answerId);
    boolean deleteAnswersForQuestion(int questionId);
    boolean deleteALLAnswer();
}
