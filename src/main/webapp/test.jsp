<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
</head>
<body>
<h2>User List</h2>
<c:forEach items="${users}" var="user">
    <p>
            ${user.firstName} ${user.lastName} (Age: ${user.age})
        <a href="${request.getContextPath()}/user?delete=${user.id}">Delete</a>
        <a href="${request.getContextPath()}/user?edit=${user.id}">Edit</a>
    </p>
</c:forEach>

<h2>Add User</h2>
<form action="${request.getContextPath()}/user" method="post">
    <label>First Name: <input type="text" name="firstName"></label><br>
    <label>Last Name: <input type="text" name="lastName"></label><br>
    <label>Age: <input type="number" name="age"></label><br>
    <input type="submit" value="Add User">
</form>
</body>
</html>