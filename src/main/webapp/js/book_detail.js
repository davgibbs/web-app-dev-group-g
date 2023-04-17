$(document).ready(function(){

	// Try to get the ID from the URL
	var urlParams = new URLSearchParams(window.location.search);
	var bookId = urlParams.get('id');
	console.log("Book ID: "+ bookId);
	
	 const url = "./library/getbook/" + bookId;
     // Get token from main.js
     const jwtBearerToken = getToken();	 
	 var getRequest = $.ajax({
	     type: 'get',
	     url: url,
	     contentType: "application/json; charset=utf-8",
	     beforeSend: function (xhr){ 
			xhr.setRequestHeader('Authorization', 'Bearer ' + jwtBearerToken); 
	     },
	 });
	 getRequest.done(function( data ) {
		 console.log(data);
		 
		 $('#title').text(data.title);
		 $('#book-description').text(data.description);
		 $('#book-image').attr("src", data.image_path);
		 $('#author').text("By " + data.author);
		 $('#date-published').text("Published " + data.publish_date.slice(0, 3));  // Get Year only
		 
		 // Check whether it is available using records
		 const url = "./library/getrecordsBybook/" + bookId;
		 var getRecordRequest = $.ajax({
		     type: 'get',
		     url: url,
		     contentType: "application/json; charset=utf-8",
		     beforeSend: function (xhr){ 
				xhr.setRequestHeader('Authorization', 'Bearer ' + jwtBearerToken); 
		     },
		 });
	 
	 	 getRecordRequest.done(function( data ) {
			console.log("records")
			console.log(data)
			var isAvailable = true;
			// iterate through and see if any record says not returned
			for (var i=0; i < data.length; i++){
				if (data[i].isReturned == false) {
					isAvailable = false;
				}
			}
			
			if (isAvailable === true) {
				$('#author').text("Currently available");
				$('#checkOut').text("Borrow");
			}
			else {
				$('#author').text("Currently not available");
				$('#checkOut').attr('disabled', true);
				$('#checkOut').text("Checked out");
			}
			
		  })
	 })
	 
	 // Watch for a click of the button to borrow a book
	 $("#checkOut").click(function(event) {
		console.log(event)
		event.preventDefault(); 
		console.log("borrow book: " + bookId)
		
		 // Send request to update records
		 const url = "./library/borrow/" + bookId;
		 // Get token from main.js
	     const jwtBearerToken = getToken();
		 var borrowBookRequest = $.ajax({
		     type: 'get',
		     url: url,
		     contentType: "application/json; charset=utf-8",
		     beforeSend: function (xhr){ 
				xhr.setRequestHeader('Authorization', 'Bearer ' + jwtBearerToken); 
		     },
		 });
		 
		 borrowBookRequest.done(function( data ) {
			 console.log(data)
			 alert("Successfully borrowed book ID: " + bookId)
	  	  })
	  	  
	  	 borrowBookRequest.fail(function( data ) {
			 console.log(data)
			 alert("Issue occured borrowing book ID: " + bookId)
	  	  })

	})
})