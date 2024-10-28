<%@ page import= "com.revshop.rev.entity.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Product</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
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
        main.container {
            margin-top: 50px;
            padding: 30px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
        }
        h2 {
            font-size: 1.5rem;
            margin-bottom: 20px;
            color: #343a40;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-control, .form-control-file, .form-control:focus {
            border-radius: 4px;
            border: 1px solid #ced4da;
            box-shadow: none;
        }
        button[type="submit"] {
            background-color: #28a745;
            border: none;
            color: white;
            padding: 10px 20px;
            border-radius: 30px;
            transition: background-color 0.3s ease;
        }
        button[type="submit"]:hover {
            background-color: #218838;
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
    </style>
</head>
<body>
    <header>
        <div class="container">
            <h1>RevShop</h1>
            <nav aria-label="Main Navigation">
            	<a href="/rev/retailer/home" class="btn btn-primary"><i class="fas fa-home"></i> Home</a>
                <a href="/rev/retailer/addProduct" class="btn btn-secondary"><i class="fas fa-plus"></i> Add Products</a>
                <a href="/rev/retailer/products" class="btn btn-secondary"><i class="fas fa-boxes"></i> View Products</a>
                <a href="/rev/retailer/orders" class="btn btn-secondary"><i class="fas fa-shopping-cart"></i> View Orders</a>
                <a href="/rev/retailer/reviews" class="btn btn-secondary"><i class="fas fa-star"></i> View Reviews</a>
                <a href="/rev/retailer/logout" class="btn btn-danger"><i class="fas fa-sign-out-alt"></i> Logout</a>
            </nav>
        </div>
    </header>

    <main class="container">
        <h2>Add New Product</h2>
        <form action="${pageContext.request.contextPath}/retailer/addProduct" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="productName">Product Name:</label>
                <input type="text" class="form-control" id="productName" name="productName" required aria-labelledby="productName">
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea class="form-control" id="description" name="description" rows="4" required aria-labelledby="description"></textarea>
            </div>
            <div class="form-group">
                <label for="price">Price:</label>
                <input type="number" step="0.01" class="form-control" id="price" name="price" required aria-labelledby="price">
            </div>
            <div class="form-group">
                <label for="stockQuantity">Stock Quantity:</label>
                <input type="number" class="form-control" id="stockQuantity" name="stockQuantity" required aria-labelledby="stockQuantity">
            </div>
            <div class="form-group">
                <label for="image">Product Image:</label>
                <input type="file" class="form-control-file" id="image" name="image" required aria-labelledby="image">
            </div>
            <div class="form-group">
                <label for="category">Category:</label>
                <select class="form-control" id="category" name="categoryId" required aria-labelledby="category">
                    <option value="1">Electronics</option>
                    <option value="2">Clothing</option>
                    <option value="3">Home & Kitchen</option>
                    <option value="4">Sports</option>
                    <option value="5">Books</option>
                    <option value="6">Beauty Products</option>
                </select>
            </div>
            <input type="hidden" name="retailerId" value="<%= ((Retailer)session.getAttribute("retailer")).getRetailerId() %>">
            <button type="submit" class="btn btn-primary">Add Product</button>
        </form>
        <div class="mt-3">
            <c:if test="${not empty message}">
                <div class="alert alert-info">${message}</div>
            </c:if>
        </div>
    </main>

    <footer>
        <p>&copy; 2024 RevShop</p>
    </footer>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>