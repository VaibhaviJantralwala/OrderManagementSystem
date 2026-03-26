package com.cafe.servlet;

import com.cafe.dao.MenuDAO;
import com.cafe.dao.OrderDAO;
import com.cafe.model.MenuItem;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        MenuDAO menuDAO = new MenuDAO();
        OrderDAO orderDAO = new OrderDAO();

        switch (action) {
            case "addItem": {
                MenuItem item = new MenuItem();
                item.setName(request.getParameter("name"));
                item.setDescription(request.getParameter("description"));
                item.setPrice(Double.parseDouble(request.getParameter("price")));
                item.setCategory(request.getParameter("category"));
                item.setAvailable(true);
                item.setImageUrl(request.getParameter("imageUrl"));
                menuDAO.addItem(item);
                response.sendRedirect(request.getContextPath() + "/admin/dashboard.html?success=1");
                break;
            }
            case "updateItem": {
                MenuItem item = new MenuItem();
                item.setId(Integer.parseInt(request.getParameter("id")));
                item.setName(request.getParameter("name"));
                item.setDescription(request.getParameter("description"));
                item.setPrice(Double.parseDouble(request.getParameter("price")));
                item.setCategory(request.getParameter("category"));
                item.setAvailable(Boolean.parseBoolean(request.getParameter("isAvailable")));
                item.setImageUrl(request.getParameter("imageUrl"));
                menuDAO.updateItem(item);
                response.sendRedirect(request.getContextPath() + "/admin/dashboard.html?success=2");
                break;
            }
            case "deleteItem": {
                int id = Integer.parseInt(request.getParameter("id"));
                menuDAO.deleteItem(id);
                response.sendRedirect(request.getContextPath() + "/admin/dashboard.html?success=3");
                break;
            }
            case "updateStatus": {
                int orderId = Integer.parseInt(request.getParameter("orderId"));
                String status = request.getParameter("status");
                orderDAO.updateOrderStatus(orderId, status);
                response.sendRedirect(request.getContextPath() + "/admin/dashboard.html?success=4");
                break;
            }
            default:
                response.sendRedirect(request.getContextPath() + "/admin/dashboard.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        MenuDAO menuDAO = new MenuDAO();
        OrderDAO orderDAO = new OrderDAO();

        if ("getItems".equals(action)) {
            request.setAttribute("items", menuDAO.getAllItems());
            request.getRequestDispatcher("/admin/dashboard.html").forward(request, response);
        } else if ("getOrders".equals(action)) {
            request.setAttribute("orders", orderDAO.getAllOrders());
            request.getRequestDispatcher("/admin/dashboard.html").forward(request, response);
        }
    }
}