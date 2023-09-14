package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.dao.model.Question;
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

    public QuestionsDaoJdbc(Logger logger, ConnectionManager connectionManager) {
        this.logger = logger;
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
    public Question getQuestion(int id) {
        String sql = "SELECT * FROM questions WHERE question_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return new Question(
                        id,
                        result.getString("question_title"),
                        result.getString("question_description"),
                        result.getDate("question_date"),
                        null
                );
            }
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Question> getAllQuestion() {
        String sql = "SELECT * FROM questions ORDER BY question_date DESC";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet result = preparedStatement.executeQuery();
            List<Question> questions = new ArrayList<>();
            while (result.next()) {
                questions.add(new Question(
                        result.getInt("question_id"),
                        result.getString("question_title"),
                        result.getString("question_description"),
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
    public Question updateQuestion(Question question) {
        String sql = "UPDATE questions SET  question_title = ?, question_description = ? WHERE question_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, question.getQuestionTitle());
            preparedStatement.setString(2, question.getQuestionDescription());
            preparedStatement.setInt(3, question.getId());
            preparedStatement.executeUpdate();

            return
                    new Question(
                            question.getId(),
                            question.getQuestionTitle(),
                            question.getQuestionDescription(),
                            question.getDate(),
                            null
                    );

        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean deleteQuestion(int id) {
        String sql = "DELETE FROM questions WHERE question_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            answersDAO.deleteAnswersForQuestion(id);
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
