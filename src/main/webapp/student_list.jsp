<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student List</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f4f4f4; /* Admin pages often have light backgrounds */
        }
        .container {
            margin-top: 40px; /* Provide sufficient spacing from the top */
        }
        .card {
            box-shadow: 0 2px 4px rgba(0,0,0,.1); /* Subtle shadow for depth */
            border: none; /* Remove default border to keep it clean and modern */
            display: flex;
            justify-content: space-between; /* Align children (header and button) to the sides */
        }
        .card-header {
            background-color: #007bff; /* Primary color for header */
            color: #ffffff; /* White text for contrast */
            font-size: 20px; /* Increase font size for better visibility */
        }
        .card-body {
            padding: 20px; /* Ample padding for aesthetic spacing */
        }
        .table {
            margin-top: 20px; /* Add space above the table for layout balance */
        }
        th, td {
            text-align: left; /* Align table headers and cells for readability */
        }
        .logout-btn {
            margin-top: auto; /* Align the button to the bottom of the card header */
            margin-bottom: 20px; /* Add some margin to separate from the card content */
        }
    </style>
</head>
<body>

<div class="container">

        <form action="index.jsp" method="post" class="logout-btn">
            <button type="submit" class="btn btn-danger">Logout</button>
        </form>
    <div class="card">
        <div class="card-header">
            Student List
        </div>
        <div class="card-body">
            <h5 class="card-title">Students Enrolled in the Course</h5>
            <table class="table table-striped table-hover"> <!-- Added hover for better UX -->
                <thead class="thead-dark"> <!-- Dark theme for the table header for contrast -->
                    <tr>
                        <th>Name</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="student" items="${students}">
                        <tr>
                            <td>${student.name}</td>
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
