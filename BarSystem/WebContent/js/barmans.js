// Barman's page JS

/* function run() {

	var e = document.getElementById('drinkSelectId'); // the select element
	return e.options[e.selectedIndex].text; // text or value
} */
	
function setOrderIdFromRadioBtn(e) { // not used!!! otherwise is called on onchange on input/radio // for the modal dialog when accept an order
	document.getElementsByName('orderIDRadioBtn')[0].value=e.value;
}

function addInput(divName){ // not used!!! to add new UI compoments (when adding drinks to a new client) !!!! BOOKMARKED 
	  var newdiv = document.createElement('div');
      newdiv.innerHTML = "Entry " + (counter + 1) + " <br><input type='text' name='myInputs[]'>";
      document.getElementById(divName).appendChild(newdiv);
      counter++;
}
	 
 var bordersTableAutoRefresh = setInterval(  
	 function ()  
	 {  
	     $('#ordersTable').load('http://192.168.0.2:8080/BarMngmtSystem/LoadBarmansOrders?currUser='+$("#cUser").val()).fadeIn("slow");
	 }, 10000
 );

 
// orders alert notifications...
 
 var ordersAlertNotif = setInterval(  
	 function ()  
	 {  
		$.get('http://192.168.0.2:8080/BarMngmtSystem/OrdersAlert', function (data) { /* change the host!!! 130.185.253.67 */
			alert("asdf");
			if(data.size != '0') {
				$("#alertOrderP").html(data.arr);
				$("#aId").trigger("click");			
			}
		}, "json");
	 }, 5000
 );
 
 

 var auto_show_msg = setInterval(  
		 function ()  
		 {  
		   //  $("#aId").trigger("click");
		 }, 5000
	 );
 
 function showMessage(type)
 {
 	$('.'+ type +'-trigger').click(function(){
 		  hideAllMessages();				  
 		  $('.'+type).animate({top:"0"}, 500);
 	});
 }
 
 function hideAllMessages()
 {
	  //$('.' + 'info').outerHeight(); // fill array
	  $('.' + 'info').css('top', -500); //move element outside viewport	  
 }
 
$(document).ready(function(){
	 // Initially, hide them all
	 hideAllMessages();
	 
	 // Show message
	 showMessage('info');
	 
	 // When message is clicked, hide it
	 $('.message').click(function(){			  
			  $(this).animate({top: "500"}, 0);
	  });		 
	 
});  
