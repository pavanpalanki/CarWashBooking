<!DOCTYPE html>
<%@page import="com.carwash.business.*"%>
<%@page import="java.util.*"%>
<html lang="en">

<head>
<meta charset="utf-8">
<title>Welcome Owner</title>

<!-- <link rel="stylesheet" href="styles/animate.css">-->
<link rel="stylesheet" href="styles/owner.css">
<link rel="stylesheet" href="styles/table.css">
<link rel="stylesheet" href="styles/nav.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
</head>

<body background="styles/photo_bg.jpg">
<br />
<%@ include file="OwnerHeader.html"%>
	<div class="container">
		

		<div>
			<%
				ArrayList<Order> bookings = (ArrayList<Order>) request.getAttribute("bookings");
			%>

				<table id="data">
				<tr> <th colspan="6">ORDERS</th></tr>
					<tr>
						<th>Order ID</th>
						<th>Customer</th>
						<th>Order Date</th>
						<th>Type Of Wash</th>
						<th>Time Slot</th>
						<th>Manage Booking</th>
					</tr>
					<%
						for (Order booking : bookings) {
							
					%>
					<form method="post" action="OwnerController">
					<input type="hidden" value="cancel" name="action" />
					<tr>
						<td><input type="hidden" value=<%=booking.getId()%> name="bookingID" /><%=booking.getId()%></td>
						<td><%=booking.getCustomerEmail()%></td>
						<td><%=booking.getDate()%></td>
						<td><%=booking.getWashType()%></td>
						<td><%=booking.getWashTime()%></td>
						<td><%if(booking.getCancel()==true){							
							%><input type="submit" value="cancel" />
						<% }%>
						</td>
					</tr>
					</form>
					<%					
						}
					%>
				</table>
			</div>
			</form>
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