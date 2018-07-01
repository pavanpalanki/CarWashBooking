<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>Register</title>

<!-- Google Fonts -->
<link
	href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900'
	rel='stylesheet' type='text/css'>

<!-- <link rel="stylesheet" href="styles/animate.css">-->
<link rel="stylesheet" href="styles/register.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript">
//Phone number validation
function phonenumber(inputtxt)
{
  var phoneno = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
  if((inputtxt.value.match(phoneno)))
        {
      return true;
        }
      else
        {
        alert("Please enter a valid Phone number");
        window.location = "register.jsp";
        }
}
</script>
</head>

<body>

<!-- 	<div class="container">
		<div class="top">
			<h1 id="title" class="hidden">
				<span id="logo">Choice <span>Car Wash</span></span>
			</h1>
		</div> -->
		<div class="login-box animated fadeInUp">

			<div class="box-header">
				<h2>Register</h2>
			</div>
			<%
				String msg = (String) request.getAttribute("msg");
				System.out.println("msg in register.jsp : " + msg);
				if (msg != null) {
			%>
			<label><font color="red"><%=msg%>
			</font> <%
 	}
 %>
 <form name="register" class="login-form" method="post" action="LoginController">
					<table>
					<tc colspan="10"><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tc>
	<tc>
	<td>
		<input type="hidden" value="register" name="action" /> <label
			for="username">Email</label> <br /> <input type="email" name="email" />
		<br /> <label for="username">password</label> <br /> <input
			type="password" name="password" /> <br /> <label for="fname">first
			name</label> <br /> <input type="text" name="fname" /> <br /> <label
			for="lname">last name</label> <br /> <input type="text" name="lname" />
		<br /> <label for="c_number">Contact Number</label> <br /> <input
			type="Contact Number" placeholder="ex: 9999999999" name="phone" />
		<br /> <label for="d_no">Door number</label><br /><input
			type="text" name="doorno" /></td></tc> 
			<tc colspan="10"><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tc>
	<tc><td><label for="street">street</label>
		<br /> <input type="text" name="street" /> <br /> <label for="city">city</label>
		<br /> <input type="text" name="city" /><br /> <label
			for="province">province</label> <br /> <input type="text"
			placeholder="ex: ON" name="province" /> <br /> <label for="p_code">postal
			code</label> <br /> <input type="text" placeholder="ex: N9B2R7"
			name="postalcode" /> <br /> <select name="role">
			<option value="customer">Customer</option>
			<option value="owner">Owner</option>
		</select> <br /> <br />
		<button type="submit" onclick="phonenumber(document.register.phone)">Sign
			Up</button>
		<a href="index.jsp"><p class="small">Login Here</p></a>
		</td>
	</tc>
	</table>
	</form>
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