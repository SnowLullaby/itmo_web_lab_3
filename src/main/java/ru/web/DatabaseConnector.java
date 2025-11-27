package ru.web;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@ApplicationScoped
public class DatabaseConnector {
    private final String url = System.getProperty("dbUrl");
    private final String username = System.getProperty("dbUser");
    private final String password = System.getProperty("dbPass");

    private Connection connection;

    @PostConstruct
    public void init() {
        connection = tryConnection();
    }

    @PreDestroy
    public void close() {
        if (connection != null) {
            try {
                connection.prepareStatement("DROP TABLE IF EXISTS points CASCADE ;").execute();
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private Connection tryConnection(){
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement statement = conn.createStatement();
            String createQuery = "CREATE TABLE IF NOT EXISTS points (" +
                    "id SERIAL PRIMARY KEY," +
                    "x DOUBLE PRECISION," +
                    "y DOUBLE PRECISION," +
                    "z DOUBLE PRECISION," +
                    "r DOUBLE PRECISION," +
                    "hit BOOLEAN," +
                    "\"current_time\" VARCHAR(100)," +
                    "execution_time_ns BIGINT" +
                    ");";
            statement.execute(createQuery);
            System.out.println("Table created successfully!");
            return conn;
        } catch (SQLException e) {
            System.err.println("Error creating connection: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public Connection getConnection() {
        if (connection == null) {
            connection = tryConnection();
        }
        return connection;
    }
}
