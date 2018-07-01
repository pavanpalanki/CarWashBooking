<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.carwash.business.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- <link rel="stylesheet" href="styles/animate.css">-->
<link rel="stylesheet" href="styles/viewbookings.css">
<link rel="stylesheet" href="styles/table.css">
<link rel="stylesheet" href="styles/nav.css">


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Review Order</title>
</head>


<body>
<br/>
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
			<%
				ArrayList<Order> bookings = (ArrayList<Order>) request.getAttribute("bookings");
			%>

			
				<table id="data">
				<tr> <th colspan="6">ORDERS</th></tr>
					<tr>
						<th>Order ID</th>
				<%
					if (role.equals("customer")) {
				%>
				<th>Company</th>
				<%
					} else if (role.equals("owner")) {
				%>
				<th>Customer</th>
				<%
					}
				%>
				<th>Order Date</th>
						<th>Type Of Wash</th>
						<th>Time Slot</th>
						<th>Manage Booking</th>
					</tr>
					<%
						for (Order booking : bookings) {
					%>
					<%
						if (role.equals("customer")) {
					%>
					<form method="post" action="CustomerController">
						<%
							} else if (role.equals("owner")) {
						%>
						<form method="post" action="OwnerController">
							<%
								}
							%>
							<input type="hidden" value="cancel" name="action" />
							<tr>
								<td><input type="hidden" value=<%=booking.getId()%>
									name="bookingID" /><%=booking.getId()%></td>
								<%
									if (role.equals("customer")) {
								%>
								<td><%=booking.getCarWash()%></td>
								<%
									} else if (role.equals("owner")) {
								%>
								<td><%=booking.getCustomerEmail()%></td>
								<%
									}
								%>
								<td><%=booking.getDate()%></td>
								<td><%=booking.getWashType()%></td>

								<td><%=booking.getWashTime()%> </td> <td><%
 	if (booking.getCancel() == true) {
 %><input type="submit" value="cancel" /> <%
 	}
 %></td>
							</tr>
						</form>
						<%
							}
						%>
					
				</table>
			</div>
			</form>

</body>
</html>