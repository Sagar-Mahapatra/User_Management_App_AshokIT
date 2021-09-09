<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

label,h3 {
	text-transform: uppercase;
}
</style>
</head>
<body>
	<div class="container">
		<div class="card">
			<div class="card-header bg-primary text-white text-center">
				<h3>forgot password</h3>
			</div>
			<div class="card-body">
				<form id="forgotPswForm" action="forgotPassword" method="POST">
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


					<div class="text-center">
						<div class="row">
							<div class="col-md-4">
								<button type="submit" class="btn btn-success">
									<i class="fa fa-sign-in" aria-hidden="true"></i> Submit
								</button>
							</div>

							<div class="col-md-4">
								<a href="loadLoginForm" class="btn btn-info">Login</a>
							</div>

							<div class="col-md-4">
								<a href="loadRegForm" class="btn btn-primary">SignUp</a>
							</div>
						</div>

					</div>

				</form>
			</div>
			<div class="card-footer">
				<h3 class="text-primary">${msg}</h3>
			</div>
		</div>
	</div>
	<script>
		$(document).ready(function() {
			//1. hide error section
			$("#emailError").hide();

			//2. define error variable
			var emailError = false;

			//3. validate function
			function validate_email() {
				//read input value
				var val = $("#email").val();
				if (val == "") {
					$("#emailError").show();
					$("#emailError").html("*Please Enter <b>email</b>");
					$("#emailError").css("color", "red");
					emailError = false;
				} else {
					$("#emailError").hide();
					emailError = true;
				}
				return emailError;
			}

			//4. link with action-event
			$("#email").keyup(function() {
				validate_email();
			});

			//5.(at last) on-submit
			$("#forgotPswForm").submit(function() {
				//call all validate functions
				validate_email();

				if (emailError)
					return true;
				else
					return false;
			});
		});
	</script>
</body>
</html>