package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.Question;
import com.codecool.stackoverflowtw.logger.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class QuestionsDaoJdbc implements QuestionsDAO {
    public static final String PASSWORD = "Katakur1";
    private  final Logger logger;
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,"");
            preparedStatement.setString(2,question.getQuestion());
            preparedStatement.executeUpdate();
            logger.logInfo("New Question Added");
            return true;
        }catch (SQLException e){
            logger.logError(e.getMessage());
        }
        return false;
    }

    @Override
    public Question getQuestion(int id) {
        String sql = "SELECT * FROM questions WHERE question_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if(result.first()){
                return new Question(id,
                        result.getString("question_text"),
                        LocalDate.parse(result.getString("question_date")),
                        null);
            }
        }catch (SQLException e){
            logger.logError(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Question> getAllQuestion() {
        return null;
    }

    @Override
    public boolean updateQuestion(Question question) {
        return false;
    }

    @Override
    public boolean deleteQuestion(int id) {
        return false;
    }

    @Override
    public boolean deleteALLQuestion() {
        return false;
    }

    @Override
    public void sayHi() {
        System.out.println("Hi DAO!");
    }
}
