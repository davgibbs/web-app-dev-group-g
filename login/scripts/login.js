function CheckPassword() {
    var check = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/;
    if (document.querySelector('#signup_form').style.display == 'block') {
        var psw = document.getElementById('psw_signup');
    } else {
        var psw = document.getElementById('psw_login')
    }

    //check if the password has 6-20 characters with one digit, one uppercase and one lowercase letter
    if (psw.value.match(check)) {
        psw.style.backgroundColor = "rgba(100, 223, 100, 0.603)";
        psw = psw.value;
        window.sessionStorage.setItem('psw', psw);
        return true;
    }
    else {
        psw.style.backgroundColor = "rgba(223, 100, 100, 0.603)";
        return false;
    }
}

function CheckUsername() {
    var check = /^.{3,20}$/;
    //username must be between 3 to 20 characters long, it can be anything 
    if (document.querySelector('#signup_form').style.display == 'block') {
        var uname = document.getElementById('uname_signup');
    } else {
        var uname = document.getElementById('uname_login');
    }

    if (uname.value.match(check)) {
        //if DatabaseUsername(uname)
        uname.style.backgroundColor = "rgba(100, 223, 100, 0.603)";
        uname = uname.value;
        window.sessionStorage.setItem('uname', uname);
        return true;
    }
    else {
        uname.style.backgroundColor = "rgba(223, 100, 100, 0.603)";
        return false;
    }
}



/*
    Database checks 
*/

//should only need to check for uname and psw
function DatabaseUsername(database, uname) {
    //check for username in database

    //return id ? 
    //return true(in database)
    //return false (not in)

    if ( database == input) {
        return true
        //pas besoin de ça le fetch renvoi directement ce qu'il faut
    }else {

    }

}

function DatabasePsw() {

}

//to modify to correct fecth
function CheckDatabase(uname) {
    //fetch id using username and storing id in browser
    
    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };
/*
    fetch("http://localhost:8080/library/getmember/{username}", requestOptions)
    .then(data => {
    	data.forEach(obj => {
      		for (const i of obj) {
            	insertRow(i);
        	}
    	});
  	})
  	.catch(error => console.error(error));
*/
    var username = uname.value;

    try {
        
    } catch (error) {
        
    }
    var data = fetch("http://localhost:8080/library/getmember/{username}", requestOptions)

    if(data[2])

    if(DatabaseUsername(data[1])){
        DatabasePsw()
    }

    data.forEach(element => {
        if (k == input) {
            return false
        }
    });

    //for may not be required as the query would either output the infos or error?
    return true
}



/*
if (CheckDatabase('password', psw)){
document.querySelector(psw + '_p').innerHTML = "*Field must not be empty";

window.sessionStorage.setItem('psw', psw);
return true;
}*/



/* 
    Submit login (less test than signup)
*/

/*check for inputs in database*/
document.getElementById("submit_login").addEventListener('click', event => {
    //on click of the submit button verifies that all conditions are respected
    event.preventDefault();
    var t1 = CheckUsername();

    const psw = document.login.psw;
    var t2 = CheckPassword();

    if (t1 && t2) {
        document.querySelector('#login').submit();
    }


})



/*
    More tests for signup
*/

/*add a check for database if username... already exist*/

function Checkform(id, reg) {
    const value = document.querySelector(id).value; //gives the value inside the form to "value"

    if (value.length == 0) {
        //if box is empty, tell to fill and change background color
        document.querySelector(id + '_p').innerHTML = "*Field must not be empty";
        document.querySelector(id + '_p').style = "color: red";
        document.querySelector(id).style.backgroundColor = "rgba(223, 100, 100, 0.603)";
    }

    else {
        if (!reg.test(value)) {
            document.querySelector(id + '_p').style = "color: red";
            document.querySelector(id + '_p').innerHTML = "Not correct format, Remove symbols";

            if (id == '#email') {
                document.querySelector(id + '_p').innerHTML = "Please enter a valid email address";
            }
            document.querySelector(id).style.backgroundColor = "rgba(223, 100, 100, 0.603)";
        }
        else {
            document.querySelector(id + '_p').style = "color: default";
            document.querySelector(id + '_p').innerHTML = "";
            document.querySelector(id).style.backgroundColor = "rgba(100, 223, 100, 0.603)";

            //storage
            window.sessionStorage.setItem(id, value);

            return true;
        }
    }
    return false;
}




document.getElementById("submit_signup").addEventListener('click', event => {
    //on click of the submit button verifies that all conditions are respected
    event.preventDefault();
    var t1 = Checkform('#name', /^\w[a-zA-Z]/); //format for name can accept composed names
    var t2 = Checkform('#surname', /^\w[a-zA-Z]/);
    var t3 = CheckUsername();
    var t4 = Checkform('#email', /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,})+$/);
    var t5 = CheckPassword();

    if (t1 && t2 && t3 && t4 && t5) {
        document.querySelector('#signup').submit();
    }
})



/*
Change the form used
*/

var signup_btn = document.getElementById('signup_btn');
var signup_form = document.querySelector('#signup_form');
var signin_btn = document.getElementById('signin_btn');
var signin_form = document.querySelector('#login_form');

signup_btn.onclick = function () {
    if (signup_form.style.display == 'block') {
        signup_form.style.display = 'none';
        signin_form.style.display = 'block';
    }
    else {
        signup_form.style.display = 'block';
        signin_form.style.display = 'none';
    }
}

signin_btn.onclick = function () {
    if (signin_form.style.display == 'block') {
        signin_form.style.display = 'none';
        signup_form.style.display = 'block';
    }
    else {
        signin_form.style.display = 'block';
        signup_form.style.display = 'none';
    }
}