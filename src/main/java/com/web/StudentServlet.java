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

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the username from the session
        String username = (String) request.getSession().getAttribute("username");

        try {
            // Initialize database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/course_management_database", "root", "");

            // Query to retrieve the ID of the student based on the username
            String studentIdQuery = "SELECT id FROM students WHERE username = ?";
            PreparedStatement studentIdStatement = conn.prepareStatement(studentIdQuery);
            studentIdStatement.setString(1, username);
            ResultSet studentIdResult = studentIdStatement.executeQuery();

            int studentId = -1; // Initialize with a default value
            if (studentIdResult.next()) {
                studentId = studentIdResult.getInt("id");
            }

            String myname = "";
            PreparedStatement studentNameStatement = conn.prepareStatement("SELECT full_name FROM students WHERE username = ?");
            studentNameStatement.setString(1, username);
            ResultSet result = studentNameStatement.executeQuery();
            
            if(result.next()) {
            	myname = result.getString("full_name");
            }
            request.setAttribute("myname", myname);

            // Query to retrieve all course details associated with the student along with teacher's full name
            String takenCoursesQuery = "SELECT c.id, c.name, c.code, c.credit, t.fullname AS teacher " +
                    "FROM courses c " +
                    "INNER JOIN student_courses sc ON c.id = sc.course_id " +
                    "INNER JOIN teachers t ON c.teacher_id = t.id " +
                    "WHERE sc.student_id = ?";

            PreparedStatement takenCoursesStatement = conn.prepareStatement(takenCoursesQuery);
            takenCoursesStatement.setInt(1, studentId);
            ResultSet takenCoursesResult = takenCoursesStatement.executeQuery();

            // Create a list to hold courses taken by the student
            List<Course> takenCourses = new ArrayList<>();

            // Populate the list of taken courses
            while (takenCoursesResult.next()) {
                Course course = new Course();
                course.setId(takenCoursesResult.getInt("id"));
                course.setName(takenCoursesResult.getString("name"));
                course.setCode(takenCoursesResult.getString("code"));
                course.setCredit(takenCoursesResult.getInt("credit"));
                course.setTeacher(takenCoursesResult.getString("teacher"));
                course.setMyId(studentId);
                takenCourses.add(course);
            }

            // Query to retrieve courses that are available but not taken by the student
            String notTakenCoursesQuery = "SELECT c.id, c.name, c.code, c.credit, t.fullname AS teacher " +
            	    "FROM courses c " +
            	    "LEFT JOIN student_courses sc ON c.id = sc.course_id AND sc.student_id = ? " +
            	    "LEFT JOIN teachers t ON c.teacher_id = t.id " +
            	    "WHERE sc.student_id IS NULL";

            PreparedStatement notTakenCoursesStatement = conn.prepareStatement(notTakenCoursesQuery);
            notTakenCoursesStatement.setInt(1, studentId);
            ResultSet notTakenCoursesResult = notTakenCoursesStatement.executeQuery();

            // Create a list to hold courses not taken by the student
            List<Course> notTakenCourses = new ArrayList<>();

            // Populate the list of not taken courses
            while (notTakenCoursesResult.next()) {
                Course course = new Course();
                course.setId(notTakenCoursesResult.getInt("id"));
                course.setName(notTakenCoursesResult.getString("name"));
                course.setCode(notTakenCoursesResult.getString("code"));
                course.setCredit(notTakenCoursesResult.getInt("credit"));
                course.setTeacher(notTakenCoursesResult.getString("teacher"));
                course.setMyId(studentId);
                notTakenCourses.add(course);
            }

            // Set the taken courses and not taken courses lists as request attributes
            request.setAttribute("takenCourses", takenCourses);
            request.setAttribute("notTakenCourses", notTakenCourses);

            // Forward the request to the student.jsp
            request.getRequestDispatcher("student.jsp").forward(request, response);

            // Close database connection
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("student.jsp?error=Database error");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
