<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.revshop.rev.entity.Product" %>


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
  	<form action="${pageContext.request.contextPath}/buyer/search" method="get">
    <input type="text" name="keyword" placeholder="Search Product, Brands and More" class="search-input">
    <button type="submit" class="search-button"><i class="fa fa-search"></i></button>
    </form>
   	<button class="cart" onclick="window.location.href='view-cart.jsp'"><i class="fa-solid fa-cart-shopping"></i>Cart</button>
       <div class="dropdown">
        <button class="cart user-btn"><i class="fa fa-user-circle"></i><%= session.getAttribute("name") %></button>
        <div class="dropdown-content">
            <a href="buyerProfile.jsp">Profile</a>
            <a href="orders.jsp">My Orders</a>
            <a href="logout.jsp">Logout</a>
        </div>
    </div>
  </nav>
</header>

<form action="${pageContext.request.contextPath}/buyer/viewAllProducts" method="get">
    <button type="submit">View All Products</button>
</form>

 <c:if test="${not empty buyersList}">
    <h2>Available Products</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Product Name</th>
                <th>Product Price</th>
                <th>Description</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${buyersList}">
                <tr>
                    <td>${product.productName}</td>
                    <td>${product.price}</td> <!-- Ensure you are using 'price' if that's the correct field -->
                    <td>${product.description}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>

<c:if test="${empty buyersList}">
    <p>No products available at the moment.</p>
</c:if>

    
</body>
</html>
