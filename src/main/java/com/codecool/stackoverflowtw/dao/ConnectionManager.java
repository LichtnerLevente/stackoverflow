package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.logger.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionManager {

    public static final String PASSWORD = "1234";
    private final Logger logger;
    private final Connection connection;
    private final String dbFile;

    public ConnectionManager(Logger logger, String dbFile) {
        this.logger = logger;
        this.dbFile = dbFile;
        this.connection = initializeConnection();
    }

    private Connection initializeConnection() {
        Connection conn = null;
        try {
            String url = "jdbc:postgresql:" + dbFile;
            conn = DriverManager.getConnection(url, "postgres", PASSWORD);
            logger.logInfo("Connection to Postgres has been established.");
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return conn;
    }

    public Connection getConnection() {
        return connection;
    }
}
