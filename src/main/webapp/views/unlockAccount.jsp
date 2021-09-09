<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Unlock Account Page</title>
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

label, h3, b {
	text-transform: uppercase;
}
</style>
</head>
<body>
	<div class="container">
		<div class="card">
			<div class="card-header bg-primary text-white text-center">
				<h3>unlock account here</h3>
			</div>
			<div class="card-body">
				<form id="unlockAccountForm" action="unlockAccount" method="POST">
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
							<label>temp password</label>
						</div>
						<div class="col-4">
							<input type="password" name="tempPsw" id="tempPsw"
								class="form-control" placeholder="Enter Temporary Password" />
						</div>
						<div class="col-6">
							<span id="tempPswError"></span>
						</div>
					</div>
					<!-- row#3 -->
					<div class="row">
						<div class="col-2">
							<label>new Password</label>
						</div>
						<div class="col-4">
							<input type="password" name="newPsw" id="newPsw"
								class="form-control" placeholder="Enter New Password" />
						</div>
						<div class="col-6">
							<span id="newPswError"></span>
						</div>
					</div>
					<button type="submit" class="btn btn-success">
						<i class="fa fa-sign-in" aria-hidden="true"></i> Proceed
					</button>
				</form>
			</div>
			<div class="card-footer">
				<h5 class="text-success">${msg}</h5>
			</div>
		</div>
	</div>
	<script>
		$(document)
				.ready(
						function() {
							//1. hide error section
							$("#emailError").hide();
							$("#tempPswError").hide();
							$("#newPswError").hide();

							//2. define error variable
							var emailError = false;
							var tempPswError = false;
							var newPswError = false;

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

							function validate_tempPsw() {
								//read form input
								var val = $("#tempPsw").val();
								if (val == "") {
									$("#tempPswError").show();
									$("#tempPswError")
											.html(
													"*Please Enter <b>Temp password</b>");
									$("#tempPswError").css("color", "red");
									tempPswError = false;
								} else {
									$("#tempPswError").hide();
									tempPswError = true;
								}
								return tempPswError;
							}
							function validate_newPsw() {
								//read form input
								var val = $("#newPsw").val();

								if (val == "") {
									$("#newPswError").show();
									$("#newPswError")
											.html(
													"*Please Enter <b>new password</b>");
									$("#newPswError").css("color", "red");
									newPswError = false;
								} else {
									$("#newPswError").hide();
									newPswError = true;
								}
								return newPswError;
							}

							//4. link with action-event
							$("#email").keyup(function() {
								validate_email();
							});

							$("#tempPsw").keyup(function() {
								validate_tempPsw();
							});
							$("#newPsw").keyup(function() {
								validate_newPsw();
							});

							//5.(at last) on-submit
							$("#unlockAccountForm").submit(function() {
								//call all validate functions
								validate_email();
								validate_tempPsw();
								validate_newPsw();

								if (emailError && tempPswError && newPswError)
									return true;
								else
									return false;
							});
						});
	</script>
</body>
</html>