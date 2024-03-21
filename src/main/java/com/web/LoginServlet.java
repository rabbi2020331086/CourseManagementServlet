package com.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    String role = request.getParameter("role");

	    // Authenticate user
	    boolean authenticated = authenticateUser(username, password, role);

	    if (authenticated) {
	    	
	        // Redirect based on user role
	        if ("admin".equals(role)) {
	            response.sendRedirect("admin.jsp");
	        } else if ("teacher".equals(role)) {
	            request.getSession().setAttribute("username", username); 
	            response.sendRedirect("TeacherServlet");
	        } else if ("student".equals(role)) {
	            request.getSession().setAttribute("username", username); 
	            response.sendRedirect("StudentServlet");
	        }
	    } else {
	        request.setAttribute("authError", true);
	        request.getRequestDispatcher("index.jsp").forward(request, response);
	    }
	}


	private boolean authenticateUser(String username, String password, String role) {
	    boolean authenticated = false;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        // Load the MySQL JDBC driver class
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        // Establish database connection
	        String db_url = "jdbc:mysql://localhost:3306/course_management_database";
	        String db_user = "root";
	        String db_password = "";
	        conn = DriverManager.getConnection(db_url, db_user, db_password);
	        System.out.println("Database connection established successfully!");

	        // Select the table based on the role
	        String table = "";
	        switch (role) {
	            case "student":
	                table = "students";
	                break;
	            case "teacher":
	                table = "teachers";
	                break;
	            case "admin":
	                table = "admins";
	                break;
	            default:
	                // Invalid role
	                return false;
	        }

	        // Query to check user credentials
	        String query = "SELECT * FROM " + table + " WHERE username=? AND password=?";
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, username);
	        pstmt.setString(2, password);
	        rs = pstmt.executeQuery();

	        // Check if any row is returned
	        if (rs.next()) {
	            authenticated = true;
	        }
	    } catch (ClassNotFoundException e) {
	        System.out.println("MySQL JDBC driver not found!");
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace(); // Handle or log the exception appropriately
	    } finally {
	        // Close JDBC objects
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (pstmt != null) {
	                pstmt.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); // Handle or log the exception appropriately
	        }
	    }

	    return authenticated;
	}



}
