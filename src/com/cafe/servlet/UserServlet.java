package com.cafe.servlet;

import com.cafe.dao.MenuDAO;
import com.cafe.dao.OrderDAO;
import com.cafe.model.Order;
import com.cafe.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        MenuDAO menuDAO = new MenuDAO();
        OrderDAO orderDAO = new OrderDAO();
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if ("getMenu".equals(action)) {
            request.setAttribute("items", menuDAO.getAllItems());
            request.getRequestDispatcher("/user/menu.html").forward(request, response);
        } else if ("getOrders".equals(action)) {
            request.setAttribute("orders", orderDAO.getOrdersByUserId(user.getId()));
            request.getRequestDispatcher("/user/orders.html").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        OrderDAO orderDAO = new OrderDAO();
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if ("placeOrder".equals(action)) {
            Order order = new Order();
            order.setUserId(user.getId());
            order.setTotalAmount(Double.parseDouble(request.getParameter("totalAmount")));
            order.setStatus("pending");
            orderDAO.placeOrder(order);
            response.sendRedirect(request.getContextPath() + "/user/orders.html?success=1");
        }
    }
}