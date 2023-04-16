$(document).ready(function(){
	console.log('ddd')

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

/*fetch("http://localhost:8080/library/getallrecords", requestOptions) // get all books, then pass them to row handler to display book in table
    .then(response => response.json())
    .then(result => {
        for (const i of result) {
            rowHandler(i);
        }
    })
    .catch(error => console.log('error', error));

function rowHandler(data){ // format information form fetch command into table
    const table = document.getElementById("records");
    let row = table.insertRow();

    row.insertCell(0).innerHTML = data.recordid;
    row.insertCell(1).innerHTML = data.memberid;
    row.insertCell(2).innerHTML = data.isbn;
    row.insertCell(3).innerHTML = data.borrowed_date;
    row.insertCell(4).innerHTML = data.due_date;
    row.insertCell(5).innerHTML = data.overdue;
}*/