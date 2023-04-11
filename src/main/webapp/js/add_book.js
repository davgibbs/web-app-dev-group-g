function doFetch()
{
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

    var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    console.log("called");
    fetch("http://localhost:8080/library/addbook", requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}