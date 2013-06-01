<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormat"%>
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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
<link rel="stylesheet" type="text/css"
	href="/BarMngmtSystem/css/orders/ordersTable.css" />
	
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

function showClientDiv(showClientUIStyle) {
	document.getElementById('clientDivId').style.display = showClientUIStyle;
	if (showClientUIStyle == "block") {
		document.getElementById('consumerComboId').style.display = "none";
	} else {
		document.getElementById('consumerComboId').style.display = "block";
	}
}

function setParamsAndSubmitTheOrder() { // it is used to pass correct parameters
	var f = document.getElementById('createNewOrderFormId');
	/* 	var formAction = document.createElement('input');
	formAction.type = "hidden";
	formAction.name = "actionHid";
	formAction.value = "Create an Order"; */
	/* f.appendChild(formAction); */
	var drinksNum = document.createElement('input');
	drinksNum.type = "hidden";
	drinksNum.name = "orderedDrinkNumber";
	drinksNum.value = document.getElementsByName('drinkSelect').length;
	f.appendChild(drinksNum);
	// add a new param per drink: its name and count of it
	var drSel = document.getElementsByName('drinkSelect');
	var countS = document.getElementsByName('count');
	for (var i=0; i < drSel.length; i++) {
		var dName = document.createElement('input');
		dName.type = "hidden";
		dName.name = "drinkName_"+i;
		dName.value = drSel[i].options[drSel[i].selectedIndex].text;
		
		var dCount = document.createElement('input');
		dCount.type = "hidden";
		dCount.name = "drinkCount_"+i;
		dCount.value = countS[i].value;
		f.appendChild(dName);
		f.appendChild(dCount);
	}
	var dateH = document.createElement('input');
	dateH.type = "hidden";
	dateH.name = "dateHid";
	dateH.value = document.getElementsByName('date')[0].value;
	f.appendChild(dateH);
	f.action=<%= getServletContext().getContextPath() + "/protected/waiters" %>;
	f.submit();
}

function calculateAllBills(){
	var bill = 0;
	var drSel = document.getElementsByName('drinkSelect');
	var countS = document.getElementsByName('count');
	for (var i=0; i < drSel.length; i++) {
		bill += parseFloat(drSel[i].options[drSel[i].selectedIndex].value)*(countS[i].value);
	}
	return bill.toFixed(2);
}

function addComponents() {
    var clone=document.getElementById("drinkP").cloneNode(true);
 	var foo = document.getElementById("drinkChoiceDiv");
	foo.appendChild(clone);   
}

</script>
<script type="text/javascript" src="/BarMngmtSystem/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">  

  var auto_refresh = setInterval(  
	 function ()  
	 {  
	     $('#ordersTable').load('http://localhost:8080/BarMngmtSystem/LoadOrders').fadeIn("slow");
	   /*$.get('http://localhost:8080/BarMngmtSystem/LoadOrders', function(data) {
	    	 $('#ordersTable').html(data);
	    	}); */
	 }, 10000
 );
  
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
			<form class="form-5" action=<%=getServletContext().getContextPath() + "/public/controller"%>	method="post">
				<input type="submit" name="action" value="logout">
			</form>

		</section>
		<div>		<!-- starts the orders's table  --> 
			<p><h4>Choose an order, which client to be closed:</h4>
			<form name="waitersOrdersTableForm" id="waitersOrdersTableFormId"  action=<%= getServletContext().getContextPath() + "/protected/waiters" %> method="post">
				<div class="ordersTableDiv">	
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
					<div id="ordersContentTableDivId" class="ordersContentTableDiv">
						<table id="ordersTable">
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
								   <c:if test="${(order.status=='OVERDUE') || (order.status=='PENDING') || (order.status=='ACCEPTED')}">
								   		<td><INPUT TYPE="radio" NAME="orderIDRadioBtn" VALUE="${order.id}" disabled="disabled"></td>
								   </c:if>
								   <c:if test="${order.status=='DONE'}">
								   		<td><INPUT TYPE="radio" NAME="orderIDRadioBtn" VALUE="${order.id}" ></td>
								   </c:if>
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
				<input  type="submit" name="action" value="Close an order"/> 
			</form> 	
		</div>	<!-- ends the orders's table  -->
		<p>
			<br> <br>
		</p>
		<div>
			<form id="createNewOrderFormId" name="createNewOrderForm" action=<%= getServletContext().getContextPath() + "/protected/waiters" %> 
						onclick="bill.value=calculateAllBills()" method="post"> <!-- (getDrinkPrice()*parseInt(countPerDrinkId.value)).toFixed(2) -->
				<p>
					<h3>Choose an action:</h3>
					<input type="radio" checked="checked" id="addClientRBId" name="radioAction" onclick="showClientDiv('block')" value="0" />&nbsp;Add a client
					<input type="radio" name="radioAction" id="addOrderRBId" onclick="showClientDiv('none')" value="1" />&nbsp;Add an order&nbsp;&nbsp;&nbsp;
			
				<div id="clientDivId">
					<p>
						<label>Place</label>
						<!-- TODO: validate that the current place is "empty"(there is no active client on it!!!) -->
						<input type="text" name="place" placeholder="Place/Table" required > 
					</p>
					<p>
						<label>Date</label> 
						<input type="text" name="date" disabled="disabled" contenteditable="false" value="<%= new SimpleDateFormat("dd/MM/yy hh:mm:ss").format(new Date()) %>" >
					</p>
				</div>
				<div>
					<h4>Add an order</h4>
					<p id="consumerComboId" style="display: none;"> <!-- not to be shown be default  -->
						<label>Consumer:&nbsp;</label> <!-- if one is about to add an order => combo, else => don't show it! -->
						<select name="clientSelect" id="clientSelectId">
							<option value="0" selected>Select a client</option>
							<c:forEach items="${consumers}" var="consumer">
								<option value="${consumer.id}"><c:out value="${consumer.place}" /></option>
				            </c:forEach>
						</select> 
					</p>
					<p>
						<label>Status</label>
						<input type="text" name="status" disabled="disabled" contenteditable="false" value="Pending">
					</p>
					<div id="drinkChoiceDiv" >
						<p id="drinkP">
							<label>Drink</label>
							<select name="drinkSelect" id="drinkSelectId">
								<option value="0" selected>Select a drink</option>
								<%
								    DrinksManagement drinks2 = (DrinksManagement) getServletContext().getAttribute("drinksM");
								    Map<String, Drink> map2 = drinks2.getAllDrinks();
								    for (Entry<String, Drink> e : map2.entrySet()) {
										out.print("<option value=" + e.getValue().getPrice() + ">" + e.getKey() + "</option>");
								    }
								%>
							</select>
							<label>Count:&nbsp;</label>
							<input type="number" id="countPerDrinkId" name="count" min="1" value="1" required />
						</p>
					</div>
					<label>Bill:&nbsp;</label><output name="bill"></output>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value=" + " onClick="addComponents();">
				</div>
				<p>
					<input type="submit" name="action" value="Add" onclick="setParamsAndSubmitTheOrder();" /> <!--submit;  -->
				</p>
			</form>
		</div>
	</div>
</body>
</html>

<!--
<a href="#openModal">(Ingredients)</a>					
<div id="openModal" class="modalDialog">
	<div>
		<a href="#close" title="Close" class="close">X</a>
		<script>
			document.write("<h2>Ingredients of '"+ document.getElementByName("drinkSelect").value +"'</h2>");
			document.write("<p>... </p>");
		</script>
	</div>
</div>
 -->