<%@ page import="java.util.List"%>
<%@ page import="com.revshop.rev.entity.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Products List</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<style>
body {
	background-color: #f8f9fa;
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	margin-bottom: 100px;
}

header {
	background-color: #ffffff;
	padding: 20px;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

header .container {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

header h1 {
	font-size: 1.75rem;
	color: #007bff;
}

nav a {
	margin-left: 10px;
}

nav a.btn {
	border-radius: 30px;
	font-weight: 600;
	padding: 10px 20px;
}

nav a.btn-primary {
	background-color: #007bff;
	color: white;
}

nav a.btn-primary:hover {
	background-color: #0056b3;
}

nav a.btn-secondary:hover {
	background-color: #6c757d;
}

nav a.btn-danger:hover {
	background-color: #d9534f;
}

.product-card {
	background-color: #ffffff;
	border-radius: 8px;
	box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
	padding: 20px;
	margin-bottom: 20px;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	height: 300px;
}

.product-card img {
	width: 100px;
	height: 100px;
	object-fit: cover;
	border-radius: 8px;
}

.product-card h5 {
	color: #007bff;
}

.product-card .btn {
	margin-right: 10px;
}

.product-card .btn i {
	margin-right: 5px;
}

footer {
	background-color: #343a40;
	color: #fff;
	text-align: center;
	padding: 15px;
	position: fixed;
	width: 100%;
	bottom: 0;
}

footer p {
	margin: 0;
	font-size: 0.9rem;
}

@media ( min-width : 768px) {
	.product-grid {
		display: grid;
		grid-template-columns: repeat(2, 1fr);
		gap: 20px;
	}
}

@media ( min-width : 1200px) {
	.product-grid {
		grid-template-columns: repeat(3, 1fr);
	}
}

.button-container {
	display: flex;
	gap: 10px;
	align-items: center;
	margin-top: auto;
}

.button-form {
	margin: 0;
}

.btn {
	display: inline-flex;
	align-items: center;
}
</style>
</head>
<body>
	<header>
		<div class="container">
			<h1>RevShop</h1>
			<nav>
				<a href="/rev/retailer/home" class="btn btn-primary"><i class="fas fa-home"></i> Home</a>
                <a href="/rev/retailer/addProduct" class="btn btn-secondary"><i class="fas fa-plus"></i> Add Products</a>
                <a href="/rev/retailer/products" class="btn btn-secondary"><i class="fas fa-boxes"></i> View Products</a>
                <a href="/rev/retailer/orders" class="btn btn-secondary"><i class="fas fa-shopping-cart"></i> View Orders</a>
                <a href="/rev/retailer/reviews" class="btn btn-secondary"><i class="fas fa-star"></i> View Reviews</a>
                <a href="/rev/retailer/logout" class="btn btn-danger"><i class="fas fa-sign-out-alt"></i> Logout</a>
			</nav>
		</div>
	</header>

	<main class="container mt-4">
		<h2 class="text-center">Products List</h2>

		<%
		String message = (String) request.getAttribute("message");
		List<Product> products = (List<Product>) request.getAttribute("products");
		%>

		<%
		if (message != null) {
		%>
		<p style="color: red;" class="text-center"><%=message%></p>
		<%
		}
		%>

		<%
		if (products != null && !products.isEmpty()) {
		%>
		<div class="product-grid">
			<%
			for (Product product : products) {
			%>
			<div class="product-card">
				<div class="d-flex align-items-center">
					<%
					if (product.getImage() != null && !product.getImage().isEmpty()) {
					%>
					<img
						src="<%=request.getContextPath() + "/images/" + product.getImage()%>"
						alt="Product Image" class="img-thumbnail mt-2"
						style="max-width: 150px;">
					<%
					}
					%>
					<div class="ml-3">
						<h5><%=product.getProductName()%></h5>
						<p><%=product.getDescription()%></p>
						<p>
							<strong>Price:</strong> $<%=product.getPrice()%></p>
						<p>
							<strong>Quantity:</strong>
							<%=product.getStockQuantity()%></p>
						<p>
							<strong>Category:</strong>
							<%=product.getCategory().getCategoryName()%></p>
					</div>
				</div>
				<div class="button-container mt-3">
					<form action="/retailer/deleteProduct" method="post"
						class="button-form">
						<input type="hidden" name="productId"
							value="<%=product.getProductId()%>">
						<button type="submit" class="btn btn-danger btn-sm">
							<i class="fas fa-trash"></i> Delete
						</button>
					</form>
					<a
						href="/retailer/updateProduct?productId=<%=product.getProductId()%>"
						class="btn btn-warning btn-sm"><i class="fas fa-edit"></i>
						Update</a>
				</div>
			</div>
			<%
			}
			%>
		</div>
		<%
		} else {
		%>
		<p class="text-center">No products available for this retailer.</p>
		<%
		}
		%>
	</main>

	<footer>
		<p>&copy; 2024 RevShop</p>
	</footer>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>