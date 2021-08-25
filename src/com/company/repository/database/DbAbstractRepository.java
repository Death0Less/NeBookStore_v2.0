package com.company.repository.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DbAbstractRepository {

    Connection connection;

    private static final String URL = "jdbc:postgresql://localhost:5432/test_base";
    private static final String LOGIN = "postgres";
    private static final String PASSWORD = "admin";

    {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException sqlException) {
            sqlException.getMessage();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
