package com.codecool.stackoverflowtw.dao.model;

import com.codecool.stackoverflowtw.controller.dto.NewUserDTO;
import com.codecool.stackoverflowtw.dao.ConnectionManager;
import com.codecool.stackoverflowtw.logger.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJbdc implements UserDAO {
    private final Logger logger;
    private final Connection connection;

    public UserDaoJbdc(Logger logger, ConnectionManager connectionManager) {
        this.logger = logger;
        this.connection = connectionManager.getConnection();
    }

    @Override
    public List<User> getAllUser() {
        String sql = "SELECT  * FROM  users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet result = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while (result.next()) {
                users.add(new User(
                        result.getInt("user_id"),
                        result.getString("user_name"),
                        result.getString("user_password"),
                        result.getDate("user_date")
                ));
            }
            return users;
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return null;
    }

    @Override
    public User getUserById(int id) {
        String sql = "SELECT  * FROM  users WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return new User(
                        result.getInt("user_id"),
                        result.getString("user_name"),
                        result.getString("user_password"),
                        result.getDate("user_date")
                );
            }
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return null;
    }

    @Override
    public int addUser(NewUserDTO user) {
        String sql = "INSERT INTO users(user_name, user_password)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.username());
            preparedStatement.setString(2, user.password());
            preparedStatement.executeUpdate();
            ResultSet result = preparedStatement.getGeneratedKeys();
            if (result.next()) {
                return result.getInt("user_id");
            }
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return -1;
    }
}
