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

<link rel="stylesheet" type="text/css" href="/BarMngmtSystem/css/style.css" />
<link rel="stylesheet" type="text/css" href="/BarMngmtSystem/css/visibility/visibleIf.css" />
<script type="text/javascript" src="/BarMngmtSystem/js/visibility/visibleIf.js"></script>
<script type="text/javascript" src="/BarMngmtSystem/js/visibility/EventHelpers.js"></script>

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

.modalDialog {
	position: fixed;
	font-family: Arial, Helvetica, sans-serif;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	background: rgba(0,0,0,0.8);
	z-index: 99999;
	opacity:0;
	-webkit-transition: opacity 20ms ease-in;
	-moz-transition: opacity 20ms ease-in;
	transition: opacity 20ms ease-in;
	pointer-events: none;
}
.modalDialog:target {
	opacity:1;
	pointer-events: auto;
}

.modalDialog > div {
	width: 400px;
	position: relative;
	margin: 10% auto;
	padding: 5px 20px 13px 20px;
	border-radius: 10px;
	background: #fff;
	background: -moz-linear-gradient(#fff, #999);
	background: -webkit-linear-gradient(#fff, #999);
	background: -o-linear-gradient(#fff, #999);
}
</style>
<script type="text/javascript">
 function run() {
     return selectedDrink.value;
 }
 </script>
</head>
<body>	
	<div class="container">
		<header>
			<h2>Welcome, waiter ... <%=session.getAttribute("username")%> </h2>
			<div class="support-note">
				<span class="note-ie">Sorry, only modern browsers.</span>
			</div>
		</header>
		
		<section class="main">
			<!-- what method should be used in order not to see the username/password in the url? -->
			<form class="form-5" action=<%=getServletContext().getContextPath() + "/public/controller"%> method="post">
					<input type="submit" name="action" value="logout">
			</form>
			
		</section>
		<div>
			<p>
				<label>Choose an action:</label>
				<select name="barmanActionSelect">
					<option value="addClient" selected>Create a new consumer</option>
					<option value="addOrder">Add a new order</option>
				</select> 
			</p>
			<form action="createConsumerForm" >
				<p>
					<label>Place</label> 
					<input type="text" name="place" placeholder="Place/Table" required>
				</p>
				<p>
					<label>Date</label> 
					<input type="text" name="date" disabled="disabled" contenteditable="false" value="<%= new Date()%>">
				</p>
			</form>
			<form name="addAnOrderForm" action=<%=getServletContext().getContextPath() + "/protected/waiters"%> method="post">
				<h4>Add an order</h4>
				<p>
					<label>Consumer</label> 
					<input type="text" name="consumer"  required>
				</p>
				<p>
					<label>Status</label> 
					<input type="text" name="status" disabled="disabled" contenteditable="false" value="Pending">
				</p>
			</form>
			<% 
				DrinksManagement drinks = (DrinksManagement) getServletContext().getAttribute("drinksM");
				Map<String, Drink> map = drinks.getAllDrinks();
			%>
			<form name="drinkCountForm" oninput="bill.value=(drinkSelectId.value)*parseInt(drinkCountId.value)"> <!-- take the price of the selected drink!!!  -->
				<p>
					<label>Drink</label>
					<select name="drinkSelect" id="drinkSelectId" ><!-- onclick="selectedDrink.value=drinkSelectId.value" >  -->
					<%
					int i=1;
						for(Entry<String, Drink> e : map.entrySet()) {
							out.print("<option value="+ i +">" + e.getKey() + "</option>");
							i++;
						}
					%>
					</select>
					<a href="#openModal">(Ingredients)</a>
					<label>Count</label> 
					<input type="number" id="drinkCountId" name="count" placeholder="Count of drink" min="1" required >
				</p>					
				<div id="openModal" class="modalDialog">
					<div>
						<a href="#close" title="Close" class="close">X</a>
						<script>
							document.write("<h2>Ingredients of '"+ document.getElementByName("drinkSelect").value +"'</h2>");
							document.write("<p>... </p>");
						</script>
					</div>
				</div>
				<label>Bill</label>
				<output name="bill" for="drinkSelectId drinkCountId"></output>
			</form>
			<input type="submit" name="action" value="Add Order">
		</div>
	</div>

</body>
</html>