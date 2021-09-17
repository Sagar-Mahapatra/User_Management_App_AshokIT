<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" />
<style type="text/css">
.row {
	margin: 1rem 0;
}

label {
	text-transform: uppercase;
}
</style>
</head>
<body>
	<div class="container">
		<div class="card">
			<div class="card-header bg-primary text-white text-center">
				<h3>USER LOGIN PAGE</h3>
			</div>
			<div class="card-body">
				<form:form id="myLoginForm" modelAttribute="loginForm"
					action="userLogin" method="POST">
					<!-- row#1 -->
					<div class="row">
						<div class="col-2">
							<label for="email">email</label>
						</div>
						<div class="col-4">
							<input type="email" name="email" id="email" class="form-control"
								placeholder="Enter email" />
						</div>
						<div class="col-6">
							<span id="emailError"></span>
						</div>
					</div>

					<!-- row#2 -->
					<div class="row">
						<div class="col-2">
							<label>Password</label>
						</div>
						<div class="col-4">
							<input type="password" name="password" id="password"
								class="form-control" placeholder="Enter Password" />
						</div>
						<div class="col-6">
							<span id="passwordError"></span>
						</div>
					</div>
					<div class="text-center">
						<div class="row">
							<div class="col-md-4">
								<button type="submit" class="btn btn-success">
									<i class="fa fa-sign-in" aria-hidden="true"></i> Login User
								</button>
							</div>
							<div class="col-md-4">
								<a href="loadForgotPasswordForm" class="btn btn-primary">Forgot
									Password</a>
							</div>
							<div class="col-md-4">
								<a href="loadRegForm" class="btn btn-primary">SignUp</a>
							</div>
						</div>

					</div>

				</form:form>
			</div>
			<div class="card-footer">
				<h3 class="text-success">${msg}</h3>
			</div>
		</div>
	</div>
	<script>
		$(document)
				.ready(
						function() {
							//1. hide error section
							$("#emailError").hide();
							$("#passwordError").hide();

							//2. define error variable
							var emailError = false;
							var passwordError = false;

							//3. validate function
							function validate_email() {
								//read input value
								var val = $("#email").val();
								if (val == "") {
									$("#emailError").show();
									$("#emailError").html(
											"*Please Enter <b>email</b>");
									$("#emailError").css("color", "red");
									emailError = false;
								} else {
									$("#emailError").hide();
									emailError = true;
								}
								return emailError;
							}

							function validate_password() {
								//read form input
								var val = $("#password").val();
								if (val == "") {
									$("#passwordError").show();
									$("#passwordError")
											.html(
													"*Please Enter <b>User password</b>");
									$("#passwordError").css("color", "red");
									passwordError = false;
								} else {
									$("#passwordError").hide();
									passwordError = true;
								}
								return passwordError;
							}

							//4. link with action-event
							$("#email").keyup(function() {
								validate_email();
							});

							$("#password").keyup(function() {
								validate_password();
							});

							//5.(at last) on-submit
							$("#myLoginForm").submit(function() {
								//call all validate functions
								validate_email();
								validate_password();

								if (emailError && passwordError)
									return true;
								else
									return false;
							});
						});
	</script>
</body>
</html>