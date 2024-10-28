<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import= "com.revshop.rev.entity.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Product</title>
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
        <h2 class="text-center">Edit Product</h2>

        <%
        Product product = (Product) request.getAttribute("product");
        List<Category> categories = (List<Category>) request.getAttribute("categories");

        if (categories == null) {
            categories = new ArrayList<>();
        }

        if (product == null) {
        }
        %>

        <form action="/retailer/updateProduct" method="post" enctype="multipart/form-data">
            <input type="hidden" name="productId" value="<%= product.getProductId() %>">
            <div class="form-group">
                <label for="productName">Product Name</label>
                <input type="text" class="form-control" id="productName" name="productName" value="<%= product.getProductName() %>" required>
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea class="form-control" id="description" name="description" rows="4" required><%= product.getDescription() %></textarea>
            </div>
            <div class="form-group">
                <label for="price">Price</label>
                <input type="number" step="0.01" class="form-control" id="price" name="price" value="<%= product.getPrice() %>" required>
            </div>
            <div class="form-group">
                <label for="stockQuantity">Stock Quantity</label>
                <input type="number" class="form-control" id="stockQuantity" name="stockQuantity" value="<%= product.getStockQuantity() %>" required>
            </div>
            <div class="form-group">
                <label for="image">Image</label>
                <input type="file" class="form-control-file" id="image" name="image">
                <% if (product.getImage() != null && !product.getImage().isEmpty()) { %>
                    <img src="<%= request.getContextPath() + "/images/" + product.getImage() %>" alt="Product Image" class="img-thumbnail mt-2" style="max-width: 150px;">
                <% } %>
            </div>
            <div class="form-group">
                <label for="categoryId">Category</label>
                <select class="form-control" id="categoryId" name="categoryId" required>
                    <% for (Category category : categories) { %>
                        <option value="<%= category.getCategoryId() %>" <%= product.getCategory().getCategoryId() == category.getCategoryId() ? "selected" : "" %>>
                            <%= category.getCategoryName() %>
                        </option>
                    <% } %>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Update Product</button>
            <a href="/retailer/products" class="btn btn-secondary">Back to Products</a>
        </form>
    </main>

    <footer>
        <p>&copy; 2024 RevShop</p>
    </footer>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>