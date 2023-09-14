package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.logger.ConsoleLogger;
import com.codecool.stackoverflowtw.logger.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuestionsDaoJdbcTest {
    private static QuestionsDAO questionsDAO;
    private final Logger logger = new ConsoleLogger();

    private final String dbFile = "//localhost:5432/stackoverflow";
    private  final ConnectionManager connectionManager = new ConnectionManager(logger,dbFile);



    @BeforeAll
     void beforeAll() {
        questionsDAO = new QuestionsDaoJdbc(new ConsoleLogger(), connectionManager);//NOT STATIC  ANYMORE
    }

    @Test
    void addQuestion() {
        NewQuestionDTO question = new NewQuestionDTO("asd", "fdebd");
        int id = questionsDAO.addQuestion(question);
        System.out.println(questionsDAO.getAllQuestion().size());
        System.out.println(id);
        assertTrue(id > 0);
    }

    @Test
    void getQuestion() {
        QuestionDTO question = questionsDAO.getQuestion(1);
        assertNotNull(question);
//        System.out.println(question.getQuestion());
//        System.out.println(question.getDate());

    }

    @Test
    void getAllQuestion() {
        List<QuestionDTO> questions = questionsDAO.getAllQuestion();
        for (QuestionDTO question : questions) {
            assertNotNull(question);
//            System.out.println(question.getQuestion() + " " + question.getId());
        }
    }

    @Test
    void deleteQuestion() {
        assertTrue(questionsDAO.deleteQuestion(2));
        System.out.println(questionsDAO.getAllQuestion().size());
    }

//    @Test
//    void deleteALLQuestion() {
//        assertTrue(questionsDAO.deleteALLQuestion());
//        System.out.println(questionsDAO.getAllQuestion().size());
//    }
}