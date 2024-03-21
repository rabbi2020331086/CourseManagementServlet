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

@WebServlet("/RegisterCourseServlet")
public class RegisterCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the course ID from the request
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        String username = request.getParameter("username");
        // Retrieve the student's ID (you might need to retrieve it from the session)
        System.out.println(studentId+" dfbd ");
        try {
            // Initialize database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/course_management_database", "root", "");

            // Insert the registration record into the student_courses table
            String registerCourseQuery = "INSERT INTO student_courses (student_id, course_id) VALUES (?, ?)";
            PreparedStatement registerCourseStatement = conn.prepareStatement(registerCourseQuery);
            registerCourseStatement.setInt(1, studentId);
            registerCourseStatement.setInt(2, courseId);
            int rowsInserted = registerCourseStatement.executeUpdate();

            if (rowsInserted > 0) {
	            request.getSession().setAttribute("username", username); 
                response.sendRedirect("StudentServlet");
            } else {
                // Registration failed
                response.sendRedirect("student.jsp?error=Course registration failed");
            }

            // Close database connection
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("student.jsp?error=Database error");
        }
    }

    // Method to retrieve the student's ID from the session (you need to implement this)
    private int getStudentIdFromSession(HttpServletRequest request) {
        Object studentIdObj = request.getSession().getAttribute("studentId");
        if (studentIdObj != null && studentIdObj instanceof Integer) {
            return (int) studentIdObj; 
        } else {
            return 0; 
        }
    }

}
