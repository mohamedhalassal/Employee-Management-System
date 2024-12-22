package org.software.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {

    private static volatile DatabaseConnectionManager instance;
    private Connection connection;

    private DatabaseConnectionManager() {
    }

    public static DatabaseConnectionManager getInstance() {
        if (instance == null) {
            if (instance == null) {
                instance = new DatabaseConnectionManager();
            }

        }
        return instance;
    }

    public void connect() {

        System.out.println("Connecting to the database...");
        try {
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=employ_MS;encrypt=true;trustServerCertificate=true;",
                    "emp",
                    "pegasus"
            );
            System.out.println("Connected to the database successfully.");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }

    public void disconnect() {
        System.out.println("Disconnecting from the database...");
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Disconnected from the database.");
            }
        } catch (SQLException e) {
            System.out.println("Error disconnecting from the database: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
}

