/* When the document is ready then add watchers for different events */
$(document).ready(function(){
  $("#search-books").submit(function(event) {
    console.log("Handler for .submit() called");
    event.preventDefault();  // Prevent the default behaviour of sending the data to a backend server
    
    searchTitle();
    })
});

function searchEngine(){
	const selectVal = document.getElementById("searchType").value;
	if(selectVal === "Title"){
		window.location.href = "searchBooksTitle.html";
		searchAuthor();
	}
	else{
		window.location.href = "searchBooksAuthor.html";
		searchTitle();
	}
}

function searchTitle(){
 
	const title = document.getElementById("searchInput").value;
	//const selectVal = document.getElementById("searchType").value;
	// may choose author

    // Get token from main.js
    const jwtBearerToken = getToken();

    const url = "./library/getbooksByTitle?title=" + title;	 
	var getting = $.ajax({
        type: 'get',
        url: url,
        contentType: "application/json; charset=utf-8",
        beforeSend: function (xhr){ 
        	xhr.setRequestHeader('Authorization', 'Bearer ' + jwtBearerToken); 
    	},
    });
    
    
    getting.done(function( data ) {
	  var results = data;
	  $("#display-results").empty();
	  
	  if (results.length === 0){
	      $("#display-results").append("<p>No results for query</p>");
	    } else {
	      var content = '<table id="result-table" class="searchTable">';
	      content += '<tr>'
	      content += '<th>Title</th>'
	      content += '<th>Author</th>'
	      content += '<th>Publish Date</th>'
	      content += '<th>ISBN</th>'
	      content += '<th>View</th>'
	      content += '</tr>'
	      for (var i=0; i < results.length; i++){
			// Add a button with class btnSelect to watch for clicks			
	        row = '<tr data-id="' + results[i].id + '" >';
	        row += '<td>' + results[i].title + '</td>';
	        row += '<td>' + results[i].author + '</td>';
	        row += '<td>' + results[i].publish_date + '</td>';
	        row += '<td>' + results[i].isbn + '&nbsp;  </td>';
	        row += '<td><button class="btnSelect">View</button></td>';
	        row += '</tr>';
	        content += row;
	      }
	      content += "</table>"
	      $("#display-results").append(content);
	      
	      $("#display-results").on('click','.btnSelect',function(){
			  
			 var currentRow = $(this).closest("tr");          
	         var bookId = currentRow.data('id'); // get current row 1st TD data-id value
	         
	         console.log("Getting details for book id: " + bookId);
	         window.location.href = "./book-detail.html?id=" + bookId;
		  })	      
	  }
	})
}


