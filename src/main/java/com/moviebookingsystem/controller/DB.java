package com.moviebookingsystem.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Read cloud environment variables
            String url = System.getenv("DB_URL");
            String user = System.getenv("DB_USER");
            String pass = System.getenv("DB_PASS");

            // If env variables are missing → throw clear error
            if (url == null || user == null || pass == null) {
                throw new RuntimeException("❌ Database environment variables (DB_URL / DB_USER / DB_PASS) are not set.");
            }

            // Log for debugging (safe, no passwords)
            System.out.println("🌐 Connecting to DB: " + url);

            return DriverManager.getConnection(url, user, pass);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("❌ Database connection failed: " + e.getMessage(), e);
        }
    }
}

