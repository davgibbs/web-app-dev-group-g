const requestOptions = { // effectively the same file as book_display
    method: 'GET',
    redirect: 'follow'
};

fetch("http://localhost:8080/library/getallrecords", requestOptions) // get all books, then pass them to row handler to display book in table
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
}