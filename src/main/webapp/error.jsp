<%@ page import="org.example.servletcrudapp.exception.ExceptionResponse" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String exception = request.getParameter("exception"); %>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Error Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
            crossorigin="anonymous"
        >
    </head>
    <body>
        <div class="container justify-content-center align-content-center">
            <div class="row mb-2">
                <h1 class="display-1 text-center text-danger">Oops:( Error ${exception.statusCode}</h1>
            </div>
            <div class="row mb-5">
                <p class="lead">${exception.message}</p>
            </div>
            <div class="row justify-content-center align-content-center">
                <a href="${pageContext.request.contextPath}/user" class="btn btn-outline-primary btn-lg w-25" role="button">
                    Click to go Home
                </a>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    </body>
</html>
