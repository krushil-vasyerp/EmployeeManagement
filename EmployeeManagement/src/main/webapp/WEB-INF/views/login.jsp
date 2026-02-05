<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Login</title>

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

/* Center Container */
.container {
    width: 350px;
    margin: 80px auto;
    background: white;
    padding: 30px;
    border-radius: 8px;
    box-shadow: 0px 0px 10px rgba(0,0,0,0.2);
}

/* Form Title */
.form-title {
    text-align: center;
    margin-bottom: 20px;
}

/* Input Fields */
input[type="text"],
input[type="password"] {
    width: 100%;
    padding: 10px;
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

/* Error */
.error {
    color: red;
    text-align: center;
    margin-top: 15px;
}

</style>

</head>

<body>

<div class="header">
    Employee Management System
</div>

<div class="container">

<h2 class="form-title">Employee Management Login</h2>

<form action="/login" method="post">

Username :
<input type="text" name="username" required/>

<br><br>

Password :
<input type="password" name="password" required/>

<br><br>

<button type="submit">Login</button>

</form>

<p class="error">${error}</p>

</div>

</body>
</html>
