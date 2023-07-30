<%@ page import="org.example.servletcrudapp.exception.ExceptionResponse" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String exception = request.getParameter("exception"); %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 30px;
            background-color: #ffffff;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #ff0000;
            text-align: center;
        }
        p {
            font-size: 18px;
            text-align: center;
        }
        .back-btn {
            display: block;
            margin-top: 20px;
            text-align: center;
        }

    </style>
</head>
<body>
    <div class = "container">
    <h1>
        Oops :( Error
        ${exception.statusCode}
    </h1>
        <p>
            ${exception.message}
        </p>
    <a href="http://localhost:8081/servletcrud/user" class="back-btn">
        <button>Click to go Home</button>
    </a>
</div>
</body>
</html>
