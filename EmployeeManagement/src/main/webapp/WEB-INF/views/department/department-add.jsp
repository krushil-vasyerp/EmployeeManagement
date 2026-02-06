<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Add Department</title>

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
    width: 450px;
    margin: 60px auto;
    background: white;
    padding: 30px;
    border-radius: 8px;
    box-shadow: 0px 0px 10px rgba(0,0,0,0.2);
}

/* Title */
.title {
    text-align: center;
    margin-bottom: 15px;
}

/* Navigation Links */
.top-links {
    text-align: center;
    margin-bottom: 15px;
}

.top-links a {
    text-decoration: none;
    padding: 8px 15px;
    background-color: #2c3e50;
    color: white;
    border-radius: 5px;
    margin: 0 5px;
    font-size: 14px;
}

.top-links a:hover {
    background-color: #1a252f;
}

/* Inputs */
select {
    width: 100%;
    padding: 8px;
    margin-top: 5px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

/* Button */
button {
    width: 100%;
    padding: 10px;
    background-color: #2c3e50;
    color: white;
    border: none;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
}

button:hover {
    background-color: #1a252f;
}

</style>

</head>

<body>

<div class="header">
    Employee Management System
</div>

<div class="container">

<h2 class="title">Add Department</h2>

<div class="top-links">
<a href="/departments">⬅ Back to Department List</a>
<a href="/dashboard">🏠 Dashboard</a>
</div>

<hr>

<form action="/departments/save" method="post">

Department Type:

<select name="name">

<c:forEach items="${types}" var="type">
<option value="${type}">${type}</option>
</c:forEach>

</select>

<br><br>

<button type="submit">Save</button>

</form>

</div>

</body>
</html>
