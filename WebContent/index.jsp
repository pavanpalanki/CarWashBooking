<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>Sign In</title>
<!-- <link rel="stylesheet" href="styles/animate.css">-->
<link rel="stylesheet" href="styles/style.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
</head>

<body>
	<div class="container">
		<div class="top">
			<h1 id="title" class="hidden">
				<span id="logo">Choice <span>Car Wash</span></span>
			</h1>
		</div>

		<div class="login-box animated fadeInUp">
			<div class="box-header">
				<h2>Log In</h2>
			</div>
			<%
				String msg = (String) request.getAttribute("message");				
				if (msg != null) {
			%>
			<label><font color="red"><%=msg%>
			</font> <%
 	}
 %>
			<form method="post" action="LoginController">
				<input type="hidden" value="login" name="action" /> <label
					for="username">Username/Email ID</label> <br /> <input type="text"
					name="email"> <br /> <label for="password">Password</label>
				<br /> <input type="password" name="password"> <br />
				<button type="submit">Sign In</button>
				<br /> <a href="register.jsp"><p class="small">Dont have
						account? Register!</p></a>
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