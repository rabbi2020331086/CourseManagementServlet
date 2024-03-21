<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register Course</title>
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
    </style>
</head>
<body>

<div class="container">
    <div class="card">
        <div class="card-body">
            <h1 class="title">Register for a New Course</h1>
            <form action="RegisterCourseServlet" method="post">
                <div class="form-group">
                    <label for="studentId">Student ID:</label>
                    <input type="text" class="form-control" id="studentId" name="studentId" required>
                </div>
                <div class="form-group">
                    <label for="courseId">Course ID:</label>
                    <input type="text" class="form-control" id="courseId" name="courseId" required>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Register Course</button>
            </form>
            <%-- Success/Error messages --%>
            <% if ("true".equals(request.getParameter("success"))) { %>
                <div class="alert alert-success mt-3" role="alert">
                    Course registered successfully!
                </div>
            <% } %>
            <% if ("true".equals(request.getParameter("error"))) { %>
                <div class="alert alert-danger mt-3" role="alert">
                    Error registering course. Please try again.
                </div>
            <% } %>
        </div>
    </div>
</div>

<!-- Include Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
