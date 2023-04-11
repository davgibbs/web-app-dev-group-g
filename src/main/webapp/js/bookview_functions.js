const params = new Proxy(new URLSearchParams(window.location.search), { // find link value
    get: (searchParams, prop) => searchParams.get(prop),
});

let ISBNvalue = params.bookisbn; // "store it here"

const requestOptionsForPopulation = {
    method: 'GET',
    redirect: 'follow'
};

fetch("http://localhost:8080/library/getallbooks", requestOptionsForPopulation)
    .then(response => response.json())
    .then(result => {
        for (const i of result) {
            if (i.isbn == ISBNvalue){
                prepopulate(i);
            }
        }
    })
    .catch(error => console.log('error', error));

function prepopulate(book){
    document.getElementById("bookViewTitle").innerText = book.title;
    document.getElementById("isbn").value = book.isbn;
    document.getElementById("title").value = book.title;
    document.getElementById("author").value = book.author;
    document.getElementById("availability").value = book.available;
    document.getElementById("date").value = book.date;
}

function deleteBook() {
    var requestOptions = {
        method: 'DELETE',
        redirect: 'follow'
    };
    var delURL = "http://localhost:8080/library/deletebook/" + ISBNvalue;


    fetch(delURL, requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}

function addBook() {
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({
        "date": document.getElementById('date').value,
        "author": document.getElementById('author').value,
        "available": document.getElementById('availability').value,
        "image": null,
        "isbn": document.getElementById('isbn').value,
        "title": document.getElementById('title').value
    });

    var requestOptionsAdd = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    console.log("called");
    fetch("http://localhost:8080/library/addbook", requestOptionsAdd)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}

function updateBook() {
    deleteBook();
    addBook();
}