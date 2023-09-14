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

    private QuestionsDAO questionsDAO;

    @Autowired
    public QuestionService(QuestionsDAO questionsDAO) {
        this.questionsDAO = questionsDAO;
    }

    public List<QuestionDTO> getAllQuestions() {
        List<QuestionDTO> questions = new ArrayList<>();

        questionsDAO.getAllQuestion().forEach(question ->  {
            questions.add(new QuestionDTO(
                    question.getId(),
                    question.getQuestionTitle(),
                    question.getQuestionDescription(),
                    question.getDate()
            ));
        });
        return questions;
    }
    public QuestionDTO getQuestionById(int id) {
        Question question = questionsDAO.getQuestion(id);
        return new QuestionDTO(
                question.getId(),
                question.getQuestionTitle(),
                question.getQuestionDescription(),
                question.getDate()
        );
    }
    public boolean deleteQuestionById(int id) {
        return questionsDAO.deleteQuestion(id);
    }

    public int addNewQuestion(NewQuestionDTO question) {
        return questionsDAO.addQuestion(question);
    }
    public boolean updateQuestion(NewQuestionDTO question, int id){
        return questionsDAO.updateQuestion(question, id);
    }
}
