package ru.velkomfood.datamax.print.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * Created by dpetrov on 12.11.16.
 */

public class DbManager {

    private static DbManager instance;
    private Connection connection;

    private DbManager() { }

    public static DbManager getInstance() {
        if (instance == null) instance = new DbManager();
        return instance;
    }

    public void openDbConnection() throws SQLException {

        int year = Calendar.getInstance().get(Calendar.YEAR);
        String dbPath = "jdbc:sqlite:db/sapdata-" + year + ".db";
        connection = DriverManager.getConnection(dbPath);

    }

    public void closeDbConnection() throws SQLException {
        if (connection != null && !connection.isClosed())
            connection.close();
    }

}
