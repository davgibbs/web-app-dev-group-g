const params = new Proxy(new URLSearchParams(window.location.search), { // find link value
    get: (searchParams, prop) => searchParams.get(prop),
});

let value = params.bookname; // "store it here"

document.getElementById("bookViewTitle").innerText = value;

const requestOptionsForPopulation = {
    method: 'GET',
    redirect: 'follow'
};

fetch("http://localhost:8080/library/getallbooks", requestOptionsForPopulation)
    .then(response => response.json())
    .then(result => {
        for (const i of result) {
            if (i.title == value){
                prepopulate(i);
            }
        }
    })
    .catch(error => console.log('error', error));

function prepopulate(book){
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

    var tempString = "http://localhost:8080/library/deletebook/"

    tempString += document.getElementById("isbn").value;

    fetch(tempString, requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}