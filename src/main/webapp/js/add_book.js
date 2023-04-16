/* When the document is ready then add watchers for different events */
$(document).ready(function(){
	

  /* Watch for a submit event for the main registration form */
  $("#form-new-book").submit(function(event) {
	  
	console.log("Handler for .submit() called new book.");
    event.preventDefault(); 

    var raw = JSON.stringify({ // create book
        "publish_date": document.getElementById('publish-date').value,
        "author": document.getElementById('author').value,
        "description": document.getElementById('description').value,
        "isbn": document.getElementById('isbn').value,
        "title": document.getElementById('title').value
    });
    
    // Get token from main.js
    const jwtBearerToken = getToken();

     const url = "./library/addbook";	 
	 var posting = $.ajax({
         type: 'post',
         url: url,
         data: raw,
         contentType: "application/json; charset=utf-8",
         beforeSend: function (xhr){ 
        	xhr.setRequestHeader('Authorization', 'Bearer ' + jwtBearerToken); 
    	 },
     });

	posting.done(function( data ) {
	  alert("Successfully added new book");
	  window.location.href = "./admin.html"
	  
	})
	  
	posting.fail(function( data ) {
	  console.log(data);
	})
  
  })
})