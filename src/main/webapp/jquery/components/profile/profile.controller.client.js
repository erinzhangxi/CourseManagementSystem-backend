//IIFE
(function () {

    $(init);

    var $staticUsername;
    var $phone;
    var $email;
    var $dateOfBirth;
    var $role;
    var $updateBtn;
    var userService = new UserServiceClient();

    var userId;
    
    function init() {
    	
        $staticUsername = $("#profile-username");
        $firstName = $("#profile-firstname");
        $lastName = $("#profile-lastname");
        $phone = $("#profile-phone");
        $email = $("#profile-email");
        $dateOfBirth = $("#dob");
        $role = $("#inputRole");
        $updateBtn = $("#updateBtn")
            .click(updateUser);
        $logoutBtn = $("#logoutBtn")
        		.click(logout);

        template = $('#template');
      
        userId = retrieveID()[0];
        console.log("User ID: ");
        console.log(userId);
        
        findUserById(userId);
    }

    function retrieveID() {
    		var vars = [], hash; 
    		var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&'); 
    		
    		for(var i = 0; i < hashes.length; i++) { 
    			hash = hashes[i].split('='); vars.push(hash[0]); 
    			vars[i] = hash[1];
    			console.log(hash);
    		}   		
    		return vars;
    }
    
    function updateUser() {
    		var user = {
    				id: userId,
				username: $staticUsername.val(),
				firstName: $firstName.val(),
				lastName: $lastName.val(),
				phone: $phone.val(),
				email: $email.val(), 
				role: $role.val(),
				dateOfBirth: $dateOfBirth.datepicker({ dateFormat: 'yy-mm-dd' }).val()
		};

        userService
            .updateUser(userId, user)
            .then(success);
    }
 
    function success() {
    		return alert('user updated');
    }
    
    function findUserById(userId) {
        userService
            .findUserById(userId)
            .then(renderUser);;
    }
    
    function renderUser(user) {
        console.log(user);
        var clone = template.clone();
        clone.attr('id', user.id);
        clone.find('.profile-username')
        		.html(user.username);
        clone.find('.profile-firstname')
        		.html(user.firstName);
        clone.find('.profile-lastname')
		.html(user.lastName);
        clone.find('.profile-phone')
        		.html(user.phone);
        clone.find('.profile-email')
			.html(user.email);
        clone.find('.inputRole')
			.val(user.role);
        clone.find('.dob')
        		.datepicker("setDate", new Date(2008,9,03) );
        
        $staticUsername.val(user.username);
        $phone.val(user.phone);
        $email.val(user.email);
        $dateOfBirth.val(user.dataOfBirth);
        $role.val(user.role);
        $firstName.val(user.firstName);
        $lastName.val(user.lastName);
    }
    
    function logout() {
    	 	console.log("Log Out");
         window.location.href = "/jquery/components/login/login.template.client.html"
    }
    
})();
