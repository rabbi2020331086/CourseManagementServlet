<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Teacher Dashboard - Registered Courses</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <style>
        body {
            background-color: #f4f7f6; /* Lighter background to ease the visual experience */
            margin-top: 20px;
        }
        .container {
            max-width: 1200px; /* Adjust container width for better alignment */
        }
        .card {
            border-radius: .25rem; /* Rounded corners */
            border: 0; /* Remove border */
            box-shadow: 0 0.125rem 0.25rem rgba(0,0,0,0.075); /* Subtle shadow */
        }
        .card-header {
            background-color: #0056b3; /* Darker shade for header */
            font-size: 20px; /* Increase font size for better readability */
            color: #fff; /* White color for text */
        }
        .btn-info {
            color: #fff; /* White text color */
            background-color: #117a8b; /* Custom button color */
            border-color: #10707f; /* Border color slightly darker than button color */
        }
        .btn-info:hover {
            background-color: #0e6677; /* Darken button on hover for visual feedback */
            border-color: #0d5e69;
        }
        .table .thead-dark th {
            background-color: #343a40; /* Darker table header for contrast */
            border-color: #454d55; /* Matching border color */
        }
        h2, h4 {
            color: #333; /* Dark gray color for text */
        }
    </style>
</head>
<body>

<div class="container">

        <form action="index.jsp" method="post" class="logout-btn">
            <button type="submit" class="btn btn-danger">Logout</button>
        </form>
    <div class="mb-4">
        <h2>Teacher Dashboard</h2>
        <div class="username">
            <h4>Name: <span class="text-primary">${myname}</span></h4>
        </div>
    </div>
    <div class="card">
    
        <div class="card-header">
            Your Courses
        </div>
        <div class="card-body">
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th>Course Code</th>
                        <th>Course Name</th>
                        <th>Credit</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="course" items="${registeredCourses}">
                        <tr>
                            <td>${course.code}</td>
                            <td>${course.name}</td>
                            <td>${course.credit}</td>
                            <td>
                                <form action="StudentListServlet" method="post">
                                    <input type="hidden" name="courseId" value="${course.id}">
                                    <button type="submit" class="btn btn-info"><i class="fas fa-users"></i> View Students</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
