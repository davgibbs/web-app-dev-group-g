function getToken() {
	// Get the Json web token from the cookie
    const jwtCookie = document.cookie.split('; ').find(row => row.startsWith('token='));
    const jwtBearerToken = jwtCookie ? jwtCookie.split('=')[1] : null;
    return jwtBearerToken;
}