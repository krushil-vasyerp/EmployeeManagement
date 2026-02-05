<html>
<head>
<title>Dashboard</title>

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

/* Main Container */
.container {
    width: 500px;
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

/* Navigation Links */
.nav-links a {
    display: block;
    padding: 12px;
    margin: 8px 0;
    background-color: #ecf0f1;
    color: #2c3e50;
    text-decoration: none;
    border-radius: 5px;
    font-weight: bold;
}

.nav-links a:hover {
    background-color: #d0d7de;
}

/* Logout */
.logout {
    text-align: center;
    margin-top: 20px;
}

.logout a {
    background-color: #e74c3c;
    color: white;
    padding: 10px 20px;
    text-decoration: none;
    border-radius: 5px;
}

.logout a:hover {
    background-color: #c0392b;
}

</style>

</head>

<body>

<div class="header">
    Employee Management System
</div>

<div class="container">

<h2 class="title">Dashboard</h2>

<hr>

<div class="nav-links">
<a href="/departments">Departments</a>
<a href="/employees">Employees</a>
<a href="/desks">Desk Management</a>
<a href="/salary">Salary</a>
<a href="/tasks">Tasks</a>
<a href="/users">Users</a>
</div>

<hr>

<div class="logout">
<a href="/logout">Logout</a>
</div>

</div>

</body>
</html>
