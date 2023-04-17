$(document).ready(function(){
	 // get all books, then pass them to row handler to display book in table

	 const url = "./library/getallrecords";
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
		 console.log(data)
		 var table = $("#records");
		 
		 for (var i=0; i < data.length; i++){
			row = '<tr>';
	        row += '<td>' + data[i].id + '</td>';
	        row += '<td>' + data[i].memberid + '</td>';
	        row += '<td>' + data[i].bookId + '</td>';
	        row += '<td>' + data[i].borrowed_date + '</td>';
	        row += '<td>' + data[i].due_date + '</td>';
	        row += '<td>' + data[i].isReturned + '</td>';
	        row += '</tr>';
	        table.append(row);
		 }
	        
		 
	 })
})