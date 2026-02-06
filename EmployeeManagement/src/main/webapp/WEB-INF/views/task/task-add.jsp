<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Add Task</title>

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
    margin-bottom: 20px;
}

/* Inputs */
input[type="text"],
input[type="date"],
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

<h2 class="title">Add Task</h2>

<form action="/tasks/save" method="post">

Project:
<input type="text" name="project"/><br><br>

Employee:
<select name="employeeId">
<c:forEach items="${employees}" var="emp">
<option value="${emp.id}">
${emp.empCode} - ${emp.name}
</option>
</c:forEach>
</select>

<br><br>

Status:
<select name="status">
<c:forEach items="${statuses}" var="s">
<option value="${s}">${s}</option>
</c:forEach>
</select>

<br><br>

Assigned Date:
<input type="date" name="assignedDate"/><br><br>

Completed Date:
<input type="date" name="completedDate"/><br><br>

<button type="submit">Save</button>

</form>

</div>

</body>
</html>
