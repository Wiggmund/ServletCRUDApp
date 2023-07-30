<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>400 Exception Page</title>
</head>
<body>
<h1>Ошибка! Такого юзера нет</h1>
<h1>
    Error <%= request.getAttribute("javax.servlet.error.status_code") %>
</h1>
<p>
    <%= request.getAttribute("errorMessage") %>
</p>
<a
        href="http://localhost:8081/servletcrud/user">
    <button>Click to go Home</button>
</a>
</body>
</html>
