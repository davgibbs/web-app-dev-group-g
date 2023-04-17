
$(document).ready(function () {

    //get the cookie related to isAdmin
    var cookies = document.cookie.split(';');
    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i].trim();
        if (cookie.indexOf('isAdmin=') == 0) {
            var isAdmin = cookie.substring('isAdmin='.length, cookie.length);
            break;
        }
    }

    // Retrieve all cookies for the current document
    const allCookies = document.cookie;

    // Check if the cookie with the specified name exists and has the expected value
    if (allCookies.includes("isAdmin" + "=" + "true")) {
        // console.log("The cookie exists and has the correct value.");
        document.getElementById("admin").style.display = "flex"
    } else {
        document.getElementById("admin").style.display = "none"
    }


})