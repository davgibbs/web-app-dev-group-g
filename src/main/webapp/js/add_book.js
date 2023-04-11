function doFetch() // function that utilizes fetch command add book to library
{
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({ // create book
        "date": document.getElementById('date').value,
        "author": document.getElementById('author').value,
        "available": document.getElementById('availability').value,
        "image": null,
        "isbn": document.getElementById('isbn').value,
        "title": document.getElementById('title').value
    });

    var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    fetch("http://localhost:8080/library/addbook", requestOptions) // fetch command to add
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}