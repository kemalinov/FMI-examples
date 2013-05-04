<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Welcome to BarMngmt System</title>
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

			<div class="support-note">
				<span class="note-ie">Sorry, only modern browsers.</span>
			</div>

		</header>
		<!-- what method should be used in order not to see the username/password in the url? -> method="post" not to show in the url-->
		<!-- /fmi/public/controller - kolko se moje po-malko java code v jsp !!!-->
		<section class="main">
			<form class="form-4" action=<%= getServletContext().getContextPath() + "/public/controller" %> method="post">
				<h1>Login or Register</h1>
				<p>
					<label for="login">Username or email</label> 
					<input type="text" name="username" placeholder="Username or email" required>
				</p>
				<p>
					<label for="password">Password</label> 
					<input type="password" name='password' placeholder="Password" required>
				</p>

				<p>
					<input type="submit" name="action" value="login">
				</p>
			</form>
			
			<form class="form-5" action="<%= getServletContext().getContextPath() + "/public/register.jsp" %>">
				<p>
					<input type="submit" name="action" value="register">
				</p>
			</form>
		</section>

	</div>
</body>
</html>