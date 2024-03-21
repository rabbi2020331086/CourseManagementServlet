package com.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.web.Student;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/StudentListServlet")
public class StudentListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {    	
        // Get the courseId parameter from the request
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        List<Student> students = new ArrayList<>();

        try {
            // Establish database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/course_management_database", "root", "");

            // Query to retrieve student IDs enrolled in the selected course
            String query = "SELECT student_id FROM student_courses WHERE course_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, courseId);
            ResultSet resultSet = statement.executeQuery();

            // Iterate through the result set and fetch student details for each student ID
            while (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                // Query to fetch student details using student ID
                String studentQuery = "SELECT id, full_name FROM students WHERE id = ?";
                PreparedStatement studentStatement = conn.prepareStatement(studentQuery);
                studentStatement.setInt(1, studentId);
                ResultSet studentResult = studentStatement.executeQuery();
                // If student found, add it to the list of students
                if (studentResult.next()) {
                    Student student = new Student();
                    student.setName(studentResult.getString("full_name"));
                    students.add(student);
                }
                studentResult.close();
                studentStatement.close();
            }


            // Close the database connection
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("teacher.jsp?error=Database error");
            return;
        }

        // Set the list of students as a request attribute
        request.setAttribute("students", students);

        // Forward the request to a JSP page to display the list of students
        request.getRequestDispatcher("student_list.jsp").forward(request, response);
    }
}
