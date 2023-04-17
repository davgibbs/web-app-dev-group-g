function getToken() {
	// Get the Json web token from the cookie
    const jwtCookie = document.cookie.split('; ').find(row => row.startsWith('token='));
    const jwtBearerToken = jwtCookie ? jwtCookie.split('=')[1] : null;
    return jwtBearerToken;
}


function deleteToken(){
  // Delete token from local application cookie by deleting cookies for this domain
  var cookies = document.cookie.split("; ");
  // Iterate cookies
    for (var c = 0; c < cookies.length; c++) {
        var d = window.location.hostname.split(".");
        // Clear cookies for this domain
        while (d.length > 0) {
            var cookieBase = encodeURIComponent(cookies[c].split(";")[0].split("=")[0]) + '=; expires=Thu, 01-Jan-1970 00:00:01 GMT; domain=' + d.join('.') + ' ;path=';
            var p = location.pathname.split('/');
            document.cookie = cookieBase + '/';
            while (p.length > 0) {
                document.cookie = cookieBase + p.join('/');
                p.pop();
            };
            d.shift();
        }
    }
	window.location.href= "./login.html"
}


$(document).ready(function() {
	// Show the correct elements based on user type
    var adminElements = $(".admin-element");
    var normalElements = $(".normal-element");
    var anonElements = $(".anon-element");
    adminElements.hide();
    normalElements.hide();
    anonElements.hide();

    var cookieValue = document.cookie.replace(/(?:(?:^|.*;\s*)userRoles\s*\=\s*([^;]*).*$)|^.*$/, "$1");
    if(cookieValue) {
      var decodedCookieValue = decodeURIComponent(cookieValue);
      var roles = JSON.parse(decodedCookieValue);
      for (let i in roles) {
          if (roles[i] === "ROLE_ADMIN") {
              adminElements.show();
              normalElements.hide();
              anonElements.hide();
          } else if (roles[i] === "ROLE_USER") {
              adminElements.hide();
              normalElements.show();
              anonElements.hide();
          }
      }
  	}  else {
      adminElements.hide();
      normalElements.hide();
      anonElements.show();
	}

	$("a#logout").click(function(event) {
	  event.preventDefault();
      deleteToken();
	});
});