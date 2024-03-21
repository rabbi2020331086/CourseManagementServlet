<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add New Course</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Include Font Awesome for icons -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #806C51; /* Light gray background */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            width: 100%;
            max-width: 600px; /* Limit card width */
            padding: 20px; /* Adjust padding */
        }
        .card {
            border: none; /* Remove card border */
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1); /* Add subtle shadow */
            background-color: #fff; /* Card background color */
            border-radius: 20px; /* Rounded corners */
        }
        .card-body {
            padding: 30px; /* Increased padding */
        }
        .form-control {
            border-radius: 20px; /* Rounded form input fields */
            border-color: #ced4da; /* Light gray border color */
        }
        .btn-primary {
            background-color: #007bff; /* Primary button color */
            border-color: #007bff; /* Primary button border color */
            border-radius: 20px; /* Rounded button */
        }
        .btn-primary:hover {
            background-color: #0056b3; /* Darker hover color */
            border-color: #0056b3; /* Darker hover border color */
        }
        .title {
            text-align: center; /* Center align title */
            color: #007bff; /* Primary color for title */
            margin-bottom: 40px; /* Increased bottom margin */
        }
        .subtitle {
            text-align: center; /* Center align subtitle */
            color: #6c757d; /* Gray color for subtitle */
            margin-bottom: 30px; /* Increased bottom margin */
        }
        .form-group label {
            font-size: 0.9rem; /* Adjusted label font size */
            color: #495057; /* Dark color for labels */
        }
    </style>
</head>
<body>

<div class="container">
    <div class="card">
    
            <form action="index.jsp" method="post" class="text-center">
                <button type="submit" class="btn btn-danger mt-3">Logout</button>
            </form>
        <div class="card-body">
            <h1 class="title">Admin Dashboard</h1>
            <h2>Add New Course</h2>
            
            <% 
            String message = request.getParameter("message");
            if(message != null && !message.isEmpty()) {
            %>
            <script>
                // Display browser notification
                Notification.requestPermission().then(function(result) {
                    if (result === 'granted') {
                        new Notification('<%= message %>', { icon: 'path/to/icon.png' });
                    }
                });
            </script>
            <% } %>
            
            <form action="AddCourseServlet" method="post">
                <div class="form-group">
                    <label for="courseName">Course Name:</label>
                    <input type="text" class="form-control" id="courseName" name="courseName" required>
                </div>
                <div class="form-group">
                    <label for="courseCode">Course Code:</label>
                    <input type="text" class="form-control" id="courseCode" name="courseCode" required>
                </div>
                <div class="form-group">
                    <label for="credit">Credit:</label>
                    <input type="number" class="form-control" id="credit" name="credit" required>
                </div>
                <div class="form-group">
                    <label for="teacherId">Select Teacher:</label>
                    <select class="form-control" id="teacherId" name="teacherId" required>
                        <% 
                        String DB_URL = "jdbc:mysql://localhost:3306/course_management_database";
                        String USER = "root";
                        String PASS = "";
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                            Statement stmt = conn.createStatement();
                            String sql = "SELECT id, fullname FROM teachers";
                            ResultSet rs = stmt.executeQuery(sql);
                            while (rs.next()) {
                        %>
                                <option value="<%= rs.getInt("id") %>"><%= rs.getString("fullname") %></option>
                        <%  
                            }
                            conn.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        %>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Add Course</button>
            </form>
            
        </div>
    </div>
</div>

<!-- Include Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
