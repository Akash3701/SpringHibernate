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
/* Login form container */
#loginForm {
    width: 350px;
    margin: 60px auto;
    padding: 40px;
    border: 1px solid #ccc;
    border-radius: 15px;
    background-color: #f4f4f9;
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
    font-family: 'Arial', sans-serif;
    transition: all 0.3s ease;
}


/* Fieldset and legend */
#loginForm fieldset {
    border: none;
    margin-bottom: 25px;
}

#loginForm legend {
    font-size: 18px;
    font-weight: bold;
    color: #333;
    margin-bottom: 15px;
}

/* Input field wrapper to ensure proper positioning */
#loginForm div {
    position: relative; /* Added to handle relative positioning of the input and icon */
    display: flex;
    align-items: center;
}

/* Input fields */
.icon-input {
    width: 100%;
    padding: 12px 35px; /* Adjusted padding to leave room for icons */
    margin-top: 8px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 14px;
    background-color: #fff;
    color: #333;
    box-sizing: border-box;
    transition: border-color 0.3s ease;
}

.icon-input:focus {
    border-color:  #4a89dc;
    outline: none;
    background-color: #fff;
}

/* Icons for email and password */
.icon {
    position: absolute;
    left: 10px; /* Adjusted to fix the positioning */
    top: 50%;
    transform: translateY(-50%);
    color: #4a89dc;
    pointer-events: none; /* Makes sure the icon doesn't interfere with input field interaction */
}

/* Toggle password icon */
.toggle-password {
    position: absolute;
    right: 10px;
    top: 50%;
    transform: translateY(-50%);
    cursor: pointer;
    color: #888;
    transition: color 0.3s ease;
}

.toggle-password:hover {
    color: #4a89dc;
}

/* Submit button */
#submit-button {
    width: 100%;
    padding: 12px;
    background-color: #4a89dc;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
    text-transform: uppercase;
    letter-spacing: 1px;
    transition: background-color 0.3s ease, transform 0.3s ease;
}



/* Forgot password link */
.forgot-password {
    text-align: right;
    margin-top: 5px;
    margin-bottom: 20px;
    background:none;
}

.forgot-password a {
    color: #333;
    text-decoration: none;
    font-size: 14px;
}

.forgot-password a:hover {
    text-decoration: underline;
    color: #f7b733;
}

/* Error message */
#errorMessage {
    text-align: center;
    font-size: 14px;
    color: red;
}

/* Options for registering */
.options {
    text-align: center;
    margin-top: 30px;
    font-size: 14px;
    margin-bottom:40px;
}

.options a {
    color: #333;
    text-decoration: none;
    font-weight: bold;
    transition: color 0.3s ease;
}

.options a:hover {
    text-decoration: underline;
    color: #f7b733;
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
    <button class="cart" onclick="window.location.href='buyerRegistration.jsp'"><i class="fa fa-user-circle"></i>Login</button>
   	<button class="cart" onclick="window.location.href='view-cart.jsp'"><i class="fa-solid fa-cart-shopping"></i>Cart</button>
    <button class="favourite"><i class="fa-solid fa-shop"></i>Become a Seller</button>
  </nav>
</header>
     <h2>Buyer Login</h2>
    <form action="${pageContext.request.contextPath}/buyer/Login" method="POST">
        <label>Email:</label>
        <input type="email" name="email" required/><br>

        <label>Password:</label>
        <input type="password" name="password" required/><br>

        <input type="submit" value="Login">
    </form>
</body>
<script>  const togglePassword = document.querySelector("#togglePassword");
const password = document.querySelector("#password");

togglePassword.addEventListener("click", function() {
    // Toggle the type attribute between 'password' and 'text'
    const type = password.getAttribute("type") === "password" ? "text" : "password";
    password.setAttribute("type", type);

    // Toggle the eye icon classes
    this.classList.toggle("fa-eye");
    this.classList.toggle("fa-eye-slash");
});
</script>
</html>