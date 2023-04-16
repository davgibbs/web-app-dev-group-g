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
	 
	$("#update-delete-book").submit(function(event) {
		
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

function deleteBook() { // use fetch command to delete book
    var requestOptions = {
        method: 'DELETE',
        redirect: 'follow'
    };
    var delURL = "./library/deletebook/" + ISBNvalue; // string concatenation with isbn val


    fetch(delURL, requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}


function updateBook() { // updating a book consists of deleting the old one and adding with the present form content
// todo add a PUT request
    deleteBook();
    addBook();
}