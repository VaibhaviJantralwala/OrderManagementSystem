package com.cafe.servlet;

import com.cafe.dao.UserDAO;
import com.cafe.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByEmailAndPassword(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());

            if (user.getRole().equals("admin")) {
                response.sendRedirect(request.getContextPath() + "/admin/dashboard.html");
            } else {
                response.sendRedirect(request.getContextPath() + "/user/menu.html");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/index.html?error=1");
        }
    }
}