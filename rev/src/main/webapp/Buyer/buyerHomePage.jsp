<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Buyer Homepage</title>
    <link rel="stylesheet" href="Welcomepage.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Arial">
  	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
  	<link rel="stylesheet" href="buyerHomePage.css">
</head>
<body>
<header class="site-header">
  <div class="site-identity">
    <a href="HomePage.jsp"><img src="IMAGES/LOGO.png" alt="Site Logo" /></a>
    <h1><a href="HomePage.jsp">REVSHOP</a></h1>
  </div>  
  <nav class="search-bar">
  	<form action="search" method="get">
    <input type="text" name="keyword" placeholder="Search Product, Brands and More" class="search-input">
    <button type="submit" class="search-button"><i class="fa fa-search"></i></button>
    </form>
   	<button class="cart" onclick="window.location.href='view-cart.jsp'"><i class="fa-solid fa-cart-shopping"></i>Cart</button>
       <div class="dropdown">
        <button class="cart user-btn"><i class="fa fa-user-circle"></i><%= session.getAttribute("buyerName") %></button>
        <div class="dropdown-content">
            <a href="buyerProfile.jsp">Profile</a>
            <a href="orders.jsp">My Orders</a>
            <a href="logout.jsp">Logout</a>
        </div>
    </div>
  </nav>
</header>
    <h2>Welcome, ${buyerName}!</h2>

    <form action="search" method="GET">
        <label>Search Products:</label>
        <input type="text" name="keyword" placeholder="Search for products">
        <input type="submit" value="Search">
    </form>
    <div class="container">
        <!-- View Products by Category -->
        <form action="category-products" method="get">
            <select name="categoryName">
                <c:forEach var="category" items="${categories}">
                    <option value="${category}">${category}</option>
                </c:forEach>
            </select>
            <button type="submit">View Category</button>
        </form>

        <!-- Product List -->
        <h2>Product List</h2>
        <c:forEach var="product" items="${products}">
            <div class="product">
                <h3>${product.productName}</h3>
                <p>${product.description}</p>
                <p>Price: $${product.price}</p>

                <!-- Add to Cart Form -->
                <form action="add-to-cart" method="post">
                    <input type="hidden" name="productId" value="${product.productId}">
                    Quantity: <input type="number" name="quantity" value="1" min="1">
                    <button type="submit">Add to Cart</button>
                </form>
            </div>
        </c:forEach>


    
</body>
</html>
