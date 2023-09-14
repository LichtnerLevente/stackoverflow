package com.codecool.stackoverflowtw;

import com.codecool.stackoverflowtw.dao.*;
import com.codecool.stackoverflowtw.logger.ConsoleLogger;
import com.codecool.stackoverflowtw.logger.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StackoverflowTwApplication {
    private final Logger logger = new ConsoleLogger();
    private final String dbFile = "//localhost:5432/stackoverflow";
    private  final ConnectionManager connectionManager = new ConnectionManager(logger,dbFile);
    public static void main(String[] args) {
        SpringApplication.run(StackoverflowTwApplication.class, args);
    }

    @Bean
    public QuestionsDAO questionsDAO() {
        return new QuestionsDaoJdbc(logger, connectionManager);
    }

    @Bean
    public AnswersDAO answersDAO(){ return new AnswersDaoJdbc(connectionManager,logger);
    }

}
