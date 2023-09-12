package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.Answer;
import com.codecool.stackoverflowtw.dao.model.Question;
import com.codecool.stackoverflowtw.logger.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class AnswersDaoJdbc implements  AnswersDAO {

    private final Connection connection;
    private  final Logger logger;

    public AnswersDaoJdbc(ConnectionManager connection, Logger logger) {
        this.connection = connection.getConnection();
        this.logger = logger;
    }

    @Override
    public boolean addAnswer(Answer answer) {
        String sql = "INSERT INTO answers(username, answer_text) VALUES(?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,"");
            preparedStatement.setString(2,answer.getAnswer());
            preparedStatement.executeUpdate();
            logger.logInfo("New Answer Added");
            return true;
        }catch (SQLException e){
            logger.logError(e.getMessage());
        }
        return false;
    }

    @Override
    public Answer getAnswer(int id) {
        String sql = "SELECT * FROM answers WHERE question_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if(result.first()){
                return new Answer(id,result.getString("answer_text"),
                        result.getString("username"),
                        result.getInt("question_id"),
                        result.getDate("answer_date")
                        );
            }
        }catch (SQLException e){
            logger.logError(e.getMessage());
        }
        return null;
    }




    @Override
    public List<Answer> getAllAnswer() {
        return null;
    }

    @Override
    public boolean updateAnswer(Answer answer, String modifiedAnswer) {
        return false;
    }

    @Override
    public boolean deleteAnswer(Answer answer) {
        return false;
    }

    @Override
    public boolean deleteALLAnswer() {
        return false;
    }
}
