<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" />

<style>
.row {
	padding-bottom: 10px;
}

label, b {
	text-transform: uppercase;
}
</style>
</head>

<body>
	<div class="container">
		<div class="card">
			<div class="card-header bg-primary text-white text-center">
				<h3>USER REGISTRATION FORM</h3>
			</div>
			<!-- card header end -->
			<div class="card-body">
				<form:form action="register" method="POST" modelAttribute="user"
					id="regForm">
					<!-- row#1 -->
					<div class="row">
						<div class="col-md-2">
							<label for="firstName">FIRST NAME</label>
							<!-- for attribute is used to link form input focus -->
						</div>
						<div class="col-md-5">
							<form:input path="firstName" name="firstName" id="firstName"
								class="form-control" />
						</div>
						<div class="col-md-5">
							<!-- This is to show error section -->
							<span id="firstNameError"></span>
						</div>
					</div>
					<!-- row#2 -->
					<div class="row">
						<div class="col-md-2">
							<label for="lastName">lastName</label>
							<!-- for attribute is used to link form input focus -->
						</div>
						<div class="col-md-5">
							<form:input path="lastName" name="lastName" id="lastName"
								class="form-control" />
						</div>
						<div class="col-md-5">
							<!-- This is to show error section -->
							<span id="lastNameError"></span>
						</div>
					</div>
					<!-- row#3 -->
					<div class="row">
						<div class="col-md-2">
							<label for="email">email</label>
							<!-- for attribute is used to link form input focus -->
						</div>
						<div class="col-md-5">
							<form:input path="email" type="email" name="email" id="email"
								class="form-control" />
						</div>
						<div class="col-md-5">
							<!-- This is to show error section -->
							<span id="emailError"></span>
						</div>
					</div>

					<!-- row#4 -->
					<div class="row">
						<div class="col-md-2">
							<label for="phNo">phNo</label>
							<!-- for attribute is used to link form input focus -->
						</div>
						<div class="col-md-5">
							<form:input path="phNo" name="phNo" id="phNo"
								class="form-control" type="number" />
						</div>
						<div class="col-md-5">
							<!-- This is to show error section -->
							<span id="phNoError"></span>
						</div>
					</div>

					<!-- row#5 -->
					<div class="row">
						<div class="col-md-2">
							<label for="dob">dob</label>
						</div>
						<div class="col-md-5">
							<form:input path="dob" type="date" name="dob" id="dob"
								class="form-control" />
						</div>
						<div class="col-md-5">
							<span id="dobError"></span>
						</div>
					</div>
					<!-- row#6 -->
					<div class="row">
						<div class="col-md-2">
							<label>gender</label>
						</div>
						<div class="col-md-5">
							<form:radiobutton path="gender" name="gender" id="gender1"
								value="Male" />
							Male
							<form:radiobutton path="gender" name="gender" id="gender2"
								value="Female" />
							Female
						</div>
						<div class="col-md-5">
							<span id="genderError"></span>
						</div>
					</div>

					<!-- row#7 -->
					<div class="row">
						<div class="col-md-2">
							<label for="country">country</label>
						</div>
						<div class="col-md-5">
							<select name="country" id="country" class="form-control">
								<option>--SELECT--</option>
								<c:forEach var="country" items="${countries}">
									<option value="${country.getKey()}">${country.getValue()}</option>
								</c:forEach>

							</select>
						</div>
						<div class="col-md-5">
							<span id="countryError"></span>
						</div>
					</div>

					<!-- row#8 -->
					<div class="row">
						<div class="col-md-2">
							<label for="state">state</label>
						</div>
						<div class="col-md-5">
							<select name="state" id="state" class="form-control">
								<option>--SELECT--</option>
							</select>
						</div>
						<div class="col-md-5">
							<span id="stateError"></span>
						</div>
					</div>

					<!-- row#9 -->
					<div class="row">
						<div class="col-md-2">
							<label for="city">city</label>
						</div>
						<div class="col-md-5">
							<select name="city" id="city" class="form-control">
								<option>--SELECT--</option>
							</select>
						</div>
						<div class="col-md-5">
							<span id="cityError"></span>
						</div>
					</div>


					<div class="text-center">
						<button type="submit" class="btn btn-success">Create</button>
						<button type="reset" class="btn btn-danger">Clear</button>
					</div>
				</form:form>
			</div>
			<!-- card body end -->
			<div class="card-footer bg-info text-white">
				<h3 class="text-center">${msg}</h3>
			</div>
			<!-- footer end -->
		</div>
		<!-- card end -->
	</div>
	<!-- container end -->

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
																	s += '<option value="'
																			+ res[i]
																					.getKey()
																			+ '">'
																			+ res[i]
																					.getValue()
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
							//form validation starts
							//  hiding all error msg
							$("#firstNameError").hide();
							$("#lastNameError").hide();
							$("#emailError").hide();
							$("#phNoError").hide();
							$("#dobError").hide();
							$("#genderError").hide();
							$("#countryError").hide();
							$("#stateError").hide();
							$("#cityError").hide();

							//  defining error variables

							var firstNameError = false;
							var lastNameError = false;
							var emailError = false;
							var phNoError = false;
							var dobError = false;
							var genderError = false;

							//creating validate functions

							function validate_firstName() {
								//read input value
								var val = $("#firstName").val();
								var exp = /^[A-Za-z]{3,8}$/;
								if (val == "") {
									$("#firstNameError").show();
									$("#firstNameError").html(
											"*Please Enter <b>first name</b>");
									$("#firstNameError").css("color", "red");
									firstNameError = false;
								} else if (!exp.test(val)) {
									$("#firstNameError").show();
									$("#firstNameError")
											.html(
													"*Must contain <b>between 3 to 8 chars</b>");
									$("#firstNameError").css("color", "red");
									firstNameError = false;
								} else {
									$("#firstNameError").hide();
									firstNameError = true;
								}
								return firstNameError;
							}
							function validate_lastName() {
								//read input value
								var val = $("#lastName").val();
								var exp = /^[A-Za-z]{3,10}$/;
								if (val == "") {
									$("#lastNameError").show();
									$("#lastNameError").html(
											"*Please Enter <b>last name</b>");
									$("#lastNameError").css("color", "red");
									lastNameError = false;
								} else if (!exp.test(val)) {
									$("#lastNameError").show();
									$("#lastNameError")
											.html(
													"*Must contain <b>between 3 to 8 chars</b>");
									$("#lastNameError").css("color", "red");
									lastNameError = false;
								} else {
									$("#lastNameError").hide();
									lastNameError = true;
								}
								return lastNameError;
							}
							function validate_email() {
								//read input value
								var val = $("#email").val();
								var exp = /^[A-Za-z0-9\@\.]{10,30}$/;
								if (val == "") {
									$("#emailError").show();
									$("#emailError").html(
											"*Please Enter <b>email</b>");
									$("#emailError").css("color", "red");
									lastNameError = false;
								} else if (!exp.test(val)) {
									$("#emailError").show();
									$("#emailError")
											.html(
													"*Must contain <b>between 10 to 30 chars</b>");
									$("#emailError").css("color", "red");
									lastNameError = false;
								} else {
									$("#emailError").hide();
									emailError = true;
								}
								return emailError;
							}
							function validate_phNo() {
								//read input value
								var val = $("#phNo").val();
								if (val == "") {
									$("#phNoError").show();
									$("#phNoError")
											.html(
													"*Please Enter <b>phone number</b>");
									$("#phNoError").css("color", "red");
									phNoError = false;
								} else {
									$("#phNoError").hide();
									phNoError = true;
								}
								return phNoError;
							}
							function validate_dob() {
								var val = $("#dob").val();
								if (val == "") {
									$("#dobError").show();
									$("#dobError").html(
											"*Please Enter <b>dob</b>");
									$("#dobError").css("color", "red");
									dobError = false;
								} else {
									$("#dobError").hide();
									dobError = true;
								}

								return dobError;
							}

							function validate_gender() {

								var length = $("[name='gender']:checked").length;
								if (length == 0) {
									//alert('Please Select Your Gender');
									$("#genderError").show();
									$("#genderError")
											.html(
													'*Please Select <b>Your Gender</b>');
									$("#genderError").css("color", "red");
									genderError = false;
								} else {
									$("#genderError").hide();
									genderError = true;
								}

								return genderError;
							}

							//linking function with action event
							$("#firstName").keyup(function() {
								validate_firstName();
							});
							$("#lastName").keyup(function() {
								validate_lastName();
							});
							$("#email").keyup(function() {
								validate_email();
							});
							$("#phNo").keyup(function() {
								validate_phNo();
							});
							$("#dob").keyup(function() {
								validate_dob();
							});
							$("[name='gender']").click(function() {
								validate_gender();
							});

							$("#regForm").submit(
									function() {
										validate_firstName();
										validate_lastName();
										validate_email();
										validate_phNo();
										validate_dob();
										validate_gender();
										if (firstNameError && lastNameError
												&& emailError && phNoError
												&& dobError && genderError) {
											return true;
										}
										return false;
									});
							//form validation ends
						});
	</script>
</body>

</html>