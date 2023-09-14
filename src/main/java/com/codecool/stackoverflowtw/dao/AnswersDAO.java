package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.dao.model.Answer;
import com.codecool.stackoverflowtw.dao.model.Question;

import java.util.List;

public interface AnswersDAO {
    int addAnswer(NewAnswerDTO newAnswer);
    Answer getAnswer(int id);
    List<Answer> getAllAnswer();
    boolean updateAnswer(Answer answer,String modifiedAnswer);
    boolean deleteAnswer(int answerId);
    boolean deleteAnswerForQuestion(int questionId);
    boolean deleteALLAnswer();
}
