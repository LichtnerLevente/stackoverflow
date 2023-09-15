package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.dao.model.Answer;
import com.codecool.stackoverflowtw.logger.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswersDaoJdbc implements  AnswersDAO {

    private final Connection connection;
    private  final Logger logger;

    public AnswersDaoJdbc(ConnectionManager connection, Logger logger) {
        this.connection = connection.getConnection();
        this.logger = logger;
    }

    @Override
    public int addAnswer(NewAnswerDTO newAnswer) {
        String sql = "INSERT INTO answers( answer_text, question_id) VALUES (?, ?) RETURNING * ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newAnswer.answer());
            preparedStatement.setInt(2, newAnswer.questionId());
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return result.getInt("answer_id");
            }
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return 0;
    }

    @Override
    public Answer getAnswer(int id) {
        String sql = "SELECT * FROM answers WHERE answer_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if(result.first()){
                return new Answer(id,result.getString("answer_text"),
                        result.getInt("question_id"),
                        result.getDate("answer_date"),
                        result.getInt("user_id")
                        );
            }
        }catch (SQLException e){
            logger.logError(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Answer> getAllAnswersForQuestions(int questionId) {
        String sql ="SELECT * FROM answers WHERE question_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, questionId);
           ResultSet result = preparedStatement.executeQuery();
           List<Answer> answers = new ArrayList<>();
           while (result.next()){
               answers.add(new Answer(
                       result.getInt("answer_id"),
                       result.getString("answer_text"),
                       result.getInt("question_id"),
                       result.getDate("answer_date"),
                       result.getInt("user_id")
               ));
           }
            return answers;
        }catch (SQLException e){
             logger.logError(e.getMessage());
        }
        return null;
    }

    @Override
    public Answer updateAnswer(Answer answer) {
        String sql = "UPDATE answer SET answer_text = ? WHERE answer_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, answer.getAnswer());
            pstmt.setInt(2, answer.getId());
            pstmt.executeUpdate();
            logger.logInfo("Answer updated");
            return answer;
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return null;
    }


    @Override
    public boolean deleteAnswerById(int answerId) {
        String sql = "DELETE FROM answers WHERE answer_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, answerId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return false;

    }

    @Override
    public boolean deleteAnswersForQuestion(int questionId) {
        String sql = "DELETE FROM answers WHERE question_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, questionId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteALLAnswer() {
        String sql = "DELETE FROM answers";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            logger.logInfo("All Answers Deleted");
            return true;
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return false;
    }
}
