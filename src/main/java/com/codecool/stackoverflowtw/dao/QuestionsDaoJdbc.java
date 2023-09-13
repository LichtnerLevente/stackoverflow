package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.logger.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDaoJdbc implements QuestionsDAO {
    private final Logger logger;
    private final Connection connection;
    private final AnswersDAO answersDAO;

    public QuestionsDaoJdbc(Logger logger, String dbFile) {
        this.logger = logger;
        ConnectionManager connectionManager = new ConnectionManager(logger, dbFile);
        this.connection = connectionManager.getConnection();
        this.answersDAO = new AnswersDaoJdbc(connectionManager, logger);
    }

    @Override
    public int addQuestion(NewQuestionDTO question) {
        String sql = "INSERT INTO questions(question_title, question_description) VALUES(?, ?) RETURNING question_id";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, question.title());
            preparedStatement.setString(2, question.description());
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                logger.logInfo("New Question Added");
                return result.getInt("question_id");
            }
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return -1;
    }

    @Override
    public QuestionDTO getQuestion(int id) {
        String sql = "SELECT * FROM questions WHERE question_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return new QuestionDTO(
                        id,
                        result.getString("question_title"),
                        result.getString("question_description"),
                        result.getDate("question_date")
                );
            }
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return null;
    }

    @Override
    public List<QuestionDTO> getAllQuestion() {
        String sql = "SELECT * FROM questions";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet result = preparedStatement.executeQuery();
            List<QuestionDTO> questions = new ArrayList<>();
            while (result.next()) {
                questions.add(new QuestionDTO(
                        result.getInt("question_id"),
                        result.getString("question_title"),
                        result.getString("question_description"),
                        result.getDate("question_date")
                ));
            }
            return questions;
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateQuestion(QuestionDTO question) {
        String sql = "UPDATE questions SET  question_title = ?, question_description = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, question.title());
            preparedStatement.setString(2, question.description());
            preparedStatement.setInt(3, question.id());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteQuestion(int id) {
        String sql = "DELETE FROM questions WHERE question_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            answersDAO.deleteAnswers(id);
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
            answersDAO.deleteALLAnswer();
            preparedStatement.executeUpdate();
            logger.logInfo("All Questions Deleted");
            return true;
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return false;
    }
}
