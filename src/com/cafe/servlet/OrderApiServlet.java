package com.cafe.servlet;

import com.cafe.dao.OrderDAO;
import com.cafe.model.Order;
import com.cafe.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.List;

@WebServlet("/api/orders/*")
public class OrderApiServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        OrderDAO orderDAO = new OrderDAO();
        List<Order> orders;

        String pathInfo = request.getPathInfo();

        if (pathInfo != null && pathInfo.equals("/user")) {
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");
            orders = orderDAO.getOrdersByUserId(user.getId());
        } else {
            orders = orderDAO.getAllOrders();
        }

        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            json.append("{")
                .append("\"id\":").append(order.getId()).append(",")
                .append("\"userId\":").append(order.getUserId()).append(",")
                .append("\"totalAmount\":").append(order.getTotalAmount()).append(",")
                .append("\"status\":\"").append(order.getStatus()).append("\",")
                .append("\"createdAt\":\"").append(order.getCreatedAt()).append("\"")
                .append("}");
            if (i < orders.size() - 1) json.append(",");
        }
        json.append("]");

        response.getWriter().write(json.toString());
    }
}