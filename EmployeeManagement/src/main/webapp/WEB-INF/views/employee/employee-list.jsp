<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Employee List</title>

<style>

body {
    margin: 0;
    font-family: Arial, Helvetica, sans-serif;
    background-color: #f4f6f9;
}

/* Header */
.header {
    background-color: #2c3e50;
    color: white;
    padding: 15px;
    text-align: center;
    font-size: 24px;
    font-weight: bold;
}

/* Container */
.container {
    width: 95%;
    margin: 30px auto;
    background: white;
    padding: 25px;
    border-radius: 8px;
    box-shadow: 0px 0px 10px rgba(0,0,0,0.2);
}

/* Title */
.title {
    margin-bottom: 10px;
}

/* Top Links */
.top-links a {
    text-decoration: none;
    padding: 8px 15px;
    background-color: #2c3e50;
    color: white;
    border-radius: 5px;
    margin-right: 10px;
    font-size: 14px;
}

.top-links a:hover {
    background-color: #1a252f;
}

/* Table */
table {
    border-collapse: collapse;
    width: 100%;
    margin-top: 15px;
}

th, td {
    border: 1px solid #ccc;
    padding: 8px;
    text-align: left;
}

th {
    background-color: #ecf0f1;
}

/* Action Buttons */
.action-btn {
    margin-right: 5px;
    text-decoration: none;
    padding: 5px 10px;
    border-radius: 4px;
    font-size: 13px;
}

.action-btn[href*="edit"] {
    background-color: #3498db;
    color: white;
}

.action-btn[href*="delete"] {
    background-color: #e74c3c;
    color: white;
}

.action-btn:hover {
    opacity: 0.8;
}

</style>

</head>

<body>

<div class="header">
    Employee Management System
</div>

<div class="container">

<h2 class="title">Employee Management</h2>

<hr>

<div class="top-links">
<a href="/employees/add">➕ Add Employee</a>
<a href="/dashboard">⬅ Back to Dashboard</a>
</div>

<hr>

<table>

<tr>
<th>ID</th>
<th>Emp Code</th>
<th>Name</th>
<th>Email</th>
<th>Contact</th>
<th>Emergency Contact</th>
<th>Address</th>
<th>DOB</th>
<th>Joining Date</th>
<th>Department</th>
<th>Actions</th>
</tr>

<c:forEach var="emp" items="${employees}">

<tr>

<td>${emp.id}</td>
<td>${emp.empCode}</td>
<td>${emp.name}</td>
<td>${emp.email}</td>
<td>${emp.contact}</td>
<td>${emp.emergencyContact}</td>
<td>${emp.address}</td>
<td>${emp.dob}</td>
<td>${emp.joiningDate}</td>

<td>
<c:choose>
<c:when test="${emp.department != null}">
${emp.department.name}
</c:when>
<c:otherwise>
N/A
</c:otherwise>
</c:choose>
</td>

<td>

<a class="action-btn"
   href="/employees/edit/${emp.id}">
   ✏ Edit
</a>

<a class="action-btn"
   href="/employees/delete/${emp.id}"
   onclick="return confirm('Are you sure to delete this employee?')">
   ❌ Delete
</a>

</td>

</tr>

</c:forEach>

</table>

</div>

</body>
</html>
