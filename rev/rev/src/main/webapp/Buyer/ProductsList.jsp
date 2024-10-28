<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>REVSHOP</title>
<style>
    * {
      box-sizing: border-box;
    }

    .product-list {
      display: flex;
      flex-wrap: wrap;
      justify-content: center;
    }

    .product-item {
      width: 200px;
      margin: 10px;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }

    .product-image {
      width: 100%;
      height: auto;
    }

    .product-name {
      font-weight: bold;
      margin-top: 10px;
    }

    .product-price {
      font-weight: bold;
      color: #ff0000;
    }

    @media screen and (max-width: 600px) {
      .product-item {
        width: 100%;
      }
    }
  </style>
</head>
<body>

  <div class="product-list">
    <div class="product-item">
      <img class="product-image" src="IMAGES/LOGO.png" alt="Product 1">
      <div class="product-name">Product 1</div>
      <div class="product-price">$19.99</div>
    </div>
    <div class="product-item">
      <img class="product-image" src="IMAGES/LOGO.png" alt="Product 1">
      <div class="product-name">Product 1</div>
      <div class="product-price">$19.99</div>
    </div>
    <div class="product-item">
      <img class="product-image" src="IMAGES/LOGO.png" alt="Product 1">
      <div class="product-name">Product 1</div>
      <div class="product-price">$19.99</div>
    </div>
    <div class="product-item">
      <img class="product-image" src="IMAGES/LOGO.png" alt="Product 1">
      <div class="product-name">Product 1</div>
      <div class="product-price">$19.99</div>
    </div>
    <!-- Add more product items here -->
  </div>
</body>
</html>