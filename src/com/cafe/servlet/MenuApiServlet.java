package com.cafe.servlet;

import com.cafe.dao.MenuDAO;
import com.cafe.model.MenuItem;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.List;

@WebServlet("/api/menu")
public class MenuApiServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        MenuDAO menuDAO = new MenuDAO();
        List<MenuItem> items = menuDAO.getAllItems();

        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            json.append("{")
                .append("\"id\":").append(item.getId()).append(",")
                .append("\"name\":\"").append(item.getName()).append("\",")
                .append("\"description\":\"").append(item.getDescription() != null ? item.getDescription() : "").append("\",")
                .append("\"price\":").append(item.getPrice()).append(",")
                .append("\"category\":\"").append(item.getCategory()).append("\",")
                .append("\"available\":").append(item.isAvailable()).append(",")
                .append("\"imageUrl\":\"").append(item.getImageUrl() != null ? item.getImageUrl() : "").append("\"")
                .append("}");
            if (i < items.size() - 1) json.append(",");
        }
        json.append("]");

        response.getWriter().write(json.toString());
    }
}