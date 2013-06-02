// Waiter's page JS

function getCheckedRadioBtn() { // not used!
    for (var i = 0; i < document.getElementsByName('radioAction').length; i++) {
   		if(document.getElementsByName('radioAction')[i].checked == true) {
        	return document.getElementsByName('radioAction')[i].value;
       	}
    }
}

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
		$("#placeId").prop("required", false);
	} else {
		document.getElementById('consumerComboId').style.display = "block";
		$("#placeId").prop("required", true);
	}
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

// refresh orders' table content
var ordersTableAutoRefresh = setInterval(  
	 function ()  
	 {  
	     $('#ordersTable').load('http://localhost:8080/BarMngmtSystem/LoadOrders').fadeIn("slow");
	   /*$.get('http://localhost:8080/BarMngmtSystem/LoadOrders', function(data) {
	    	 $('#ordersTable').html(data);
	    	}); */
		 }, 10000
);

