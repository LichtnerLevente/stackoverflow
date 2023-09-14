package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.dao.model.Question;

import java.util.List;

public interface QuestionsDAO {
    int addQuestion(NewQuestionDTO question);
    Question getQuestion(int id);
    List<Question> getAllQuestion();
    Question updateQuestion(Question question);
    boolean deleteQuestion(int id);
    boolean deleteALLQuestion();

}
