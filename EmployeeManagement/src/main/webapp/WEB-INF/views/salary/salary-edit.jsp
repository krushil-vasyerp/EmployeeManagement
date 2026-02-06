<html>
<head>
<title>Edit Salary</title>

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
input[type="number"] {
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

<h2 class="title">Edit Salary</h2>

<form action="/salary/update" method="post">

<input type="hidden" name="employeeId" value="${employeeId}" />

Basic Salary:
<input type="number" name="basicSalary"
value="${salary.basicSalary}"/>

<br><br>

Bonus:
<input type="number" name="bonus"
value="${salary.bonus}"/>

<br><br>

Deduction:
<input type="number" name="deduction"
value="${salary.deduction}"/>

<br><br>

<button type="submit">Update</button>

</form>

</div>

</body>
</html>
