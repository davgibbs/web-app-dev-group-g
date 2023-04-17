$(document).ready(function(){
	const params = new Proxy(new URLSearchParams(window.location.search), { // find ID of book
	    get: (searchParams, prop) => searchParams.get(prop),
	});
	
	let iDvalue = params.bookid; // store it here
	
	const requestOptionsForPopulation = {
	    method: 'GET',
	    redirect: 'follow'
	};
	
	fetch("./library/getallbooks", requestOptionsForPopulation) // get all data
	    .then(response => response.json())
	    .then(result => {
	        for (const i of result) {
	            if (i.id == iDvalue){ // filter the data so that it matches ID
	                prepopulate(i);
	            }
	        }
	    })
	    .catch(error => console.log('error', error));
	 
	$("#deleteBook").click(function(event) {		
       	console.log("Handler for .submit() called delete book.");
        event.preventDefault(); 
    	var delURL = "./library/deletebook/" + iDvalue; // string concatenation with id val
	    // Get token from main.js
	    const jwtBearerToken = getToken();
		  // use ajax command to delete book
		 var deleting = $.ajax({
	         type: 'delete',
	         url: delURL,
	         contentType: "application/json; charset=utf-8",
	         beforeSend: function (xhr){ 
	        	xhr.setRequestHeader('Authorization', 'Bearer ' + jwtBearerToken); 
	    	 },
	     });
	
		deleting.done(function( data ) {
		  alert("Successfully deleted book");
		  window.location.href = "./admin.html"	  
		})
		  
		deleting.fail(function( data ) {
		  console.log(data)
		  alert("Failed to delete book");
		  window.location.href = "./admin.html"
		})
	})
	
    $("#updateBook").click(function(event) {
       	console.log("Handler for .submit() called update book.");
        event.preventDefault(); 
    	var updateURL = "./library/modifybook/" + iDvalue; // string concatenation with id val
    	
    	var raw = JSON.stringify({ // create book
	        "publish_date": document.getElementById('date_publish').value,
	        "author": document.getElementById('author').value,
	        "description": document.getElementById('description').value,
	        "isbn": document.getElementById('isbn').value,
	        "title": document.getElementById('title').value
	    });
	    // Get token from main.js
	    const jwtBearerToken = getToken();
		  // use ajax command to delete book
		 var putting = $.ajax({
	         type: 'PUT',
	         url: updateURL,
	         data: raw,
	         contentType: "application/json; charset=utf-8",
	         beforeSend: function (xhr){ 
	        	xhr.setRequestHeader('Authorization', 'Bearer ' + jwtBearerToken); 
	    	 },
	     });
	
		putting.done(function( data ) {
		  alert("Successfully updated book");
		  window.location.href = "./admin.html"	  
		})
		  
		putting.fail(function( data ) {
		  console.log(data)
		  alert("Failed to update book");
		  window.location.href = "./admin.html"
		})
	})

})

function prepopulate(book){ // insert title and prepopulate the table for ease of editing
    document.getElementById("bookViewTitle").innerText = book.title;
    document.getElementById("isbn").value = book.isbn;
    document.getElementById("title").value = book.title;
    document.getElementById("author").value = book.author;
    document.getElementById("description").value = book.description;
    document.getElementById("date_publish").value = book.publish_date;
}


function updateBook() { // updating a book consists of deleting the old one and adding with the present form content
// todo add a PUT request
    deleteBook();
    addBook();
}