<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="usersList" scope="request" type="java.util.List"/>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
    <script src="./js/index.js"></script>
</head>
<body>
    <div class="container p-3">
        <div class="row mb-3">
            <h2 class="text-center">List of users:</h2>
        </div>
        <div class="row mb-5 justify-content-sm-center">
            <a class="btn btn-outline-primary btn-lg w-25" href="${pageContext.request.contextPath}/userCreate.jsp" role="button">Create a new user</a>
        </div>
        <div class="row mb-3">
            <table class="table table-striped">
                <thead>
                <tr class="table-primary">
                    <th>Id</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Age</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${usersList}">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.age}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/userUpdate.jsp">
                                    <button class="btn btn-primary">Edit</button>
                                </a>
                                <button id="${user.id}" class="btn btn-danger delete-user-btn">Delete</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="row">
            <div class="col"></div>
            <div class="col"></div>
        </div>
    </div>

    <script>
        const btnClass = ".delete-user-btn";
        document.querySelectorAll(btnClass).forEach(btn => btn.addEventListener("click", deleteUserById))
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</body>
</html>
