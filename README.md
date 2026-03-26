# OrderManagementSystem
A web-based order management system for cafes and restaurants built using Core Java, Servlets, JDBC, and MySQL

# Order Management System 

A web-based order management system designed for local cafes and newly opened restaurants.

## About
This system allows customers to register, login, browse the menu, and place orders. Admins can manage menu items and track/update order status in real time.

## Features
- User Registration & Login
- Role-based access (Admin / User)
- Admin Dashboard — Add, Update, Delete menu items
- Admin — View all orders & update order status
- User — Browse menu with categories
- User — Place orders & track order status
- Session Management & Authentication

## Tech Stack
| Layer | Technology |
|---|---|
| Frontend | HTML, CSS, JavaScript |
| Backend | Core Java, Advanced Java (Servlets, Filters) |
| Database | MySQL (JDBC) |
| Server | Apache Tomcat 10.1 |
| IDE | Eclipse |

## Database Setup
```sql
CREATE DATABASE cafe_db;
USE cafe_db;
```

## Default Admin Login
- Email: `admin@cafe.com`
- Password: `admin123`

## Project Structure
```
OrderManagementSystem/
├── src/
│   ├── com.cafe.model/       → Entity classes
│   ├── com.cafe.dao/         → Database layer
│   ├── com.cafe.servlet/     → Request handling
│   ├── com.cafe.filter/      → Session filter
│   └── com.cafe.util/        → DB Connection
├── WebContent/
│   ├── index.html            → Login/Register page
│   ├── admin/                → Admin dashboard
│   └── user/                 → Menu & Orders
└── WEB-INF/
    ├── web.xml
    └── lib/                  → JAR files
```
