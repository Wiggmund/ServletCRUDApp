<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Form</title>
</head>
<body>
<h1>User Form</h1>

<form action="${pageContext.request.contextPath}/userForm.jsp" method="post">
    <label for="firstName">First Name:</label>
    <input type="text" id="firstName" name="firstName" required>
    <br>

    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName" required>
    <br>

    <label for="age">Age:</label>
    <input type="number" id="age" name="age" required>
    <br>

    <input type="submit" name="action" value="Create">
    <input type="submit" name="action" value="Update">
</form>
</body>
</html>
