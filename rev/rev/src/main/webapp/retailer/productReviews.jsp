<%@ page import="java.util.List"%>
<%@ page import= "com.revshop.rev.entity.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Reviews</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin-bottom: 100px; /* Prevent footer overlap */
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
        nav a.btn-secondary {
            background-color: #6c757d;
            color: white;
        }
        nav a.btn-secondary:hover {
            background-color: #5a6268;
        }
        nav a.btn-danger {
            background-color: #dc3545;
            color: white;
        }
        nav a.btn-danger:hover {
            background-color: #c82333;
        }
        .review-card {
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
            padding: 15px;
        }
        .review-card img {
            width: 100px;
            height: auto;
            border-radius: 5px;
        }
        .review-card .rating {
            font-size: 1.2rem;
            color: #ffc107; /* Bootstrap's yellow color for star ratings */
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
        <h2 class="text-center">Product Reviews</h2>

        <%
        List<ProductReview> reviews = (List<ProductReview>) request.getAttribute("reviews");
        if (reviews != null && !reviews.isEmpty()) {
        %>
        <div class="row">
            <%
            for (ProductReview review : reviews) {
                // Get the product from the review
              	 com.revshop.rev.entity.Product product = review.getProduct();
            %>
            <div class="col-md-4">
                <div class="review-card">
                    <div class="d-flex align-items-center">
                        <% if (product.getImage() != null && !product.getImage().isEmpty()) { %>
                    <img src="<%= request.getContextPath() + "/images/" + product.getImage() %>" alt="Product Image" class="img-thumbnail mt-2" style="max-width: 150px;">
                <% } %>
                        <div class="ml-3">
                            <h5><%=product.getProductName()%></h5>
                            <p class="rating">
                                <% 
                                    int rating = review.getRating();
                                    for (int i = 0; i < 5; i++) {
                                        if (i < rating) {
                                %>
                                <i class="fas fa-star"></i>
                                <% 
                                        } else {
                                %>
                                <i class="far fa-star"></i>
                                <% 
                                        }
                                    }
                                %>
                            </p>
                            <p><strong>Review:</strong> <%=review.getReviewText()%></p>
                            <form method="post" action="/retailer/deleteReview" style="display: inline;">
                                <input type="hidden" name="reviewId" value="<%=review.getReviewId()%>">
                                <button type="submit" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i> Delete</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <%
            }
            %>
        </div>
        <%
        } else {
        %>
        <p class="text-center">No reviews available at the moment.</p>
        <%
        }
        %>
    </main>

    <footer>
        <p>&copy; 2024 RevShop</p>
    </footer>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>