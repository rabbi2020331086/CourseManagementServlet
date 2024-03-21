package com.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddCourseServlet")
public class AddCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddCourseServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseName = request.getParameter("courseName");
        String courseCode = request.getParameter("courseCode");
        int credit = Integer.parseInt(request.getParameter("credit"));
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/course_management_database", "root", "");

            String sql = "INSERT INTO courses (name, code, credit, teacher_id) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, courseName);
            statement.setString(2, courseCode);
            statement.setInt(3, credit);
            statement.setInt(4, teacherId);

            int result = statement.executeUpdate();
            if (result > 0) {
                System.out.println("Course added successfully!");
            }

            response.sendRedirect("admin.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin.jsp?error=Database error");
        }
    }
}
