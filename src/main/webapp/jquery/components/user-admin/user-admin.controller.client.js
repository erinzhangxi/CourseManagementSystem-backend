//IIFE
(function () {

    jQuery(main);

    var tbody;
    var template;
    var userService = new UserServiceClient();
    var searchId;

    function main() {
        tbody = $('tbody');
        template = $('.template');
        $('#createUser').click(createUser);
        
        searchId = $('#findById').val();
		var search = $('#searchUserById').click(findUserById);
		
	 	
        findAllUsers();
        
    }

    function findAllUsers() {
        userService
            .findAllUsers()
            .then(renderUsers);
    }

    function createUser() {
        console.log('createUser');

        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var userType;

        if ($("#usertype-faculty").is(":checked")) {
            userType = "faculty";
        }
        else if ($("#usertype-student").is(":checked")) {
          userType = "student";
        }

        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            dateOfBirth: "01/01/1990",
            role: userType,
            phone: "617-111-1111",
            email: "summer1-2018@webdev.com"
        };
        
        userService
            .createUser(user)
            .then(findAllUsers);
    }

    function renderUsers(users) {
        tbody.empty();
        for(var i=0; i<users.length; i++) {
            var user = users[i];
            var clone = template.clone();

            clone.attr('id', user.id);

            clone.find('.delete').click(deleteUser);
            clone.find('.edit').click(updateUser);

            clone.find('.wbdv-username')
                .html(user.username);
            
            clone.find('.wbdv-first-name')
            .html(user.firstName);
            
            clone.find('.wbdv-last-name')
            .html(user.lastName);
            
            clone.find('.wbdv-role')
            .html(user.role);
            
            tbody.append(clone);
        }
    }

    function deleteUser(event) {
        var deleteBtn = $(event.currentTarget);
        var userId = deleteBtn
            .parent()
            .parent()
            .parent()
            .attr('id');
        console.log(userId);

        userService
            .deleteUser(userId)
            .then(findAllUsers);
    }

   
    function updateUser(event) {
        console.log('editUser')
        		
        var editBtn = $(event.currentTarget);
        // get current row - user id 
      var userId = editBtn
      	.parent()
      	.parent()
      	.parent()
      	.attr('id');
      console.log(userId);
      
        // redirect to profile page 
      window.location.href = "/jquery/components/profile/profile.template.client.html?userId=" + userId

//        
//        userService
//        .updateUser(userId)
//        .then(findAllUsers);

    }
 
    // NOT SURE IF THIS FUNCTION IS NEEDED HERE, same function exists in profile.controller
    function findUserById() {
    	 	console.log(id);
    	 	
    	 	userService
    	 		.findUserById(id);
    }
     

})();
