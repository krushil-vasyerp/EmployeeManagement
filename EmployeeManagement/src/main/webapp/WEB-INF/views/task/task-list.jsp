<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Task Management</title>

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
    width: 90%;
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

/* Action Links */
.action-link {
    text-decoration: none;
    padding: 5px 10px;
    border-radius: 4px;
    font-size: 13px;
    margin-right: 5px;
}

.edit {
    background-color: #3498db;
    color: white;
}

.delete {
    background-color: #e74c3c;
    color: white;
}

.action-link:hover {
    opacity: 0.8;
}

</style>

</head>

<body>

<div class="header">
    Employee Management System
</div>

<div class="container">

<h2 class="title">Task Management</h2>

<div class="top-links">
<a href="/tasks/add">Add Task</a>
<a href="/dashboard">Dashboard</a>
</div>

<table>

<tr>
<th>ID</th>
<th>Project</th>
<th>Status</th>
<th>Employee</th>
<th>Assigned Date</th>
<th>Completed Date</th>
<th>Action</th>
</tr>

<c:forEach var="t" items="${tasks}">
<tr>

<td>${t.id}</td>
<td>${t.project}</td>
<td>${t.status}</td>
<td>${t.employee.name}</td>
<td>${t.assignedDate}</td>
<td>${t.completedDate}</td>

<td>
<a class="action-link edit" href="/tasks/edit/${t.id}">Edit</a>
<a class="action-link delete" href="/tasks/delete/${t.id}">Delete</a>
</td>

</tr>
</c:forEach>

</table>

</div>

</body>
</html>
