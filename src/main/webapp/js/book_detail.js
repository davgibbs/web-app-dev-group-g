$(document).ready(function(){

	// Try to get the ID from the URL
	var urlParams = new URLSearchParams(window.location.search);
	var bookId = urlParams.get('id');
	console.log("Book ID: "+ bookId);
	
	
	 const url = "./library/getbook/" + bookId;
     // Get token from main.js
     //const jwtBearerToken = getToken();	 
	 var getRequest = $.ajax({
	     type: 'get',
	     url: url,
	     contentType: "application/json; charset=utf-8",
	     /*beforeSend: function (xhr){ 
			xhr.setRequestHeader('Authorization', 'Bearer ' + jwtBearerToken); 
	     },*/
	 });
	 getRequest.done(function( data ) {
		 console.log(data);
		 $('#title').text(data.title);
		 $('#book-description').text(data.description);
		 $('#book-image').attr("src", data.image_path);
		 $('#author').text("By " + data.author);
		 $('#date-published').text("Published " + data.publish_date);
	 })
	
	
    /*
        const available = document.getElementById("available");
        const availableBtn = document.getElementById("checkOut");
        
        
        if(available === "yes"){
          available.innerText = "Currently available"
          availableBtn.innerText = "Borrow"
        }
        else{
          available.innerText = "Currently not available"
          availableBtn.innerText = "Checked out"
        }
      })*/

})