<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Employee Tasks</title>

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
    width: 80%;
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

</style>

</head>

<body>

<div class="header">
    Employee Management System
</div>

<div class="container">

<h2 class="title">Employee Tasks</h2>

<div class="top-links">
<a href="/dashboard">Dashboard</a>
</div>

<table>

<tr>
<th>Project</th>
<th>Status</th>
<th>Assigned Date</th>
<th>Completed Date</th>
</tr>

<c:forEach var="t" items="${tasks}">
<tr>

<td>${t.project}</td>
<td>${t.status}</td>
<td>${t.assignedDate}</td>
<td>${t.completedDate}</td>

</tr>
</c:forEach>

</table>

</div>

</body>
</html>
