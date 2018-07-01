<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	

	<!-- Google Fonts -->
	<link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900' rel='stylesheet' type='text/css'>

	<!-- <link rel="stylesheet" href="styles/animate.css">-->
	<!-- Custom Stylesheet -->
	<link rel="stylesheet" href="styles/viewbookings.css">

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Review Order</title>
</head>


<body>
<br />
<%@ include file="CustomerHeader.html" %>
<div class="container">
		<!--<div class="top">
			<h1 id="title" class="hidden"><span id="logo">Review <span>Bookings</span></span></h1>
		</div> -->
	<div >
		<%
			ArrayList al = (ArrayList) session.getAttribute("displayOrder");
		%>
		<form method="post" action="OrderController">
			
			<div class="login-box animated fadeInUp">
			<div class="box-header">
				<h2>Review booking</h2>
			</div>

			<label>Company</label>
			<br/><br />
			<%=al.get(0)%> 
			<br /> <br />
			<label>wash name</label>
			<br/><br />
			<%=al.get(1)%>
			<br /><br />
			<label>date</label>
			<br/><br />
			<%=al.get(2)%>
			<br /> <br />
			<label>time</label>
			<br/><br />
			<%=al.get(3)%>
			<br /> <br />
			<label>price</label>
			<br/><br />
			<%=al.get(4)%>
			<br /> <br /><input type="hidden" value="book" name="action" />			
			<input type="hidden" value=<%=al%> name="displayOrder" />
			<input 	type="submit" name="book" value="Proceed to Book" />
		</form>
		<form method="post" action="OrderController">
		<input type="hidden" value="rebook" name="action" />
			<input type="submit" value="Re Plan" />
			</div>
		</form>
	</div>
	</div>
</body>
</html>