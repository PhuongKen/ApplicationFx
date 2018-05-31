/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class ConnectionHandle {

    private Connection connection;
    private final String CONNECTION_PREFIX = "jdbc:mysql://localhost:3306/";
    private final String DATABASE_NAME = "applicationFX";
    private final String UTF8_CONFIG = "?userUnicode=true&characterEncoding=utf-8";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    private static ConnectionHandle connectionHandle;

    public static ConnectionHandle getIntance() {
        if (null == connectionHandle) {
            connectionHandle = new ConnectionHandle();
        }
        return connectionHandle;
    }

    public Connection getConnection() throws SQLException {
        if (null == connection || connection.isClosed()) {
            connection = DriverManager.getConnection(CONNECTION_PREFIX + DATABASE_NAME + UTF8_CONFIG, USERNAME, PASSWORD);
        }
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
