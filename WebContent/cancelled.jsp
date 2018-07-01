<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <link rel="stylesheet" href="styles/animate.css">-->
<link rel="stylesheet" href="styles/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cancelled</title>
</head>
<body>
<br />
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
		<div class="top">
			<h1 id="title" class="hidden">
				<span id="logo">Choice <span>Car Wash</span></span>
			</h1>
		</div>
		<div>
			<%
				if (request.getAttribute("bookingID") != null) {
			%>
			<h3 style="text-align: center;">
				Cancelled order
				<%=request.getAttribute("bookingID")%><br/>
				To book new car wash, visit Quick Book
				</h3><br/>
				<%
					}
				%>
			
		</div>
	</div>

</body>
</html>