<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="java.util.*"%>
<%@ page import="com.carwash.business.*"%>

<!-- <link rel="stylesheet" href="styles/animate.css">-->
<link rel="stylesheet" href="styles/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Companies</title>
</head>
<body>
	<br />
	<%
		ArrayList<CarWashes> companies = (ArrayList<CarWashes>) request.getAttribute("companies");
	%>
	<%@ include file="CustomerHeader.html"%>
<div class="container">
		<div class="top">
			<h1 id="title" class="hidden">
				<span id="logo">Choice <span>Car Wash</span></span>
			</h1>
		</div>
<table>
<tc>
<td>
		<div>
			<%
				for (CarWashes car : companies) {
			%>
			<h3><u>
				<%=car.getCarWash()%> </u><br /> 
			</h3>
			<b><i><font color="black">
			Address : <%=car.getAddress() %><br/>
			Email   :<%=car.getEmail() %><br/>
			Phone   :<%=car.getPhone() %>
			<br /><br/>
			</font></i></b>
			<%
				}
			%>

		</div>
	</div>
	</td>
	</tc>
	<tc>
	<td>
<!-- <iframe src="https://www.google.com/maps/d/embed?mid=1ZIEZlN_Ed5Bcj_SFcJF6FXmzP40YG34l" width="640" height="480"></iframe> -->
<iframe src="src.html" width="1000" height="1000"></iframe>
<!-- <iframe src="https://www.google.com/maps/d/u/0/edit?mid=1ZIEZlN_Ed5Bcj_SFcJF6FXmzP40YG34l&ll=42.30296223728506%2C-82.9970568&z=12"  scrolling="yes" width="1000" height="1000">
<p>Your browser does not support iframes.</p></iframe> -->
</td>
</tc>
</table>
</html>