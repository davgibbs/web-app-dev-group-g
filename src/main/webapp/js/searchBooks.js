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
	const selectVal = document.getElementById("searchType").value;
	var requestOptions = {
  		method: 'GET',
  		redirect: 'follow'
	};

	fetch("http://localhost:8080/library/gettitle?title=${title}", requestOptions)
  	.then(data => {
    	// Populate table with data
    	data.forEach(obj => {
      		for (const i of obj) {
            	insertRow(i);
        	}
    	});
  	})
  	.catch(error => console.error(error));
}

function searchAuthor(){
	const author = document.getElementById("searchInput").value;
	const selectVal = document.getElementById("searchType").value;
	var requestOptions = {
  		method: 'GET',
  		redirect: 'follow'
	};

	fetch("http://localhost:8080/library/getauthor?author=${author}", requestOptions)
  	.then(data => {
    	// Populate table with data
    	data.forEach(obj => {
      		for (const i of obj) {
            	insertRow(i);
        	}
    	});
  	})
  	.catch(error => console.error(error));
}

function insertRow(obj){
    const tbody = document.getElementById(".searchBookTable tbody");
    const row = tbody.insertRow();
    const titleCell = row.insertCell();
    const authorCell = row.insertCell();
    const isbnCell = row.insertCell();
    const dateCell = row.insertCell();
    const availableCell = row.insertCell();

    titleCell.innerText = obj.title;
    authorCell.innerText = obj.author;
    isbnCell.innerText = obj.isbn;
    dateCell.innerText = obj.date;
   	availableCell.innerText = obj.available;
}