<%@page import="web.management.OrdersManagement"%>
<%@page import="web.management.DrinksManagement"%>
<%@page import="web.management.UsersManagement"%>
<%@page import="java.util.LinkedList"%>
<%@page import="web.management.UsersManagement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Manager</title>
<meta name="description" content="Welcome" />
<meta name="keywords"
	content="css3, login, form, custom, input, submit, button, html5, placeholder" />
<meta name="author" content="Codrops" />

<link rel="stylesheet" type="text/css" href="/BarMngmtSystem/css/style.css" />
<link rel="stylesheet" type="text/css"	href="/BarMngmtSystem/css/orders/orders.css" />

<link rel="icon" href="/BarMngmtSystem/images/favicon.ico">

<!-- 
<script src="/fmi/js/modernizr.custom.63321.js"></script>
 -->
<!--[if lte IE 7]><style>.main{display:none;} .support-note .note-ie{display:block;}</style><![endif]-->
<style>
/* @import url(http://fonts.googleapis.com/css?family=Raleway:400,700); */

body {
	/* background: #7f9b4e url(/fmi/images/bg2.jpg) no-repeat center top; */
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
			<h2>
				Welcome, manager...
				<%=session.getAttribute("username")%></h2>
			<div class="support-note">
				<span class="note-ie">Sorry, only modern browsers.</span>
			</div>
		</header>
		<section class="main">
			<form class="form-5"
				action=<%=getServletContext().getContextPath() + "/public/controller"%>
				method="post">
				<input type="submit" name="action" value="logout">
			</form>
			<p>Logged users list:</p>
			<div class="ordersTableDiv" style="height: 170">
				<!-- starts the orders's table  -->
				<table>
					<colgroup>
						<col width="20px" />
						<col width="40px" />
					</colgroup>
					<tbody>
						<tr>
							<th>&nbsp;&nbsp;Name</th>
							<th>&nbsp;&nbsp;Role</th>
							<th></th>
						</tr>
					</tbody>
				</table>
				<div class="ordersContentTableDiv" style="height: 150">
					<table id="ordersTable">
						<colgroup>
							<col width="20px" />
							<col width="40px" />
						</colgroup>
						<tbody>
							<c:forEach items="${loggedUsers}" var="user">
								<tr>
									<td><c:out value="${user.name}" /></td>
									<td><c:out value="${user.role.role}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<!-- ends the logged user's table  -->


		</section>
	</div>

</body>
</html>