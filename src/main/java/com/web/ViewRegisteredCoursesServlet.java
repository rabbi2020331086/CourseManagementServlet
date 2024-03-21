package com.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewRegisteredCoursesServlet")
public class ViewRegisteredCoursesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));

        List<Course> courses = new ArrayList<>();

        try {
            // Initialize database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/course_management_database", "root", "");

            // Query to retrieve registered courses for the student
            String sql = "SELECT c.id, c.name, c.code, c.credit, t.fullname " +
                         "FROM courses c " +
                         "INNER JOIN student_courses sc ON c.id = sc.course_id " +
                         "INNER JOIN teachers t ON c.teacher_id = t.id " +
                         "WHERE sc.student_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, studentId);
            ResultSet rs = statement.executeQuery();

            // Process the result set and populate the courses list
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setCode(rs.getString("code"));
                course.setCredit(rs.getInt("credit"));
                course.setTeacher(rs.getString("fullname"));
                courses.add(course);
            }

            // Set the courses list as a request attribute
            request.setAttribute("courses", courses);

            // Forward the request to the JSP for displaying registered courses
            request.getRequestDispatcher("view_registered_courses.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("view_registered_courses.jsp?error=Database error");
        }
    }
}
