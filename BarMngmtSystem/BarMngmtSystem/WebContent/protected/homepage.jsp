<%@page import="web.users.UserManagement"%>
<%@page import="java.util.LinkedList"%>
<%@page import="web.users.UserManagement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Welcome</title>
<meta name="description" content="Welcome" />
<meta name="keywords"
	content="css3, login, form, custom, input, submit, button, html5, placeholder" />
<meta name="author" content="Codrops" />

<link rel="stylesheet" type="text/css" href="/fmi/css/style.css" />
<!-- <link rel="shortcut icon" href="../favicon.ico">
<script src="/fmi/js/modernizr.custom.63321.js"></script>
 -->
<!--[if lte IE 7]><style>.main{display:none;} .support-note .note-ie{display:block;}</style><![endif]-->
<style>
@import url(http://fonts.googleapis.com/css?family=Raleway:400,700);

body {
	background: #7f9b4e url(/fmi/images/bg2.jpg) no-repeat center top;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	background-size: cover;
}

.container>header h1,.container>header h2 {
	color: #fff;
	text-shadow: 0 1px 1px rgba(0, 0, 0, 0.7);
}
</style>
</head>
<body>	
	<div class="container">
		<header>
			<h2>Welcome, ... print the username from session? </h2>
			<div class="support-note">
				<span class="note-ie">Sorry, only modern browsers.</span>
			</div>

		</header>
		
		<section class="main">
			<!-- what method should be used in order not to see the username/password in the url? -->
			<form class="form-5" action=<%= getServletContext().getContextPath() + "/public/controller" %> method="post">
				<p>
					<input type="submit" name="action" value="logout">
				</p>
			</form>
			
			<% 
				
			UserManagement userm = (UserManagement) getServletContext().getAttribute("users");
			if (userm.isManagerUser((String)session.getAttribute("username"))) {
				
				%>
				<button id="monitor_btn" name="Monitor">Manager's btn</button>
				<%
			}
			
			%>			
			<!--  if it is admin user show a monitoring button to forward to administration.jsp(list info about active sessions)
			
			for normal users do not show that button. and for normal users get the AuditLog and print the history for that user
			
			-->
	
			
		</section>

	</div>

</body>
</html>