package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.Question;
import com.codecool.stackoverflowtw.logger.ConsoleLogger;
import com.codecool.stackoverflowtw.logger.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class QuestionsDaoJdbcTest {
    private static QuestionsDAO questionsDAO;
    private final Logger logger = new ConsoleLogger();


    @BeforeAll
    static void beforeAll() {
        questionsDAO = new QuestionsDaoJdbc(new ConsoleLogger(), "//localhost:5432/stackoverflow");
    }

    @Test
    void addQuestion() {
        Question question = new Question(0, "asd", LocalDate.now(), null);
        assertTrue(questionsDAO.addQuestion(question));
    }

    @Test
    void getQuestion() {
        Question question = questionsDAO.getQuestion(1);
        assertNotNull(question);

    }
}