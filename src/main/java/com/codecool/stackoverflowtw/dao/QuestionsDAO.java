package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.dao.model.Question;

import java.util.List;

public interface QuestionsDAO {
    int addQuestion(NewQuestionDTO question);
    QuestionDTO getQuestion(int id);
    List<QuestionDTO> getAllQuestion();
    boolean updateQuestion(QuestionDTO question);
    boolean deleteQuestion(int id);
    boolean deleteALLQuestion();

}
