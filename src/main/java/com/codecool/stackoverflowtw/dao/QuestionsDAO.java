package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.Question;

import java.util.List;

public interface QuestionsDAO {
    boolean addQuestion(Question question);
    Question getQuestion(int id);
    List<Question> getAllQuestion();
    boolean updateQuestion(Question question);
    boolean deleteQuestion(int id);
    boolean deleteALLQuestion();

}
