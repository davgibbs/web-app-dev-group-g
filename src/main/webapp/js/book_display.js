const requestOptions = {
    method: 'GET',
    redirect: 'follow'
};

fetch("./library/getallbooks", requestOptions) // get all books, then pass them to row handler to display book in table
    .then(response => response.json())
    .then(result => {
        for (const i of result) {
            rowHandler(i);
        }
})
    .catch(error => console.log('error', error));

function rowHandler(data){ // format information form fetch command into table
    const table = document.getElementById("example");
    let row = table.insertRow();

    row.insertCell(0).innerHTML = data.isbn;
    row.insertCell(1).innerHTML = `<a href="/admin_bookview.html?bookid=${data.id}" style="color: black; text-decoration: none;">${data.title}</a>`
    row.insertCell(2).innerHTML = data.author;
    row.insertCell(3).innerHTML = data.publish_date;
}
