/* When the document is ready then add watchers for different events */
$(document).ready(function(){

    // Get token from main.js
    const jwtBearerToken = getToken();

	//display user info on the account page using the token
     const url = "./library/getmember";	 
	 var getting = $.ajax({
         type: 'get',
         url: url,
         contentType: "application/json; charset=utf-8",
         beforeSend: function (xhr){ 
        	xhr.setRequestHeader('Authorization', 'Bearer ' + jwtBearerToken); 
    	 },
     });
         
     getting.done(function( result ) {
		console.log("Successfully got details");
		document.getElementById("name").innerHTML = result.firstname + " " + result.surname;
        document.getElementById("email").innerHTML = result.email;
	})
	
	getting.fail(function(data) {
		console.log(data)
		alert("issue getting profile")
	})
	
	 const urlRecords = "./library/getrecordsByuser";	 
	 var gettingRecords = $.ajax({
         type: 'get',
         url: urlRecords,
         contentType: "application/json; charset=utf-8",
         beforeSend: function (xhr){ 
        	xhr.setRequestHeader('Authorization', 'Bearer ' + jwtBearerToken); 
    	 },
     });
	
	 gettingRecords.done(function( result ) {
		console.log("Successfully got records");
		console.log(result)
		
		if (result.length === 0){
	      $("#account-records").append("<p>No records yet</p>");
	    } else {
	      var content = '<table id="records-table">';
	      content += '<tr>'
	      content += '<th>ID</th>'
	      content += '<th>Book ID</th>'
	      content += '<th>Borrowed date</th>'
	      content += '<th>Due date</th>'
	      content += '<th>is Returned?</th>'
	      content += '</tr>'
	      for (var i=0; i < result.length; i++){
	        row = '<tr>';
	        row += '<td>' + result[i].id + '</td>';
	        row += '<td>' + result[i].bookId + '</td>';
	        row += '<td>' + result[i].borrowed_date + '</td>';
	        row += '<td>' + result[i].due_date + '</td>';
	        row += '<td>' + result[i].isReturned + '</td>';
	        row += '</tr>';
	        content += row;
	      }
	      content += "</table>"
	      $("#account-records").append(content);
	    }
	})
	
	gettingRecords.fail(function(data) {
		console.log(data)
		alert("issue getting records")
	})
})



