(function () {
	var $forgetPassword;
	var $usernameFld, $passwordFld;
	var $loginBtn;
	var $fbLoginBtn; 
	var userService = new UserServiceClient();
	$(main);

	function main() { 

		$forgetPassword = $('#forgetPassword');
		$usernameFld = $('#login-username');
		$passwordFld = $('#login-password');
		$loginBtn = $('#btn-login')
					.click(login);
		$fbLoginBtn = $('#btn-fblogin');
	}
	
	function login() {
		var user = {
				username: $usernameFld.val(),
				password: $passwordFld.val()
		};
		console.log("user login");
		console.log($usernameFld.val());
		console.log($passwordFld.val());
		
		userService
			.login(user.username, user.password)
			.then(function(response) {
				console.log(response);
                window.location.href = "/jquery/components/profile/profile.template.client.html?userId=" + response.id
			})
	}
	
	
})();

