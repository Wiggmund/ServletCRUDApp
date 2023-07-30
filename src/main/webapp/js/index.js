const SERVER_APP_NAME = "servletcrud";
const SERVER_ORIGIN = document.location.origin;
const SERVER_USER_ENDPOINT = `${SERVER_ORIGIN}/${SERVER_APP_NAME}/user`;

function deleteUserById() {
	doDisableAllButtons(true);
	const data = { id: this.id };

	fetch(SERVER_USER_ENDPOINT, {
		method: "DELETE",
		headers: {
				'Content-Type': 'application/x-www-form-urlencoded',
		},
		body: createUrlEncodedBody(data),
	}).then(() => {
		window.location.reload();
	});
}

function updateUser() {
	const data = collectDataForUserUpdate();

	console.log(SERVER_USER_ENDPOINT);
	fetch(SERVER_USER_ENDPOINT, {
		method: "PUT",
		headers: {
				'Content-Type': 'application/json',
		},
		body: createUrlEncodedBody(data),
	}).then(res => {
		if (res.status !== 200) {
			window.location.href = `${SERVER_ORIGIN}/${SERVER_APP_NAME}/error.jsp`;
		}
	});
}


function doDisableAllButtons(value) {
	const arg = value === true ? "true" : "false";
	document.querySelectorAll(".btn").forEach(btn => btn.setAttribute("disabled", arg));
}

function createUrlEncodedBody(data) {
	const formBody = [];

	for (let key in data) {
		let encodedKey = encodeURIComponent(key);
		let encodedValue = encodeURIComponent(data[key]);
		formBody.push(encodedKey + "=" + encodedValue);
	}

	return formBody.join("&");
}

function collectDataForUserUpdate() {
	const data = {};

	document.querySelectorAll(".update-user-input").forEach((input) => {
		data[input.name] = input.value;
	});

	return data;
}