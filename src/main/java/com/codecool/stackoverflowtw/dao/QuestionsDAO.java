package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.dao.model.Question;

import java.util.List;

public interface QuestionsDAO {
    int addQuestion(NewQuestionDTO question);
    Question getQuestion(int id);
    List<Question> getAllQuestion();
    Boolean updateQuestion(NewQuestionDTO question, int id);
    boolean deleteQuestion(int id);
    boolean deleteALLQuestion();

}
