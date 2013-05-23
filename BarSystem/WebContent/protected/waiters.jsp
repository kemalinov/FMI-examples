<%@page import="java.util.Date"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="web.pojos.Drink"%>
<%@page import="java.util.Map"%>
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
<title>Welcome</title>
<meta name="description" content="Welcome" />
<meta name="keywords"
	content="css3, login, form, custom, input, submit, button, html5, placeholder" />
<meta name="author" content="Codrops" />

<link rel="stylesheet" type="text/css"
	href="/BarMngmtSystem/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="/BarMngmtSystem/css/visibility/visibleIf.css" />
<link rel="stylesheet" type="text/css"
	href="/BarMngmtSystem/css/modalDialog/modalD.css" />

<script type="text/javascript"
	src="/BarMngmtSystem/js/visibility/visibleIf.js"></script>
<script type="text/javascript"
	src="/BarMngmtSystem/js/visibility/EventHelpers.js"></script>

<link rel="icon" href="/BarMngmtSystem/images/favicon.ico">
<!--<script src="/fmi/js/modernizr.custom.63321.js"></script>
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
<script type="text/javascript">

	function getDrinkPrice() { // get the selected drink id!
		var e = document.getElementById('drinkSelectId');
		// return e.options[e.selectedIndex].value; // returns the "value"
		// return e.options[e.selectedIndex].text; // returns the "text" (selected item)
		return parseFloat(e.options[e.selectedIndex].value);
	}
	
</script>
</head>
<body>
	<div class="container">
		<header>
			<h2>
				Welcome, waiter ...
				<%=session.getAttribute("username")%>
			</h2>
			<div class="support-note">
				<span class="note-ie">Sorry, only modern browsers.</span>
			</div>
		</header>

		<section class="main">
			<!-- what method should be used in order not to see the username/password in the url? -->
			
			<form class="form-5"
				action=<%=getServletContext().getContextPath() + "/public/controller"%>
				method="post">
				<input type="submit" name="action" value="logout">
			</form>

		</section>
		<div>
			<p>
				<label>Choose an action:</label> <select name="barmanActionSelect">
					<option value="addClient" selected>Create a new consumer</option>
					<option value="addOrder">Add a new order</option>
				</select>
			</p>
			<form action="createConsumerForm">
				<p>
					<label>Place</label> <input type="text" name="place"
						placeholder="Place/Table" required>
				</p>
				<p>
					<label>Date</label> <input type="text" name="date"
						disabled="disabled" contenteditable="false"
						value="<%=new Date()%>">
				</p>
			</form>
			<form name="addAnOrderForm"
				action=<%=getServletContext().getContextPath() + "/protected/waiters"%>
				method="post">
				<h4>Add an order</h4>
				<p>
					<label>Consumer /combo box of ACTIVE client places/</label> <input
						type="text" name="consumer" required>
				</p>
				<p>
					<label>Status</label> <input type="text" name="status"
						disabled="disabled" contenteditable="false" value="Pending">
				</p>
			</form>
			<%
			    DrinksManagement drinks = (DrinksManagement) getServletContext().getAttribute("drinksM");
			    Map<String, Drink> map = drinks.getAllDrinks();
			%>
			<form name="drinkCountForm" onclick="bill.value=(getDrinkPrice()*parseInt(countPerDrinkId.value)).toFixed(2)">
				<!-- take the price of the selected drink!!!  -->
				<p>
					<label>Drink</label> <select name="drinkSelect" id="drinkSelectId"
						onclick="drinkCountForm.oniput()">
						<option value="0" selected>Select a drink</option>
						<%
						    for (Entry<String, Drink> e : map.entrySet()) {
								out.print("<option value=" + e.getValue().getPrice() + ">" + e.getKey() + "</option>");
						    }
						%>
					</select> 
					<label>Count:</label> 
					<input type="number" id="countPerDrinkId" name="count" min="1" value="1" required>
				</p>
				<!-- here was placed an ingredients modal dialog!  -->
				<label>Bill</label>
				<output name="bill" for="drinkSelectId drinkCountId"></output>
			</form>
			<input type="submit" name="action" value="Add Order">
		</div>
	</div>


	<!-- <a href="#openModal">(Ingredients)</a>					
				<div id="openModal" class="modalDialog">
					<div>
						<a href="#close" title="Close" class="close">X</a>
						<script>
							document.write("<h2>Ingredients of '"+ document.getElementByName("drinkSelect").value +"'</h2>");
							document.write("<p>... </p>");
						</script>
					</div>
				</div> -->
</body>
</html>