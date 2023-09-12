package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.Question;
import com.codecool.stackoverflowtw.logger.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDaoJdbc implements QuestionsDAO {
    public static final String PASSWORD = "Katakur1";
    private final Logger logger;
    private final Connection connection;
    private final String dbFile;

    public QuestionsDaoJdbc(Logger logger, String dbFile) {
        this.logger = logger;
        this.dbFile = dbFile;
        this.connection = getConnection();
    }

    private Connection getConnection() {
        Connection connection;
        try {
            String url = "jdbc:postgresql:" + dbFile;
            connection = DriverManager.getConnection(url, "postgres", PASSWORD);

            logger.logInfo("Connection to Postgres has been established.");

            return connection;

        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean addQuestion(Question question) {
        String sql = "INSERT INTO questions(username, question_text) VALUES(?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "");
            preparedStatement.setString(2, question.getQuestion());
            preparedStatement.executeUpdate();
            logger.logInfo("New Question Added");
            return true;
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return false;
    }

    @Override
    public Question getQuestion(int id) {
        String sql = "SELECT * FROM questions WHERE question_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return new Question(id,
                        result.getString("question_text"),
                        result.getDate("question_date"),
//                        LocalDateTime.parse(result.getString("question_date")),
                        null);
            }
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Question> getAllQuestion() {
        String sql = "SELECT * FROM questions";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet result = preparedStatement.executeQuery();
            List<Question> questions = new ArrayList<>();
            while (result.next()) {
                questions.add(new Question(
                        result.getInt("question_id"),
                        result.getString("question_text"),
                        result.getDate("question_date"),
                        null
                ));
            }
            return questions;
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateQuestion(Question question) {
        return false;
    }

    @Override
    public boolean deleteQuestion(int id) {
        String sql = "DELETE FROM questions WHERE question_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteALLQuestion() {
        String sql = "DELETE FROM questions";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            logger.logInfo("All Questions Deleted");
            return true;
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return false;
    }
}
