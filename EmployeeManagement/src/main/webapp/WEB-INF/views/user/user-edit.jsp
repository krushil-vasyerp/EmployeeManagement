<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Edit User</title>

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
input[type="password"],
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

<h2 class="title">Edit User</h2>

<form action="/users/update" method="post">

<input type="hidden" name="userId" value="${user.id}"/>

Username:
<input type="text" name="username"
value="${user.username}"/>

<br><br>

Password:
<input type="password" name="password"
placeholder="Enter new password"/>

<br><br>

Role:
<select name="role">
<c:forEach items="${roles}" var="r">
<option value="${r}"
<c:if test="${r == user.role}">selected</c:if>
>${r}</option>
</c:forEach>
</select>

<br><br>

Status:
<select name="status">
<c:forEach items="${statuses}" var="s">
<option value="${s}"
<c:if test="${s == user.status}">selected</c:if>
>${s}</option>
</c:forEach>
</select>

<br><br>

<button type="submit">Update</button>

</form>

</div>

</body>
</html>
