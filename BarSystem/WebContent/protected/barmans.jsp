<%@page import="ejb.Barman"%>
<%@page import="constants.OrderStatus"%>
<%@page import="web.pojos.Order"%>
<%@page import="java.util.List"%>
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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
<link rel="stylesheet" type="text/css"
	href="/BarMngmtSystem/css/orders/ordersTable.css" />
<!-- 
<script src="/fmi/js/modernizr.custom.63321.js"></script>
 -->
<!--[if lte IE 7]><style>.main{display:none;} .support-note .note-ie{display:block;}</style><![endif]-->

<script type="text/javascript">

/* function run() {
	var e = document.getElementById('drinkSelectId'); // the select element
	return e.options[e.selectedIndex].text; // text or value
} */
	
function setOrderIdFromRadioBtn(e) { // not used!!! otherwise is called on onchange on input/radio // for the modal dialog when accept an order
	document.getElementsByName('acceptedOrderIdName')[0].value=e.value;
}

function setParamAndSubmit() { // it is used to pass the accepted/done orderId to the JSP file!
    var hidden = document.createElement('input');
    hidden.type = "hidden";
	hidden.name = "orderId";
	hidden.value = document.getElementsByName('acceptedOrderIdName')[0].value;
	var f = document.getElementById('acceptAnOrderFormId');
	f.appendChild(hidden);
	f.action=<%= getServletContext().getContextPath() + "/protected/barmans" %>;
	f.submit();
}

function addInput(divName){ // to add new UI compoments (when adding drinks to a new client) !!!! BOOKMARKED 
	  var newdiv = document.createElement('div');
      newdiv.innerHTML = "Entry " + (counter + 1) + " <br><input type='text' name='myInputs[]'>";
      document.getElementById(divName).appendChild(newdiv);
      counter++;
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
				Welcome, barman ... <%=session.getAttribute("username")%></h2>
			<div class="support-note">
				<span class="note-ie">Sorry, only modern browsers.</span>
			</div>
		</header>
		<section class="main">
			<form class="form-5" action=<%=getServletContext().getContextPath() + "/public/controller"%>	method="post">
				<input type="submit" name="action" value="logout">
			</form>
			<form class="form-5"	action=<%=getServletContext().getContextPath() + "/protected/barmans"%> 	method="post">
				<input type="submit" name="action" value="hangout">
			</form>
		</section>
		<div>
			<form name="acceptAnOrderTableForm" id="acceptAnOrderTableFormId" 
				action=<%= getServletContext().getContextPath() + "/protected/barmans.jsp#acceptAnOrderModal" %> method="post">
				<div class="ordersTableDiv">	<!-- starts the orders's table  --> 
					<table>
						<colgroup>
							<col width="20px" />
							<col width="40px" /><col width="80px" /><col width="150px" />
							<col width="100px" /><col width="80px" /><col width="60px" />
							<col width="16px"/>
						</colgroup>
						<tbody>
							<tr>
								<th>&nbsp;&nbsp;0</th>
								<th>&nbsp;&nbsp;#</th>
								<th>&nbsp;&nbsp;&nbsp;&nbsp;Place</th>
								<th>&nbsp;&nbsp;&nbsp;&nbsp;Names</th>
								<th>&nbsp;&nbsp;Count</th>
								<th>&nbsp;&nbsp;&nbsp;&nbsp;Status</th>
								<th>&nbsp;&nbsp;&nbsp;&nbsp;Bill</th>
								<th></th>
							</tr>
						</tbody>
					</table>
					<div class="ordersContentTableDiv">
						<table>
							<colgroup>
								<col width="20px" />
								<col width="40px" /><col width="80px" /><col width="150px" />
								<col width="100px" /><col width="80px" /><col width="60px" />
							</colgroup>
							<tbody>
							    <c:forEach items="${orders}" var="order">
								   <c:if test="${order.status=='OVERDUE'}">
								   		<tr style="background-color: red;">
								   </c:if>
								   <c:if test="${order.status!='OVERDUE'}">
								   		<tr>
								   </c:if>
								   <c:if test="${(order.status=='OVERDUE') || (order.status=='PENDING')}">
								   		<td><INPUT TYPE="radio" NAME="acceptOrderRadioBtn" VALUE="${order.id}"></td>
								   </c:if>
								   <c:if test="${(order.status=='ACCEPTED') || (order.status=='DONE')}">
								   		<td><INPUT TYPE="radio" NAME="acceptOrderRadioBtn" VALUE="${order.id}" disabled="disabled"></td>
								   </c:if>
					                	<%-- <td><INPUT TYPE="radio" NAME="acceptOrderRadioBtn" VALUE="${order.id}"></td> --%>
					                    <td><c:out value="${order.id}" /></td>
					                    <td><c:out value="${order.consumerId.place}" /></td>
					                    <td><c:out value="[pitie1, pitie2,..]" /></td>
					                    <td><c:out value="[c1, c2,...]" /></td>
					                    <td><c:out value="${order.status}" /></td>
					                    <td><c:out value="${order.bill}" /></td>
					               </tr>
					            </c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<input  type="submit" name="action" value="Accept an order"/> 
			</form> 	<!-- ends the orders's table  -->
			
			<div id="acceptAnOrderModal" class="modalDialog" >
				<div>
					<form name="acceptAnOrderForm" id="acceptAnOrderFormId" action=<%= getServletContext().getContextPath() + "/protected/barmans"%> method="post">
						<% 
							String orderID = (String) request.getParameter("acceptOrderRadioBtn"); 
							Barman barman = (Barman) session.getAttribute("loggedUser");
						%>
							<h4> Accepted order id: <% out.print(orderID); %> by <% out.print(barman.getName()); %> </h4>
						<% 
							if (orderID != null) {
								Order order = barman.getOrderById(Integer.valueOf(orderID.trim()));
								if (order != null) {
					        	    barman.acceptAnOrder(order);
					        	    for(Entry<Drink, Integer> e : order.getDrinks().entrySet()) {
					        			out.write("<p>"+ e.getKey().getName() + " (" + e.getKey().getIngredients() + "), count: " + e.getValue() + "</p>");
					        	    }
			    		       	 }
							}
						%>
						<input name="acceptedOrderIdName" hidden="true" value=<% out.print(orderID); %>></input>
						<input  type="submit" name="action" value="Done" /> 
					</form>
				</div>
			</div>
		</div>
		<p>
			<br> <br>
		</p>
		<div>
			<p>// Add a drink via a modal dialog form?</p>
			<a href="#addADrinkModal">Add a new drink</a>
			<div id="addADrinkModal" class="modalDialog">
				<div>
					<a href="#close" title="Close" class="close">X</a>
					<form name="addADrinkForm" action=<%= getServletContext().getContextPath() + "/protected/barmans" %> method="post">
						<h4>Add a drink</h4>
						<p>
							<label>Name</label> 
							<input type="text" name="drinkName" required>
						</p>
						<p>
							<label>Ingredients</label>
							<input type="text" name="ingredients" placeholder="ingredient,ml;[...]" required>
						</p>
						<p>
							<label>Price</label>
							<input type="text" name="price" required>
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