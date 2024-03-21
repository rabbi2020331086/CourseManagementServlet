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
import javax.servlet.http.HttpSession;

@WebServlet("/TeacherServlet")
public class TeacherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {    	
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        List<Course> registeredCourses = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/course_management_database", "root", "");

            // Query to retrieve the teacher's ID based on the username
            String teacherIdQuery = "SELECT id FROM teachers WHERE username = ?";
            PreparedStatement teacherIdStatement = conn.prepareStatement(teacherIdQuery);
            teacherIdStatement.setString(1, username);
            ResultSet teacherIdResult = teacherIdStatement.executeQuery();

            int teacherId = -1; // Initialize with a default value
            if (teacherIdResult.next()) {
                teacherId = teacherIdResult.getInt("id");
            } else {
                throw new RuntimeException("Teacher ID not found for username: " + username);
            }
            String myname = "";
            PreparedStatement studentNameStatement = conn.prepareStatement("SELECT fullname FROM teachers WHERE username = ?");
            studentNameStatement.setString(1, username);
            ResultSet result = studentNameStatement.executeQuery();
            
            if(result.next()) {
            	myname = result.getString("fullname");
            }
            request.setAttribute("myname", myname);
            // Query to retrieve the registered courses for the teacher
            String query = "SELECT id, name, code, credit FROM courses WHERE teacher_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, teacherId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getInt("id"));
                course.setName(resultSet.getString("name"));
                course.setCode(resultSet.getString("code"));
                course.setCredit(resultSet.getInt("credit"));
                registeredCourses.add(course);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("teacher.jsp?error=Database error");
            return;
        }

        request.setAttribute("teacherName", username);
        request.setAttribute("registeredCourses", registeredCourses);
        request.getRequestDispatcher("teacher.jsp").forward(request, response);
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
