<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>REVSHOP</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Arial">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f4f4f4;
    color: #333;
}

.container {
    width: 80%;
    margin: 20px auto;
    background: #fff;
    padding: 20px;
    border-radius: 12px;
    box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
}

h1 {
    font-size: 2em;
    margin-bottom: 20px;
    color: #333;
    border-bottom: 2px solid #007bff;
    padding-bottom: 10px;
}

.user-details {
    margin: 20px 0;
}

.user-details div {
    margin-bottom: 15px;
    font-size: 1.2em;
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 8px;
    background: #fafafa;
    transition: background 0.3s, border-color 0.3s;
}

.user-details div:hover {
    background: #f0f8ff;
    border-color: #007bff;
}

.user-details label {
    font-weight: bold;
    color: #333;
    flex-basis:30%;
}

.user-details span {
    flex-basis: 60%;
}

.edit-icon {
    font-size: 0.8em;
    color: #007bff;
    cursor: pointer;
    transition: color 0.3s, transform 0.3s;
}

.edit-icon:hover {
    color: #0056b3;
    transform: scale(1.1);
}

.btn {
    display: inline-block;
    padding: 12px 24px;
    margin: 10px 0;
    background-color: #007bff;
    color: #fff;
    text-decoration: none;
    border-radius: 8px;
    font-size: 1.1em;
    text-align: center;
    transition: background-color 0.3s, transform 0.2s;
}

.btn:hover {
    background-color: #0056b3;
    transform: scale(1.05);
}

.btn:active {
    transform: scale(0.98);
}

.error {
    color: red;
    font-weight: bold;
}

/* Input Fields */
input[type="text"],
input[type="email"],
input[type="tel"],
input[type="password"],
textarea {
    width: 60%;
    padding: 0px;
    border: 1px solid #ddd;
    border-radius: 8px;
    box-sizing: border-box;
    font-size: 1em;
    margin-top: 5px;
    transition: border-color 0.3s, box-shadow 0.3s;
}

input[type="text"]:focus,
input[type="email"]:focus,
input[type="tel"]:focus,
input[type="password"]:focus,
textarea:focus {
    border-color: #007bff;
    box-shadow: 0 0 5px rgba(0, 123, 255, 0.3);
    outline: none;
}

/* Responsive Design */
@media (max-width: 768px) {
    .container {
        width: 90%;
        padding: 15px;
    }

    h1 {
        font-size: 1.6em;
    }

    .user-details div {
        font-size: 1em;
        padding: 8px;
    }

    .edit-icon {
        font-size: 1.2em;
    }

    .btn {
        font-size: 1em;
        padding: 10px 20px;
    }
}

    </style>
</head>
<body>
    <div class="container">
        <h1>User Details</h1>
        
        <div class="user-details">
            <div>
                <label>Name:</label>
                <span id="name"><%= session.getAttribute("name") %></span>
                <i class=""></i>
            </div>
            <div>
                <label>Email:</label>
                <span id="email"><%= session.getAttribute("email") %></span>
                <i class="fa fa-edit edit-icon" onclick="window.location.href='UpdateEmail.jsp'"></i>
            </div>
            <div>
                <label>Contact:</label>
                <span id="contact"><%= session.getAttribute("contact") %></span>
                <i class="" onclick="window.location.href'"></i>
            </div>
            <div>
                <label>Password:</label>
                <span id="password"><%= session.getAttribute("password") %></span>
                <i class="fa fa-edit edit-icon" onclick="window.location.href='UpdatePassword.jsp'"></i>
            </div>
            <div>
                <label>Residence:</label>
                <span id="residence"><%= session.getAttribute("residence") %></span>
                <i class="" ></i>
            </div>
        </div>

        <a href="BuyerServlet?taskType=logout" class="btn">Logout</a>
    </div>
</body>
</html>
