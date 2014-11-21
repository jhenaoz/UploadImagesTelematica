function cargarFbSDK(){
    (function(d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) return;
        js = d.createElement(s); js.id = id;
        js.src = "//connect.facebook.net/en_US/sdk.js";
        fjs.parentNode.insertBefore(js, fjs);
      }(document, 'script', 'facebook-jssdk'));
}

function  iniciarFb(){
	window.fbAsyncInit = function() {
		  FB.init({
		    appId      : '847142835297547',
		    cookie     : true,  // enable cookies to allow the server to access 
		                        // the session
		    xfbml      : true,  // parse social plugins on this page
		    version    : 'v2.1' // use version 2.0
		  });
	}
}
function loginFB(){
	FB.login(function(response){
		console.log(response);
	});
}