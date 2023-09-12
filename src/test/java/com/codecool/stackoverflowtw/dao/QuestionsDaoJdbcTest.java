package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.Question;
import com.codecool.stackoverflowtw.logger.ConsoleLogger;
import com.codecool.stackoverflowtw.logger.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuestionsDaoJdbcTest {
    private static QuestionsDAO questionsDAO;
    private final Logger logger = new ConsoleLogger();


    @BeforeAll
    static void beforeAll() {
        questionsDAO = new QuestionsDaoJdbc(new ConsoleLogger(), "//localhost:5432/stackoverflow");
    }

    @Test
    void addQuestion() {
        Question question = new Question(0, "asd", new Date(), null);
        assertTrue(questionsDAO.addQuestion(question));
    }

    @Test
    void getQuestion() {
        Question question = questionsDAO.getQuestion(1);
        assertNotNull(question);
//        System.out.println(question.getQuestion());
//        System.out.println(question.getDate());

    }

    @Test
    void getAllQuestion() {
        List<Question> questions = questionsDAO.getAllQuestion();
        for (Question question : questions) {
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