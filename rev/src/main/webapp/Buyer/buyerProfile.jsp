<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Arial">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<title>REVSHOP</title>
    <style>
@charset "UTF-8";

body {
    font-family: 'Roboto', sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f4f4f4;
    color: #333;
    line-height: 1.6;
    display: flex;
    min-height: 100vh;
}

.container {
    flex: 1;
    margin: 20px auto;
    background: #fff;
    padding: 30px;
    border-radius: 12px;
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
    transition: box-shadow 0.3s ease-in-out;
}

.container:hover {
    box-shadow: 0 12px 24px rgba(0, 0, 0, 0.15);
}

h1 {
    font-size: 2.2em;
    margin-bottom: 25px;
    color: #007bff;
    border-bottom: 2px solid #007bff;
    padding-bottom: 10px;
    letter-spacing: 1px;
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
    padding: 15px;
    border: 1px solid #ddd;
    border-radius: 8px;
    background: #fefefe;
    transition: background 0.3s, border-color 0.3s, transform 0.3s;
}

.user-details div:hover {
    background: #f0f8ff;
    border-color: #007bff;
    transform: scale(1.02);
}

.user-details label {
    font-weight: bold;
    color: #333;
    flex-basis: 30%;
    font-size: 1.1em;
}

.user-details span {
    flex-basis: 60%;
    color: #555;
    font-size: 1.1em;
}

.edit-icon {
    font-size: 1.2em;
    color: #007bff;
    cursor: pointer;
    transition: color 0.3s, transform 0.3s;
}

.edit-icon:hover {
    color: #0056b3;
    transform: scale(1.15);
}

.btn {
    display: inline-block;
    padding: 14px 28px;
    margin: 15px 0;
    background-color: #007bff;
    color: #fff;
    text-decoration: none;
    border-radius: 8px;
    font-size: 1.2em;
    text-align: center;
    transition: background-color 0.3s, transform 0.3s;
}

.btn:hover {
    background-color: #0056b3;
    transform: scale(1.08);
}

.btn:active {
    transform: scale(0.98);
}

.error {
    color: red;
    font-weight: bold;
}

/* Sidebar */
.sidebar {
    width: 250px;
    background-color: #007bff;
    padding-top: 30px;
    position: fixed;
    top: 0;
    bottom: 0;
    left: -250px; /* Hidden initially */
    box-shadow: 2px 0 10px rgba(0, 0, 0, 0.1);
    transition: left 0.3s ease;
}

.sidebar.open {
    left: 0; /* When open */
}

.sidebar a {
    padding: 15px 30px;
    text-decoration: none;
    font-size: 1.2em;
    color: white;
    display: block;
    transition: background-color 0.3s, color 0.3s;
}

.sidebar a:hover {
    background-color: #0056b3;
}

.sidebar a.active {
    background-color: #0056b3;
}

.sidebar .logout-btn {
    background-color: #ff4d4d;
}

.sidebar .logout-btn:hover {
    background-color: #e60000;
}

/* Sidebar toggle button */
#sidebarToggle {
    font-size: 1.5em;
    color: #007bff;
    position: fixed;
    top: 20px;
    left: 20px;
    cursor: pointer;
}

/* Main content */
.container {
    margin-left: 270px;
    transition: margin-left 0.3s ease;
}

.container.shifted {
    margin-left: 20px; /* When sidebar is hidden */
}

/* Input Fields */
input[type="text"],
input[type="email"],
input[type="tel"],
input[type="password"],
textarea {
    width: 65%;
    padding: 10px 12px;
    border: 1px solid #ddd;
    border-radius: 8px;
    box-sizing: border-box;
    font-size: 1.1em;
    margin-top: 5px;
    transition: border-color 0.3s, box-shadow 0.3s;
}

input[type="text"]:focus,
input[type="email"]:focus,
input[type="tel"]:focus,
input[type="password"]:focus,
textarea:focus {
    border-color: #007bff;
    box-shadow: 0 0 8px rgba(0, 123, 255, 0.3);
    outline: none;
}

/* Responsive Design */
@media (max-width: 768px) {
    .container {
        width: 95%;
        padding: 20px;
    }

    h1 {
        font-size: 1.8em;
    }

    .user-details div {
        font-size: 1em;
        padding: 12px;
    }

    .edit-icon {
        font-size: 1em;
    }

    .btn {
        font-size: 1.1em;
        padding: 12px 24px;
    }

    input[type="text"],
    input[type="email"],
    input[type="tel"],
    input[type="password"],
    textarea {
        width: 90%;
        font-size: 1em;
    }
}

    </style>
</head>
<body>

    <!-- Sidebar Toggle Button -->
    <div id="sidebarToggle" onclick="toggleSidebar()">
        <i class="fa fa-bars"></i>
    </div>

    <!-- Sidebar Navigation -->
    <div class="sidebar" id="sidebar">
        <a href="#" class="active" onclick="showSection('account-details')">Account Details</a>
        <a href="#" onclick="showSection('past-orders')">View Past Orders</a>
        <a href="#" class="logout-btn">Log Out</a>
    </div>

    <!-- Main Content -->
    <div class="container" id="mainContent">
        <h1>User Details</h1>
        
        <!-- Account Details Section -->
        <div class="user-details" id="account-details">
            <div>
                <label>Name:</label>
                <span id="name"><%= session.getAttribute("buyerName") %></span>
                <i class=""></i>
            </div>
            <div>
                <label>Email:</label>
                <span id="email"><%= session.getAttribute("buyerEmail") %></span>
                <i class="fa fa-edit edit-icon" onclick="window.location.href=''"></i>
            </div>
            <div>
                <label>Contact:</label>
                <span id="contact"><%= session.getAttribute("buyerContactNo") %></span>
                <i class=""></i>
            </div>
            <div>
                <label>Password:</label>
                <span id="password"><%= session.getAttribute("buyerPassword") %></span>
                <i class="fa fa-edit edit-icon" onclick="window.location.href=''"></i>
            </div>
            <div>
                <label>Residence:</label>
                <span id="residence"><%= session.getAttribute("buyerCity") %></span>
                <i class=""></i>
            </div>
        </div>
        
        <!-- Past Orders Section -->
        <div class="user-details" id="past-orders" style="display: none;">
            <div>
                <label>Order 1:</label>
                <span>Order details for Order 1</span>
            </div>
            <div>
                <label>Order 2:</label>
                <span>Order details for Order 2</span>
            </div>
            <!-- Add more past orders here -->
        </div>
    </div>

    <!-- JavaScript for sidebar toggle and content switch -->
    <script>
        function toggleSidebar() {
            var sidebar = document.getElementById("sidebar");
            var content = document.getElementById("mainContent");

            // Toggle sidebar visibility
            if (sidebar.classList.contains("open")) {
                sidebar.classList.remove("open");
                content.classList.add("shifted");
            } else {
                sidebar.classList.add("open");
                content.classList.remove("shifted");
            }
        }

        function showSection(sectionId) {
            var accountDetails = document.getElementById("account-details");
            var pastOrders = document.getElementById("past-orders");

            // Hide both sections and only show the selected section
            accountDetails.style.display = "none";
            pastOrders.style.display = "none";

            document.getElementById(sectionId).style.display = "block";
        }
    </script>

</body>
</html>
