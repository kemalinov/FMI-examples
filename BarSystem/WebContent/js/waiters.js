// Waiter's page JS

function getCheckedRadioBtn() { // not used!
    for (var i = 0; i < document.getElementsByName('radioAction').length; i++) {
   		if(document.getElementsByName('radioAction')[i].checked == true) {
        	return document.getElementsByName('radioAction')[i].value;
       	}
    }
}

var ordersTableAutoRefresh = setInterval(  
	 function ()  
	 {  
	     $('#ordersTable').load('http://192.168.0.2:8080/BarMngmtSystem/LoadWaitersOrders?currUser='+$("#cUser").val()).fadeIn("slow"); /*(link, {...,...}) for POST*/
	   /*$.get('http://localhost:8080/BarMngmtSystem/LoadOrders', function(data) {
	    	 $('#ordersTable').html(data);
	    	}); */
		 }, 10000
);

function showClientDiv(showClientUIStyle) {
	document.getElementById('clientDivId').style.display = showClientUIStyle;
	if (showClientUIStyle == "block") { /* to show consumer and data fields  */
		document.getElementById('consumerComboId').style.display = "none"; /* hides the consumers' place combo */
		$("#placeId").prop("required", true);
	} else {
		document.getElementById('consumerComboId').style.display = "block";
		$("#placeId").prop("required", false);
	}
}


function getDrinkPrice() { // get the selected drink id!
	var e = document.getElementById('drinkSelectId');
	// return e.options[e.selectedIndex].value; // returns the "value"
	// return e.options[e.selectedIndex].text; // returns the "text" (selected item)
	return parseFloat(e.options[e.selectedIndex].value);
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

