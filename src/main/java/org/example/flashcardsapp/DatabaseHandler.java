package org.example.flashcardsapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandler extends Configs {
    private Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        // Используем правильное имя класса драйвера
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void signUpUser(String login, String name, String password) {
        // Исправляем SQL запрос: добавляем пробелы и правильный порядок
        String insert = "INSERT INTO " + Const.USER_TABLE + " (" + Const.USERS_UNAME + ", " + Const.USERS_NAME + ", " + Const.USERS_PASSWORD + ") VALUES (?, ?, ?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, login);
            prSt.setString(2, name);
            prSt.setString(3, password);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
