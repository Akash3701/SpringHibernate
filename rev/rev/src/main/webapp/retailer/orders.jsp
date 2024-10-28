<%@ page import="java.util.List"%>
<%@ page import= "com.revshop.rev.entity.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Orders</title>
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
        nav a.btn-secondary:hover {
            background-color: #6c757d;
        }
        nav a.btn-danger:hover {
            background-color: #d9534f;
        }
        .table th {
            background-color: #007bff;
            color: white;
        }
        .table td, .table th {
            padding: 15px;
            text-align: center;
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
        <h2 class="text-center">Orders List</h2>

        <%
        String message = (String) request.getAttribute("message");
        List<Order> orders = (List<Order>) request.getAttribute("orders");
        %>

        <%
        if (message != null) {
        %>
        <p style="color: red;" class="text-center"><%=message%></p>
        <%
        }
        %>

        <table class="table table-bordered mt-4">
            <thead>
                <tr>
                    <th>Order No</th>
                    <th>Billing Address</th>
                    <th>Order Status</th>
                    <th>Shipping Address</th>
                    <th>Total Amount</th>
                    <th>Buyer Name</th>
                    <th>Products</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                if (orders != null && !orders.isEmpty()) {
                    for (Order order : orders) { %>
                        <tr>
                            <td><%=order.getOrderId()%></td>
                            <td><%=order.getBillingAddress()%></td>
                            <td>
                                <form action="/retailer/updateOrderStatus" method="POST">
                                    <input type="hidden" name="orderId" value="<%=order.getOrderId()%>" />
                                    <select name="status" class="form-control">
                                        <option value="Pending" <%= "Pending".equals(order.getOrderStatus()) ? "selected" : "" %>>Pending</option>
                                        <option value="Shipped" <%= "Shipped".equals(order.getOrderStatus()) ? "selected" : "" %>>Shipped</option>
                                        <option value="Delivered" <%= "Delivered".equals(order.getOrderStatus()) ? "selected" : "" %>>Delivered</option>
                                        <option value="Canceled" <%= "Canceled".equals(order.getOrderStatus()) ? "selected" : "" %>>Canceled</option>
                                    </select>
                            </td>
                            <td><%=order.getShippingAddress()%></td>
                            <td>$<%=order.getTotalAmount()%></td>
                            <td><%=order.getBuyer().getName()%></td>
                            <td>
                                <% List<OrderItem> items = order.getOrderItems();
                                for (OrderItem item : items) {
                                    Product product = item.getProduct(); %>
                                    <p><%=product.getProductName()%></p>
                                <% } %>
                            </td>
                            <td><button type="submit" class="btn btn-primary">Update Status</button></td>
                            </form>
                        </tr>
                    <% } 
                } else { %>
                    <tr><td colspan="8" class="text-center">No orders found.</td></tr>
                <% } %>
            </tbody>
        </table>
    </main>

    <footer>
        <p>&copy; 2024 RevShop</p>
    </footer>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>