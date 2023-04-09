
//access the stored data and displays it in the contact page
var uname = window.sessionStorage.getItem('uname');
var psw = window.sessionStorage.getItem('psw');

if(uname==null || psw ==null){
    document.getElementById("uname").innerHTML = "Username";
    document.getElementById("psw").innerHTML = "password";
}else{
    document.getElementById("uname").innerHTML = uname;
    document.getElementById("psw").innerHTML = psw;
}


//add fetch to the database to get the data stored using the id stored in the browser