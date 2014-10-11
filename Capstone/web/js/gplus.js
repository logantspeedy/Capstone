    var runCount = 0;
    function signinCallback(authResult) {
        console.log(runCount);
        if (runCount < 1){runCount +=1 ;return;}
        
  if (authResult['status']['signed_in']) {
    // Update the app to reflect a signed in user
    // Hide the sign-in button now that the user is authorized, for example:
    var accessToken = authResult['access_token']
    console.log(accessToken);
    $.getJSON('https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=' + accessToken, function(data) {
        console.log(data);
        login(data['name']);
    });
    
  } else {
    // Update the app to reflect a signed out user
    // Possible error values:
    //   "user_signed_out" - User is signed-out
    //   "access_denied" - User denied access to your app
    //   "immediate_failed" - Could not automatically log in the user
    console.log('Sign-in state: ' + authResult['error']);
  }
}



