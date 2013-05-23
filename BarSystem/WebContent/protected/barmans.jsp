<%@page import="java.util.Date"%>
<%@page import="web.pojos.Drink"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="jms.TopicClient"%>
<%@page import="web.users.OrdersManagement"%>
<%@page import="web.users.DrinksManagement"%>
<%@page import="web.users.UsersManagement"%>
<%@page import="java.util.LinkedList"%>
<%@page import="web.users.UsersManagement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Barman's page</title>
<meta name="description" content="Welcome" />
<meta name="keywords"
	content="css3, login, form, custom, input, submit, button, html5, placeholder" />
<meta name="author" content="Codrops" />

<link rel="shortcut icon" href="/BarMngmtSystem/images/favicon.ico">
<link rel="stylesheet" type="text/css"
	href="/BarMngmtSystem/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="/BarMngmtSystem/css/modalDialog/modalD.css" />
<!-- 
<script src="/fmi/js/modernizr.custom.63321.js"></script>
 -->
<!--[if lte IE 7]><style>.main{display:none;} .support-note .note-ie{display:block;}</style><![endif]-->
<script type="text/javascript">
	function run() {
		var e = document.getElementById('drinkSelectId');
		var strUser = e.options[e.selectedIndex].value;
		return document.getElementById('drinkSelectId').options[document
				.getElementById('drinkSelectId').selectedIndex].text;
	}
</script>
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
			<h2>
				Welcome, barman ...
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
			<form class="form-5"
				action=<%=getServletContext().getContextPath() + "/protected/barmans"%>
				method="post">
				<input type="submit" name="action" value="hangout">
			</form>
		</section>
		<div>
			<p>// A table of all "PENDING" and "OVERDUE" orders! With select
				an order via a radio btn -> "accept" btn -> modal dialog for this
				order -> "done" btn...</p>
			<a href="#acceptAnOrderModal">Accept an order</a>
			<div id="acceptAnOrderModal" class="modalDialog">
				<div>
					<a href="#close" title="Close" class="close">X</a>
					<form name="acceptAnOrder"
						action=<%=getServletContext().getContextPath() + "/protected/barmans"%>
						method="post">
						<h4>Accepted an order id:...</h4>
						<p>
							- Show the ingredients of every drink in the order <input
								type="submit" name="action" value="Done">
					</form>
				</div>
			</div>
		</div>

		<p>
			<br>
			<br>
		</p>

		<div>
			<p>// Add a drink via a modal dialog form?</p>
			<a href="#addADrinkModal">Add a new drink</a>
			<div id="addADrinkModal" class="modalDialog">
				<div>
					<a href="#close" title="Close" class="close">X</a>
					<form name="addADrinkForm"
						action=<%=getServletContext().getContextPath() + "/protected/barmans"%>
						method="post">
						<h4>Add a drink</h4>
						<p>
							<label>Name</label> <input type="text" name="drinkName" required>
						</p>
						<p>
							<label>Ingredients</label> <input type="text" name="ingredients"
								placeholder="ingredient,ml;[...]" required>
						</p>
						<p>
							<label>Price</label> <input type="text" name="price" required>
						</p>
						<input type="submit" name="action" value="Add Drink">
					</form>
				</div>
			</div>
			<!-- end of modal dialog  -->
		</div>
	</div>



</body>
</html>