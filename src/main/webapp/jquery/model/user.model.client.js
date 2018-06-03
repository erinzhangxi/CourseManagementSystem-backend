/**
 * This class is used to create instances of users whenever sending or receiving data from the server, 
 * or rendering users in the UI
 */

function User(username, password, email, firstName, lastName, phone, role, dateOfBirth) {
  this.username = username;
  this.password = password;
  this.email = email;
  this.firstName = firstName;
  this.lastName = lastName;
  this.phone = phone;
  this.role = role;
  this.dateOfBirth = dateOfBirth;

  this.setUsername = setUsername;
  this.getUsername = getUsername;
  this.setPassword = setPassword;
  this.getPassword = getPassword;
  this.setEmail = setEmail;
  this.getEmail = getEmail;
  this.setFirstName = setFirstName;
  this.setLastName = setLastName;
  this.getFirstName = getFirstName;
  this.getLastName = getLastName;
  this.getPhone = getPhone;
  this.setPhone = setPhone;
  this.getRole = getRole;
  this.setRole = setRole;
  this.getDateOfBirth = getDateOfBirth;
  this.setDateOfBirth = setDateOfBirth;
  

  function setUsername(username) {
    this.username = username;
  }
  function getUsername() {
    return this.username;
  }
  function setPassword(passowrd) {
	  this.password= password;
  }
  function getPassword() {
	  return this.password;
  }
  function setEmail(email) {
	  this.email= email;
  }
  function getEmail() {
	  return this.email;
  }
  function setFirstName(firstname) {
	  this.firstName= firstname;
  }
  function getFirstName() {
	  return this.firstName;
  }
  function setLastName(lastname) {
	  this.lastName= lastname;
  }
  function getLastName() {
	  return this.lastName;
  }
  function setPhone(phone) {
	  this.phone= phone;
  }
  function getPhone() {
	  return this.phone;
  }
  function setRole(role) {
	  this.role= role;
  }
  function getRole() {
	  return this.role;
  }
  function setDateOfBirth(dateOfBirth) {
	  this.dateOfBirth= dateOfBirth;
  }
  function getDateOfBirth() {
	  return this.dateOfBirth;
  }
}
