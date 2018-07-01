<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.carwash.business.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <link rel="stylesheet" href="styles/animate.css">-->
<link rel="stylesheet" href="styles/style.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>About Us</title>
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
		<div class="top">
			<h1 id="title" class="hidden">
				<span id="logo">Choice <span>Car Wash</span></span>
			</h1>
		</div>

		<div>
			<!-- <h3 style="text-align:center;"> "We as a service providers give best quality of time saving environment <br>to the customers and ease of access to all the members of this association" </h3> -->
			<p>
				<font color="black"><b> Company Information:
			</p>
			<br/> Management Team<br/> JAPNEET KAUR<br/> Site
			Operations Manager<br/> Team member since 2017<br/> Hello I
			am Japneet and I run the daily operations. I make sure that company
			offers the highest quality of exterior and interior cleans. We offer
			the cheapest wash in the least amount of time. Training of my staff
			is a big priority and making sure that they meet the customer's
			expectation in every department of car Wash, whether it be the
			car wash itself, interior cleaning, or our self-service bays. Making
			sure that a specific level of quality both exterior and interior of
			customer's car is attained. Our success in winning repeat business
			over the years is due to the high standards of work performed by
			employees of Car Wash. Any questions regarding our car wash
			process, our weekly specials, or our menu, come on in and see me. Our
			management team members are all very well educated in all areas of
			our car wash. They are always here to help you with any questions or
			concerns you may have.</b> </font>
			<br /> <br/><p>
			<font color="black"><b>PAWAN PALANKI <br />
			Site Operations Manager<br />
			I am the Operations manager of the Car Wash South location. I started with Valet in 2000 as a cashier, 
			working way up to management which has helped me to clearly understand every aspect of each department 
			including detailing and customer relations. This location is fully equipped to handle all of your car 
			washing and express detailing needs. To ensure customer satisfaction, every express detailing service is
			quality inspected. Hope to see you soon!</b>
			</font>
			</p>
			<br />
			<font color="black">
			<b>ARSHDEEP KAUR THIND<br />
			Site Operations Manager<br />
			Location: 625 St. Clair Road, Windsor<br />
			Hello, my name is Patty and I am the Operations Site Manager for the Chatham Valet location. 
		I have just begun my journey as a part of the Valet family.  This company has an amazing support system,
 		and they are committed to customer satisfaction as well as ongoing training for all their employees.  
 		I want you to know that my staff and I are committed to giving you excellent customer service as well 
 		as helping with your cars' regular maintenance by giving your car a great wash, because we believe that 
 		" A Clean Car is a Happy Car."  So stop by for a visit and see for yourself! </b></font>
		</div>
	</div>

</body>
</html>