<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Edit Employee</title>

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
    width: 500px;
    margin: 50px auto;
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
input[type="email"],
input[type="date"],
textarea,
select {
    width: 100%;
    padding: 8px;
    margin-top: 5px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

/* Disabled Field */
input[disabled] {
    background-color: #e9ecef;
}

/* Textarea */
textarea {
    resize: vertical;
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

<h2 class="title">Edit Employee</h2>

<form action="/employees/update" method="post">

<input type="hidden" name="id" value="${employee.id}" />

Emp Code:
<input type="text" value="${employee.empCode}" disabled/><br><br>

Name:
<input type="text" name="name" value="${employee.name}" required/><br><br>

Email:
<input type="email" name="email" value="${employee.email}" required/><br><br>

Contact:
<input type="text" name="contact" value="${employee.contact}"/><br><br>

Emergency Contact:
<input type="text" name="emergencyContact" value="${employee.emergencyContact}"/><br><br>

Address:
<textarea name="address">${employee.address}</textarea><br><br>

DOB:
<input type="date" name="dob" value="${employee.dob}"/><br><br>

Joining Date:
<input type="date" name="joiningDate" value="${employee.joiningDate}" required/><br><br>

Department:
<select name="departmentId">

<c:forEach items="${departments}" var="dept">

<option value="${dept.id}"
<c:if test="${dept.id == employee.department.id}">
selected
</c:if>
>
${dept.name}
</option>

</c:forEach>

</select>

<br><br>

<button type="submit">Update</button>

</form>

</div>

</body>
</html>
