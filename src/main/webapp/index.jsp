<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Course Management System</title>
<!-- Include Bootstrap CSS -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<!-- Include Font Awesome for icons -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
<style>
    body {
        background-color: #806C51; /* Same background color as admin page */
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
    .form-control, .btn {
        border-radius: 20px; /* Rounded form input fields and buttons */
    }
    .btn-primary {
        background-color: #007bff; /* Primary button color */
        border-color: #007bff; /* Primary button border color */
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
</style>
</head>
<body>

<div class="container">
    <div class="card">
        <div class="card-body">
            <h1 class="title">Welcome to Course Management System</h1>
            <form action="LoginServlet" method="post">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <div class="form-group">
                    <label for="role">Select Role:</label>
                    <select class="form-control" id="role" name="role" required>
                        <option value="admin">Admin</option>
                        <option value="teacher">Teacher</option>
                        <option value="student">Student</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Login</button>
            </form>
        </div>
    </div>
</div>

<!-- Authentication failed prompt -->
<% if (request.getAttribute("authError") != null && (boolean) request.getAttribute("authError")) { %>
    <div class="alert alert-danger" role="alert">
        Authentication failed. Please check your credentials and try again.
    </div>
<% } %>

<!-- Include Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
