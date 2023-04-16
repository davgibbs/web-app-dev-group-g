
//display user info on the account page using the id stored in session storage
const requestOptions = {
    method: 'GET',
    redirect: 'follow'
};

function getUserInfo(id){
    fetch("http://localhost:8080/library/getmember/${id}",requestOptions)
    .then(response => response.json())
    .then(result =>{
        document.getElementById("name").innerHTML = result.name;
        document.getElementById("surname").innerHTML = result.surname;
        document.getElementById("email").innerHTML = result.email;
    })
    .catch(error => console.log('error', error));

    fetch("http://localhost:8080/library/getlogin/${id}",requestOptions)
    .then(response => response.json())
    .then(result =>{
        document.getElementById("uname").innerHTML = result.uname;
        document.getElementById("psw").innerHTML = result.pass;
    })
    .catch(error => console.log('error', error));
}

var id = window.sessionStorage.getItem('id');

document.addEventListener('DOMContentLoaded', getUserInfo(id))
// getUserInfo(id);