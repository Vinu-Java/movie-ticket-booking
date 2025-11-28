package com.moviebookingsystem.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    public static Connection getConnection() {

        Connection con;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 🔥 Read cloud credentials from environment variables
            String url = System.getenv("DB_URL");
            String username = System.getenv("DB_USER");
            String password = System.getenv("DB_PASS");

            // 🔥 Fallback to local database (optional, for development)
            if (url == null || url.isEmpty()) {
                url = "jdbc:mysql://localhost/TicketBooking";
            }
            if (username == null || username.isEmpty()) {
                username = "root";
            }
            if (password == null || password.isEmpty()) {
                password = "Vinu@2003";
            }

            con = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return con;
    }
}

