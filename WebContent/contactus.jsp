<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>Feedback</title>

<!-- Google Fonts -->
<link
	href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900'
	rel='stylesheet' type='text/css'>

<!-- <link rel="stylesheet" href="styles/animate.css">-->
<!-- Custom Stylesheet -->
<link rel="stylesheet" href="styles/style.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
</head>

<body>
	<%
		String role = (String) session.getAttribute("role");
		if (role.equals("customer")) {
	%>
	<%@ include file="CustomerHeader.html"%>
	<%
		} else if (role.equals("owner")) {
	%>
	<%@ include file="OwnerHeader.html"%>
	<%
		}
	%>
	<div class="container">
		

		<div class="login-box animated fadeInUp">
			<div class="box-header">
				<h2>Feedback</h2>
			</div>
			
			
<br>
			<form method="post" action="LoginController">
				<input type="hidden" value="mail" name="action" />
					<div class="form-group">
					<label>Subject(*):  </label><input type="text"
					name="subject" required> </div><br> <br>

					<div class="form-group">
							
					<label for="comment" >Message:</label>
				 <textarea class="form-control" rows="5" cols="25" name="body"></textarea>
				</div>
				<br> <br>
				<button type="submit" class="btn btn-default">Submit</button>
				<br> <br> 
			</form>
			
		</div>
	</div>
</body>

<script>
	$(document).ready(function() {
		$('#logo').addClass('animated fadeInDown');
		$("input:text:visible:first").focus();
	});
	$('#username').focus(function() {
		$('label[for="username"]').addClass('selected');
	});
	$('#username').blur(function() {
		$('label[for="username"]').removeClass('selected');
	});
	$('#password').focus(function() {
		$('label[for="password"]').addClass('selected');
	});
	$('#password').blur(function() {
		$('label[for="password"]').removeClass('selected');
	});
</script>

</html>