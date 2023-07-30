<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>Update user form</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
			integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
		<script src="./js/index.js"></script>
	</head>

	<body>
		<div class="container">
			<div class="row justify-content-center">
				<h1>Fill required fileds to update the user</h1>
			</div>
			<div class="row">
				<form action="${pageContext.request.contextPath}/user" method="put">
					<div class="mb-3">
						<label for="id" class="form-label">User id:</label>
						<input type="text" class="form-control update-user-input" id="id" name="id" required value="<%= request.getParameter("id")%>">
					</div>

					<div class="mb-3">
						<label for="firstName" class="form-label">New first name:</label>
						<input type="text" class="form-control update-user-input" id="firstName" name="firstName" required value="<%= request.getParameter("firstName")%>">
					</div>

					<div class="mb-3">
						<label for="lastName" class="form-label">New last name:</label>
						<input type="text" class="form-control update-user-input" id="lastName" name="lastName" required value="<%= request.getParameter("lastName")%>">
					</div>

					<div class="mb-3">
						<label for="age" class="form-label">New age:</label>
						<input type="number" class="form-control update-user-input" id="age" name="age" required value="<%= request.getParameter("age")%>">
					</div>

					<button type="submit" class="btn btn-primary" id="update-user-btn">Update user</button>
				</form>
			</div>
			<div class="row align-content-center justify-content-center mt-5">
				<a class="btn btn-outline-primary btn-lg w-25" href="${pageContext.request.contextPath}/user"
					role="button">Back</a>
			</div>
		</div>

		<script>
			document.getElementById("update-user-btn").addEventListener("click", (event) => {
				event.preventDefault();
				updateUser();
			});
		</script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
			crossorigin="anonymous"></script>
	</body>
	</html>