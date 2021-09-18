<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Users Page</title>

<!-- adding Bootstrap CSS CDN -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" />
<style type="text/css">
table tr th, td {
	text-transform: capitalize;
}

#regForm label {
	text-transform: uppercase;
}
</style>
</head>

<body>
	<!-- 	table starts -->
	<div class="container-fluid text-center">

		<div class="bg-primary">
			<h1 class="text-white">${msg}</h1>
		</div>

		<table class="table">
			<thead>
				<tr>
					<th scope="col">Id</th>
					<th scope="col">firstName</th>
					<th scope="col">lastName</th>
					<th scope="col">email</th>
					<th scope="col">password</th>
					<th scope="col">phNo</th>
					<th scope="col">dob</th>
					<th scope="col">gender</th>
					<th scope="col">country</th>
					<th scope="col">state</th>
					<th scope="col">city</th>
					<th scope="col" colspan="2">Operations</th>

				</tr>
			</thead>
			<tbody>

				<c:forEach var="user" items="${users}">

					<tr>
						<td><input id="userId" type="hidden" value="${user.userId}"></input></td>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<td>${user.email}</td>
						<td>${user.password}</td>
						<td>${user.phNo}</td>
						<td>${user.dob}</td>
						<td>${user.gender}</td>
						<td>${user.country}</td>
						<td>${user.state}</td>
						<td>${user.city}</td>
						<td><a data-toggle="modal" data-target="#editUserModal"
							class="btn btn-primary" id="edit">Edit</a></td>
						<td><a href="deleteuser?userId=${user.userId}"
							class="btn btn-danger" onclick="return confirmDelete()">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="container">
			<a href="/user-management-app/user/loadRegForm"
				class="btn btn-primary">Registration Page</a>
		</div>

	</div>
	<!-- 	table ends -->

	<!-- edit user modal starts -->
	<!-- Modal -->
	<div class="modal fade" id="editUserModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title text-primary text-uppercase"
						id="exampleModalLabel">Update Here</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form:form action="register" method="POST" modelAttribute="user"
						id="regForm">
						<!-- row#1 -->
						<div class="row">
							<div class="col-md-4">
								<label for="id">user Id</label>
								<!-- for attribute is used to link form input focus -->
							</div>
							<div class="col-md-8">
								<form:input path="userId" name="userId" id="id"
									class="form-control" readonly="true"></form:input>
							</div>

						</div>
						<!-- row#2 -->
						<div class="row">
							<div class="col-md-4">
								<label for="firstName">FIRST NAME</label>
								<!-- for attribute is used to link form input focus -->
							</div>
							<div class="col-md-8">
								<form:input path="firstName" name="firstName" id="firstName"
									class="form-control" />
							</div>

						</div>
						<!-- row#3 -->
						<div class="row">
							<div class="col-md-4">
								<label for="lastName">lastName</label>
								<!-- for attribute is used to link form input focus -->
							</div>
							<div class="col-md-8">
								<form:input path="lastName" name="lastName" id="lastName"
									class="form-control" />
							</div>

						</div>
						<!-- row#4 -->
						<div class="row">
							<div class="col-md-4">
								<label for="email">email</label>
								<!-- for attribute is used to link form input focus -->
							</div>
							<div class="col-md-8">
								<form:input path="email" type="email" name="email" id="email"
									class="form-control" />
							</div>

						</div>

						<!-- row#5 -->
						<div class="row">
							<div class="col-md-4">
								<label for="phNo">phNo</label>
								<!-- for attribute is used to link form input focus -->
							</div>
							<div class="col-md-8">
								<form:input path="phNo" name="phNo" id="phNo"
									class="form-control" type="number" />
							</div>

						</div>

						<!-- row#6 -->
						<div class="row">
							<div class="col-md-4">
								<label for="dob">dob</label>
							</div>
							<div class="col-md-8">
								<form:input path="dob" type="date" name="dob" id="dob"
									class="form-control" />
							</div>

						</div>


						<!-- row#7 -->
						<div class="row">
							<div class="col-md-4">
								<label for="country">country</label>
							</div>
							<div class="col-md-8">
								<form:input type="text" path="country" name="country"
									id="country" class="form-control" />
							</div>

						</div>

						<!-- row#8 -->
						<div class="row">
							<div class="col-md-4">
								<label for="state">state</label>
							</div>
							<div class="col-md-8">
								<form:input type="text" path="state" name="state" id="state"
									class="form-control" />
							</div>

						</div>

						<!-- row#9 -->
						<div class="row">
							<div class="col-md-4">
								<label for="city">city</label>
							</div>
							<div class="col-md-8">
								<form:input type="text" path="city" name="city" id="city"
									class="form-control" />
							</div>

						</div>


						<div class="text-center mt-3">

							<button type="reset" class="btn btn-primary">Clear</button>
							<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-success">Save
								changes</button>
						</div>
					</form:form>
				</div>

			</div>
		</div>
	</div>
	<!-- edit modal ends -->


	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {

							//getting cascading dropdown list by ajax
							//getting states data starts
							$('#country')
									.change(
											function() {
												var countryId = $(this).val();
												$
														.ajax({
															type : 'GET',
															url : 'http://localhost:9595/user-management-app/user/getStatesByCountry/'
																	+ countryId,
															success : function(
																	result) {
																var res = JSON
																		.parse(result);
																var s = "";
																for (var i = 0; i < res.length; i++) {
																	s += '<option value="' + res[i].stateId + '">'
																			+ res[i].stateName
																			+ '</option>';
																}

																$('#state')
																		.html(s);
															}
														});
											});
							//getting states data ends
							//getting cities data starts
							$('#state')
									.change(
											function() {
												var stateId = $(this).val();
												$
														.ajax({
															type : 'GET',
															url : 'http://localhost:9595/user-management-app/user/getCitiesByState/'
																	+ stateId,
															success : function(
																	result) {
																console
																		.log(result);
																var res = JSON
																		.parse(result);
																var s = "";
																for (var i = 0; i < res.length; i++) {
																	s += '<option value="' + res[i].cityId + '">'
																			+ res[i].cityName
																			+ '</option>';
																}

																$('#city')
																		.html(s);
															}
														});
											});
							//getting cities data ends
							//getting countries data starts
							$('#edit')
									.click(
											function() {

												$
														.ajax({
															type : 'GET',
															url : 'http://localhost:9595/user-management-app/user/getCountries',
															success : function(
																	result) {
																console
																		.log(result);
																var res = JSON
																		.parse(result);
																var s = "";
																for (var i = 0; i < res.length; i++) {
																	s += '<option value="' + res[i].countryId + '">'
																			+ res[i].countryName
																			+ '</option>';
																}

																$('#country')
																		.html(s);
															}
														});
												//getting user data starts
												var userId = $('#userId').val();

												$
														.ajax({
															type : 'GET',
															url : 'http://localhost:9595/user-management-app/user/getUserById/'
																	+ userId,
															success : function(
																	user) {
																$(
																		'#regForm #id')
																		.val(
																				user.userId);
																$(
																		'#regForm #firstName')
																		.val(
																				user.firstName);
																$(
																		'#regForm #lastName')
																		.val(
																				user.lastName);
																$(
																		'#regForm #email')
																		.val(
																				user.email);
																$(
																		'#regForm #phNo')
																		.val(
																				user.phNo);
																$(
																		'#regForm #dob')
																		.val(
																				user.dob);
																$(
																		'#regForm #country')
																		.val(
																				user.country);
																$(
																		'#regForm #state')
																		.val(
																				user.state);
																$(
																		'#regForm #city')
																		.val(
																				user.city);

															}
														});
												//getting user data ends
											});
							//getting countries data ends
							function confirmDelete() {
								return confirm('Do you want to delete this record ?');
							}
						});
	</script>

</body>
</html>
