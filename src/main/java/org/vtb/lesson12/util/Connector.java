package org.vtb.lesson12.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {

    private static Connection connection;
    private static Statement stmt;

    public void connect() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:javadb.db");
            stmt = connection.createStatement();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return stmt;
    }

    public void disconnect() {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
