
function CheckPassword() {
    // var check = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/;
    
    var check = /^.{5,10}$/;  // Check for between 5 and 10 characters
    if (document.querySelector('#signup_form').style.display == 'block') {
        var psw = document.getElementById('psw_signup');
    } else {
        var psw = document.getElementById('psw_login');
    }

    //check if the password has 6-20 characters with one digit, one uppercase and one lowercase letter
    if (psw.value.match(check)) {
        psw.style.backgroundColor = "rgba(100, 223, 100, 0.603)";
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
        return true;
    }
    else {
        uname.style.backgroundColor = "rgba(223, 100, 100, 0.603)";
        return false;
    }
}



/*  ///////////////////////////////
        Database check for login
/////////////////////////////// */
const requestOptionsGet = {
    method: 'GET',
    redirect: 'follow'
};

//check for uname and psw
function checkLogin() {
    //variable selection
    var username = $("#uname_login").val();
    var password = $("#psw_login").val();
    
    details = {}
    details["username"] = username;  // Set the username
    details["password"] = password;  // Set the username
    const url = "./auth/login";

    var posting = $.ajax({
        type: 'POST',
        url: url,
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(details)
    });
   
    posting.done(function( data ) {
		console.log("success");
		console.log(data);
		var rolesString = JSON.stringify(data.roles);
        document.cookie = "userRoles=" + encodeURIComponent(rolesString) + "; path=/"
        var usernameString = data.username;
        document.cookie = "username=" + usernameString + "; path=/"
           
        const token = data.token; // assuming the token is returned as a property named 'token' in the response
           
        document.cookie = `token=${token}; path=/`; // store the token in a cookie named 'token'
        window.location.href= "./books.html"
		return true;
	})
	
	posting.fail(function(message) {
		console.log(message.statusText);			
		document.getElementById("psw_p").innerHTML = "Password does not correspond to the username";
		return false;
	})  
}


/*  ///////////////////////////////
    verify quality of login inputs on click
/////////////////////////////// */
document.getElementById("submit_login").addEventListener('click', event => {
    //on click of the submit button verifies that all conditions are respected
    event.preventDefault();

    var t1 = CheckUsername();
    var t2 = CheckPassword();

    const psw = document.login.psw;
    if (t1 && t2) {
        var final = checkLogin();
        if (final) {
            document.querySelector('#login').submit();
        }
    }
})



/*  ///////////////////////////////
        More tests for signup
/////////////////////////////// */
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
            return true;
        }
    }
    return false;
}

/*  ///////////////////////////////
       add signup to database 
/////////////////////////////// */
function signUp() {
    details = {}
    details["username"] = document.getElementById('uname_signup').value;  // Set the username
    details["password"] = document.getElementById("psw_signup").value;  // Set the password
    details["firstname"] = document.getElementById("firstname").value;  // Set the first name
    details["email"] = document.getElementById("email").value;  // Set the email
    details["surname"] = document.getElementById("surname").value;  // Set the surname
    const url = "./auth/signup";

    var signuppost = $.ajax({
        type: 'POST',
        url: url,
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(details)
    });
   
    signuppost.done(function( data ) {
		console.log("success");
		console.log(data);
		alert("Signup successful. Please login"); 
        window.location.href= "./login.html";
	})
	
	signuppost.fail(function(message) {
		console.log("fail");
		console.log(message);
		console.log(message.statusText);			
		alert("Issue signing up"); 
		return false;
	}) 

    return true;
}


/*  ///////////////////////////////
    on click verifies signup inputs 
    /////////////////////////////// */
document.getElementById("submit_signup").addEventListener('click', event => {
    //on click of the submit button verifies that all conditions are respected
    event.preventDefault();
    var t1 = Checkform('#name', /^\w[a-zA-Z]/); //format for name can accept composed names
    var t2 = Checkform('#surname', /^\w[a-zA-Z]/);
    var t3 = CheckUsername();
    var t4 = Checkform('#email', /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,})+$/);
    var t5 = CheckPassword();

    if (t1 && t2 && t3 && t4 && t5) {
        //if the form is completed properly -> call sign up function
        var final = signUp();
        if (final) {
            //if the last test in signup is passsed (username not already in database)
            //the the form can be sumbitted and go to next page of the website page
            document.querySelector('#signup').submit();
        }
    }
})



/*  ///////////////////////////////
        Change the form used
    /////////////////////////////// */
//display either the signup form or the login on click of the button
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


document.getElementById("signup_btn").addEventListener('click', event => {
    event.preventDefault();
})
document.getElementById("signin_btn").addEventListener('click', event => {
    event.preventDefault();
})
