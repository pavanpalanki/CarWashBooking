<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>Set Prices</title>
<%@page import="java.util.ArrayList"%>
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
		if ((String) request.getAttribute("updatePrices") != null
				&& ((String) request.getAttribute("updatePrices")).equals("updatePrices")) {
	%>
	<%@ include file="OwnerHeader.html"%>
	<%
		ArrayList<Float> oldPrices = (ArrayList<Float>) request.getAttribute("oldPrices");
	%>
	<div class="container">
		<form method="post" action="OwnerController">
			<input type="hidden" value="updatePrice" name="action" />
			<div class="container">
				<div class="login-box animated fadeInUp">
					<div class="box-header">
						<h2>Update Prices</h2>
					</div>
					<label>internal wash</label> <br /> <input type="number"
						placeholder=<%=oldPrices.get(0)%> name="iWash"> <br /> <label>external
						wash</label> <br /> <input type="number" placeholder=<%=oldPrices.get(1)%> name="eWash">
					<br /> <label>full body wash</label> <br /> <input type="number"
						placeholder=<%=oldPrices.get(2)%> name="fWash"> <br /> <input type="submit"
						name="Update"> <br /> <br />

				</div>
			</div>
		</form>
	</div>
	<%
		} else {
	%>
	<form method="post" action="OwnerController">
		<input type="hidden" value="setprice" name="action" />
		<div class="container">

			<div class="login-box animated fadeInUp">
				<div class="box-header">
					<h2>Set Prices</h2>
				</div>
				<label>internal wash</label> <br /> <input type="number" step="any"
					name="iWash"> <br /> <label>external wash</label> <br />
				<input type="number" step="any" name="eWash"> <br /> <label>full
					body wash</label> <br /> <input type="number" step="any" name="fWash">
				<br /> <input type="submit" name="submit"> <br /> <br />

			</div>
		</div>
	</form>
	<%
		}
	%>
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