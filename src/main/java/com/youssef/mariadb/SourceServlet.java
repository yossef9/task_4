package com.youssef.mariadb;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/sourceServlet")
public class SourceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve user name and password from the form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Store the data in a session
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("password", password);

        // Alternatively, you can use cookies to store the data
        // Cookie usernameCookie = new Cookie("username", username);
        // Cookie passwordCookie = new Cookie("password", password);
        // response.addCookie(usernameCookie);
        // response.addCookie(passwordCookie);

        // Redirect to the target servlet
        response.sendRedirect(request.getContextPath() + "/TargetServlet");
    }
}

