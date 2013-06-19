<%@page import="java.util.ArrayList"%>
<%@page import="web.pojos.Role"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registration</title>
<meta name="description" content="Welcome" />
<meta name="keywords"
	content="css3, login, form, custom, input, submit, button, html5, placeholder" />
<meta name="author" content="Codrops" />

<link rel="stylesheet" type="text/css" href="/BarMngmtSystem/css/style.css" />
<link rel="icon" href="/BarMngmtSystem/images/favicon.ico">
<!--
<script src="../js/modernizr.custom.63321.js"></script> -->
<!--[if lte IE 7]><style>.main{display:none;} .support-note .note-ie{display:block;}</style><![endif]-->
<style>
/* @import url(http://fonts.googleapis.com/css?family=Raleway:400,700); */

body {
	/* background: #7f9b4e url(../images/bg2.jpg) no-repeat center top; */
	background-color: #FAEBD7;
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

			<div class="support-note">
				<span class="note-ie">Sorry, only modern browsers.</span>
			</div>

		</header>

		<section class="main">
			<form class="form-4" action=<%= getServletContext().getContextPath() + "/public/controller" %> method="post">
				<h1>Registration will take a few seconds</h1>
				<p>
					<label>Username or email</label> 
					<input type="text" name="username" placeholder="Username or email" required>
				</p>
				<p>
					<label for="password">Password</label> 
					<input type="password" name='password' placeholder="Password" required>
				</p>
				
				<p>
					<label for="password">Re-enter password</label> 
					<input type="password" name='password1' placeholder="Password" required>
				</p>

				<p>
					<label>Choose a role</label>
					<select name="role">
					<% 
						for(Role r : (ArrayList<Role>)getServletContext().getAttribute("roles")) {
							out.print("<option>" + r.getRole() + "</option>");
						}
					%>
					</select> 
				</p>
				<p>
					<input type="submit" name="action" value="register">
				</p>
			</form>
			<form class="form-4" action=<%= getServletContext().getContextPath() + "/public/controller" %> method="post">
				<input type="submit" name="action" value="cancel" >
			</form>
		</section>
	</div>
</body>
</html>