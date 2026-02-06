<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Edit Desk</title>

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
select {
    width: 100%;
    padding: 8px;
    margin-top: 5px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

/* Readonly Field */
input[readonly] {
    background-color: #e9ecef;
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

<h2 class="title">Edit Desk</h2>

<form action="/desks/update" method="post">

<input type="hidden" name="id" value="${desk.id}" />

Desk Code:
<input type="text" name="deskCode" value="${desk.deskCode}" readonly/><br><br>

Desk Type:
<select name="deskType">

<c:forEach items="${types}" var="type">
<option value="${type}"
<c:if test="${type == desk.deskType}">selected</c:if>
>
${type}
</option>
</c:forEach>

</select>

<br><br>

<button type="submit">Update</button>

</form>

</div>

</body>
</html>
