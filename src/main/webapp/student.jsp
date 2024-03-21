<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registered Courses</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <style>
        body {
            padding-top: 20px;
            background-color: #f8f9fa; /* Light gray background */
        }
        .container {
            background-color: #fff; /* White background for the content */
            padding: 20px;
            border-radius: 5px; /* Rounded corners for the container */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Subtle shadow for depth */
        }
        .title {
            font-size: 24px; /* Larger title font size */
            color: #007bff; /* Bootstrap primary color */
            margin-bottom: 30px; /* More space below the title */
        }
        .username {
            font-size: 18px; /* Slightly larger font size for the username */
            text-align: left; /* Align to the left */
            margin-bottom: 20px; /* Space below the username */
            color: #333; /* Darker font color for better readability */
        }
        .table {
            margin-top: 20px; /* Space above the table */
        }
        .btn-primary {
            width: 100%; /* Full width buttons */
            margin-top: 5px; /* Space above the button */
        }
        th {
            background-color: #007bff; /* Bootstrap primary color */
            color: #fff; /* White text for contrast */
        }
    </style>
</head>
<body>

<div class="container">

        <form action="index.jsp" method="post" class="logout-btn">
            <button type="submit" class="btn btn-danger">Logout</button>
        </form>
    <h1 class="title">Student Dashboard</h1>
    
    <div class="username">
        <p><strong>Name:</strong> ${myname}</p>
    </div>
    
    <div class="card mb-4">
        <div class="card-header">
            <h3>Registered Courses</h3>
        </div>
        <div class="card-body">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Course Code</th>
                        <th>Course Name</th>
                        <th>Credit</th>
                        <th>Teacher</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="course" items="${takenCourses}">
                        <tr>
                            <td>${course.code}</td>
                            <td>${course.name}</td>
                            <td>${course.credit}</td>
                            <td>${course.teacher}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    
    <div class="card">
        <div class="card-header">
            <h3>Available Courses</h3>
        </div>
        <div class="card-body">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Course Code</th>
                        <th>Course Name</th>
                        <th>Credit</th>
                        <th>Teacher</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="course" items="${notTakenCourses}">
                        <tr>
                            <td>${course.code}</td>
                            <td>${course.name}</td>
                            <td>${course.credit}</td>
                            <td>${course.teacher}</td>
                            <td>
                                <form action="RegisterCourseServlet" method="post">
                                    <input type="hidden" name="courseId" value="${course.id}">
                                    <input type="hidden" name="studentId" value="${course.myId}">
                                    <input type="hidden" name="username" value="${username}">
                                    <button type="submit" class="btn btn-primary">Register</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    
</div>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
