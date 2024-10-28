<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Arial">
  	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
  	<link rel="stylesheet" href="Welcomepage.css">
	<title>REVSHOP</title>
<style>
/* Registration form container */
.registration-form {
    width: 400px;
    margin: 60px auto;
    padding: 40px;
    border: 1px solid #ccc;
    border-radius: 10px;
    background-color: #f4f4f9;
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
    font-family: 'Arial', sans-serif;
    transition: all 0.3s ease;
}

.registration-form:hover {
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
}

.registration-form h2 {
    text-align: center;
    margin-bottom: 20px;
    color: #333;
}

/* Labels and inputs */
.registration-form label {
    font-size: 14px;
    color: #333;
    margin-bottom: 8px;
    display: block;
    font-weight: bold;
}

.registration-form .input-group {
    position: relative;
}

.registration-form input[type="text"],
.registration-form input[type="email"],
.registration-form input[type="password"] {
    width: 100%;
    padding: 10px;
    margin-bottom: 15px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 14px;
    background-color: #fff;
    box-sizing: border-box;
    transition: border-color 0.3s ease;
}

.registration-form input:focus {
    border-color: #f7b733;
    background-color: #f9f9f9;
    outline: none;
}

/* Submit button */
.registration-form input[type="submit"] {
    width: 100%;
    padding: 12px;
    background-color: #333;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
    text-transform: uppercase;
    letter-spacing: 1px;
    transition: background-color 0.3s ease, transform 0.3s ease;
}

.registration-form input[type="submit"]:hover {
    background-color: #f7b733;
    transform: translateY(-3px);
}

/* Error message */
.error-message {
    color: red;
    font-size: 12px;
    margin-top: -10px;
    margin-bottom: 10px;
    display: none;
}

/* Style for input group to position the icons */
.input-group {
    position: relative;
}

.input-group  i {
    position: absolute;
    left: 10px;
    top: 50%;
    transform: translateY(-50%);
    cursor: pointer;
    color: #999;
    font-size: 18px;
    transition: color 0.3s ease;
}



/* Change icon color on hover */
.input-group i:hover {
    color: #333;
}



</style>
</head>
<body>
<header class="site-header">
  <div class="site-identity">
    <a href="HomePage.jsp"><img src="IMAGES/LOGO.png" alt="Site Logo" /></a>
    <h1><a href="HomePage.jsp">REVSHOP</a></h1>
  </div>  
  <nav class="search-bar">
    <input type="text" placeholder="Search Product, Brands and More" class="search-input">
    <button type="submit" class="search-button"><i class="fa fa-search"></i></button>
    <button class="cart" onclick="window.location.href='login.jsp'"><i class="fa fa-user-circle"></i>Login</button>
    <button class="cart" onclick="window.location.href='view-cart.jsp'"><i class="fa-solid fa-cart-shopping"></i>Cart</button>
    <button class="favourite"><i class="fa-solid fa-shop"></i>Become a Seller</button>
  </nav>
</header>
<body>
    <h2>Buyer Registration</h2>
  <form action="${pageContext.request.contextPath}/registerBuyer" method="POST">

        <label>Name:</label>
        <input type="text" name="name" required/><br>

        <label>Email:</label>
        <input type="email" name="email" required/><br>

        <label>Password:</label>
        <input type="password" name="password" required/><br>

        <label>Contact Number:</label>
        <input type="text" name="contactNo" required/><br>

        <label>City:</label>
        <input type="text" name="city" required/><br>

        <input type="submit" value="Register">
    </form>

    <c:if test="${RegisterResult != null}">
        <p>Registration ${RegisterResult}</p>
    </c:if>
<script>
//Toggle password visibility
function togglePassword() {
    const passwordField = document.getElementById('password');
    const passwordToggle = document.getElementById('toggle-password');
    
    if (passwordField.type === 'password') {
        passwordField.type = 'text';
        passwordToggle.classList.remove('fa-eye');
        passwordToggle.classList.add('fa-eye-slash');
    } else {
        passwordField.type = 'password';
        passwordToggle.classList.remove('fa-eye-slash');
        passwordToggle.classList.add('fa-eye');
    }
}

// Validation function
function validateForm() {
    let valid = true;
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const contactNo = document.getElementById("contactNo").value;
    
    // Reset error messages
    document.getElementById("name-error").style.display = 'none';
    document.getElementById("email-error").style.display = 'none';
    document.getElementById("password-error").style.display = 'none';
    document.getElementById("contact-error").style.display = 'none';

    // Name validation (at least 4 characters)
    if (name.length < 4) {
        document.getElementById("name-error").style.display = 'block';
        valid = false;
    }

    // Email validation (basic pattern)
    const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    if (!emailPattern.test(email)) {
        document.getElementById("email-error").style.display = 'block';
        valid = false;
    }

    // Password validation (minimum 6 characters, including upper, lower, and special)
    const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&#])[A-Za-z\d@$!%*?&#]{6,}$/;
    if (!passwordPattern.test(password)) {
        document.getElementById("password-error").style.display = 'block';
        valid = false;
    }

    // Contact number validation (exactly 10 digits)
    const contactPattern = /^\d{10}$/;
    if (!contactPattern.test(contactNo)) {
        document.getElementById("contact-error").style.display = 'block';
        valid = false;
    }

    return valid; // Prevent form submission if any validation fails
}
</script>

</body>
</html>
