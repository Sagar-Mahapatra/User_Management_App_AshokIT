<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
								class="form-control" />
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
							<form:select path="country" name="country" id="country"
								class="form-control">
								<form:options items="${countries}"></form:options>
							</form:select>
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
							<form:select path="state" name="state" id="state"
								class="form-control">
								<form:options items="${states}"></form:options>
							</form:select>
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
							<form:select path="city" name="city" id="city"
								class="form-control">
								<form:options items="${cities}"></form:options>
							</form:select>
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
								if (val == "") {
									$("#firstNameError").show();
									$("#firstNameError").html(
											"*Please Enter <b>first name</b>");
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
								if (val == "") {
									$("#lastNameError").show();
									$("#lastNameError").html(
											"*Please Enter <b>last name</b>");
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
								if (val == "") {
									$("#emailError").show();
									$("#emailError").html(
											"*Please Enter <b>email</b>");
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
									$("#phNoError").html(
											"*Please Enter <b>ph no</b>");
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
									userSalError = false;
								} else {
									$("#dobError").hide();
									dobError = true;
								}

								return dobError;
							}

							function validate_gender() {
								var val1 = $("#gender1").prop('checked');
								var val2 = $("#gender2").prop('checked');
								if (val1 && val2) {
									$("#genderError").show();
									$("#genderError").html(
											"*Please select <b>gender</b>");
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