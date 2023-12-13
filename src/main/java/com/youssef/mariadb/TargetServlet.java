package com.youssef.mariadb;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/targetServlet")
public class TargetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve user name and password from the session
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");

        // Alternatively, you can retrieve data from cookies
        // Cookie[] cookies = request.getCookies();
        // String username = null;
        // String password = null;
        // for (Cookie cookie : cookies) {
        //     if (cookie.getName().equals("username")) {
        //         username = cookie.getValue();
        //     } else if (cookie.getName().equals("password")) {
        //         password = cookie.getValue();
        //     }
        // }

        // Perform database connection using MariaDB credentials
        if (isValidConnection(username, password)) {
            response.getWriter().println("Database connection successful!");
        } else {
            // If connection fails, redirect back to the login form
            response.sendRedirect(request.getContextPath() + "/index.html");
        }
    }

    private boolean isValidConnection(String username, String password) {
    	// JDBC URL for MariaDB
        String jdbcUrl = "jdbc:mariadb://localhost:3306/scmdb";

        try {
            // Load the JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            // Establish the connection
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // If no exception is thrown, the connection is successful
            connection.close();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            // Handle any exceptions (e.g., invalid credentials, unreachable host)
            e.printStackTrace();
            return false;
        }
    }
    
}

