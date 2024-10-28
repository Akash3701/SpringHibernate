<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>REVSHOP</title>
<link rel="stylesheet" href="adminLogin.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<link rel="icon" href="IMAGES/LOGO.png" type="image/png" sizes="64x64">
</head>
<body>
   <form action="adminlogin" method="post">
    <h2>Hello Admin! Please Login</h2>
        <div class="form-group">
            <i class="fas fa-envelope"></i>
            <input type="email" name="email" placeholder="Your Email" required>
        </div>
        <div class="form-group password-group">
            <i class="fas fa-lock"></i>
            <input type="password" id="password" name="password" placeholder="Password" required>
            <i class="fas fa-eye" id="togglePassword" style="cursor: pointer;"></i>
        </div>
        <input type="submit" value="Sign In">
    </form>
    <c:if test="${not empty loginResult}">
        <p>${loginResult}</p>
    </c:if>
  
    <footer>
        <a href="http://www.polymer-project.org/" target="_blank">
            <img src="IMAGES/LOGO.png" style="width:200px">
        </a>
    </footer>
</body>
<script src="adminLogin.js"></script>
</html>