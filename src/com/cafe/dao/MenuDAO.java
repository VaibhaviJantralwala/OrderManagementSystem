package com.cafe.dao;

import com.cafe.model.MenuItem;
import com.cafe.util.DBConnection;
import java.sql.*;
import java.util.*;

public class MenuDAO {

    public List<MenuItem> getAllItems() {
        List<MenuItem> list = new ArrayList<>();
        String query = "SELECT * FROM menu_items WHERE is_available = true";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MenuItem item = new MenuItem();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getDouble("price"));
                item.setCategory(rs.getString("category"));
                item.setAvailable(rs.getBoolean("is_available"));
                item.setImageUrl(rs.getString("image_url"));
                list.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addItem(MenuItem item) {
        String query = "INSERT INTO menu_items (name, description, price, category, is_available, image_url) VALUES (?,?,?,?,?,?)";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, item.getName());
            ps.setString(2, item.getDescription());
            ps.setDouble(3, item.getPrice());
            ps.setString(4, item.getCategory());
            ps.setBoolean(5, item.isAvailable());
            ps.setString(6, item.getImageUrl());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateItem(MenuItem item) {
        String query = "UPDATE menu_items SET name=?, description=?, price=?, category=?, is_available=?, image_url=? WHERE id=?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, item.getName());
            ps.setString(2, item.getDescription());
            ps.setDouble(3, item.getPrice());
            ps.setString(4, item.getCategory());
            ps.setBoolean(5, item.isAvailable());
            ps.setString(6, item.getImageUrl());
            ps.setInt(7, item.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteItem(int id) {
        String query = "DELETE FROM menu_items WHERE id=?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public MenuItem getItemById(int id) {
        MenuItem item = null;
        String query = "SELECT * FROM menu_items WHERE id=?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                item = new MenuItem();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getDouble("price"));
                item.setCategory(rs.getString("category"));
                item.setAvailable(rs.getBoolean("is_available"));
                item.setImageUrl(rs.getString("image_url"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
}