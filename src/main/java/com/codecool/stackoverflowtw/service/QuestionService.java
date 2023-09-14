package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private final QuestionsDAO questionsDAO;

    @Autowired
    public QuestionService(QuestionsDAO questionsDAO) {
        this.questionsDAO = questionsDAO;
    }

    public List<QuestionDTO> getAllQuestions() {
        List<QuestionDTO> questions = new ArrayList<>();

        questionsDAO.getAllQuestion().forEach(question -> {
            questions.add(new QuestionDTO(question));
        });
        return questions;
    }

    public QuestionDTO getQuestionById(int id) {
        return new QuestionDTO(questionsDAO.getQuestion(id));
    }

    public boolean deleteQuestionById(int id) {
        return questionsDAO.deleteQuestion(id);
    }

    public int addNewQuestion(NewQuestionDTO question) {
        return questionsDAO.addQuestion(question);
    }

    public QuestionDTO updateQuestion(QuestionDTO questionDTO) {
        return new QuestionDTO(questionsDAO.updateQuestion(new Question(questionDTO)));
    }
}
