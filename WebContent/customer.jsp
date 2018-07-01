<!DOCTYPE html>
<%@page import="com.carwash.business.*"%>
<%@page import="java.util.*"%>
<html lang="en">

<head>
<meta charset="utf-8">
<title>Welcome Customer</title>

<!-- Google Fonts -->

<!-- <link rel="stylesheet" href="styles/animate.css">-->
<link rel="stylesheet" href="styles/customer.css">
<link rel="stylesheet" href="styles/nav.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
</head>

<body>
<br/>
	<%
		//ArrayList<Wash> availableSlots = (ArrayList<Wash>) request.getAttribute("getAvailableWashes");
		ArrayList<HashMap<String, String>> owners = (ArrayList<HashMap<String, String>>) request
				.getAttribute("allOwners");
		String times[] = {"10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30",
				"15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30",};
		String types[] = {"Internal Wash", "External Wash", "Full Body Wash"};
	%>
	 <%@ include file="CustomerHeader.html" %>
	 
	<div class="container">
		
		<div class="login-box animated fadeInUp">
			<div class="box-header">
				<h2>BOOK</h2>
			</div>
			<form method="post" action="CustomerController">
			<input type="hidden" value="checkAvailability" name="action" />
				<label>DATE</label> <br /> <input type="date"
					name="date" placeholder=" ex: DD-MM-YYYY "> <br />
				<label>TIME</label> <br /> <select name="time">
					<%
						for (int i = 0; i < times.length; i++) {
					%>
					<option value=<%=(i + 100)%>><%=times[i]%></option>
					<%
						}
					%>

				</select><br /> <br /> <label>WASH TYPE</label> <br /> <select
					name="washType">
					<%
						for (int i = 0; i < types.length; i++) {
					%>
					<option value=<%=(i + 1)%>><%=types[i]%></option>
					<%
						}
					%>
				</select> 
				<br /> <br /> 
				<label>COMPANY</label> <br /> <select
					name="owner">
					<%
						for (HashMap<String, String> owner : owners) {
							Set entrySet = owner.entrySet();
							Iterator it = entrySet.iterator();
							while (it.hasNext()) {
								Map.Entry me = (Map.Entry) it.next();
					%>
					<option value=<%=me.getKey()%>><%=me.getValue()%></option>
					<%
						}
						}
					%>
</select>
					<br />
					<br /> 
					<input type="submit" value="BOOK">
					
			</form>
			<%
			if(request.getAttribute("availability") !=null){
				if (request.getAttribute("availability").equals("false")) {
					out.print("Sorry!! Selected slot is not available");
				} else if (request.getAttribute("availability").equals("true")) {
			%>
			<!-- <form method="post" action="CustomerController">
				<input type="hidden" value="continueBooking" name="action" /> <br /><input
					type="submit" value="Book" />
			</form> -->
			<%
				}else if (request.getAttribute("availability").equals("pastdate")) {
					out.print("Please sleect a future date to book");
				}
			}
			%>


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