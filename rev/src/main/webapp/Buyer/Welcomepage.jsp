<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Arial">
  	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
  	<link rel="stylesheet" href="Welcomepage.css">
	<title>REVSHOP</title>
</head>
<body>
<header class="site-header">
  <div class="site-identity">
    <a href="HomePage.jsp"><img src="IMAGES/LOGO.png" alt="Site Logo" /></a>
    <h1><a href="HomePage.jsp">REVSHOP</a></h1>
  </div>  
  <nav class="search-bar">
  
    <input type="text" placeholder="Search Product, Brands and More" class="search-input">
    <button type="submit" class="search-button"><i class="fa fa-search"></i></button>
    <button class="cart" onclick="window.location.href='login.jsp'"><i class="fa fa-user-circle"></i>Login</button>
    <!-- <button onclick="window.location.href='login.jsp'" class="login"><i class="fa fa-user-circle"></i>Login</button> -->
   	<button class="cart" onclick="window.location.href='view-cart.jsp'"><i class="fa-solid fa-cart-shopping"></i>Cart</button>
    <button class="favourite"><i class="fa-solid fa-shop"></i>Become a Seller</button>
  </nav>
</header>


   <!-- Modal -->
   <div class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>

        <div class="modal-container">

            <!-- upper button section to select
                 the login or signup form -->
            <div class="modal-slider"></div>
            <div class="modal-btn">
                <button class="modal-login">Login</button>
                <button class="modal-signup">Signup</button>
            </div>

            <!-- Form section that contains the
                 login and the signup form -->
            <div class="modal-form-section">

                <!-- login form -->
                <div class="modal-login-box">
                <form action="Login" method="post">
                    <input type="email" class="email ele" name="email" placeholder="youremail@email.com">
                    <input type="password" class="password ele" name="password" placeholder="password">
                    <button class="forgot-password">Forgot Password?</button>
                    <button class="clkbtn">Login</button>
                    </form>
                </div>

                <!-- signup form -->
                <div class="modal-signup-box">
                    <input type="text" class="name ele" placeholder="Enter your name">
                    <input type="email" class="email ele" placeholder="youremail@email.com">
                    <input type="password" class="password ele" placeholder="password">
                    <!-- <input type="password" class="password ele" placeholder="Confirm password"> -->
                    <input type="tel" class="password ele" placeholder="Phone Number">
                    <input type="text" class="password ele" placeholder="City">
                    <button class="clkbtn">Signup</button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="image-container">
  <div class="image-slider">
    <a href="" class="image-item">
      <img src="IMAGES/Category-Mobiles.png" alt="Mobiles">
      <button class="image-title">Mobiles</button>
    </a>
    <a href="" class="image-item">
      <img src="IMAGES/CategoryFashion.png" alt="Fashion">
      <button class="image-title">Fashion</button>
    </a>
    <a href="" class="image-item">
      <img src="IMAGES/Category-Electronics-removebg-preview.png" alt="Electronics">
      <button class="image-title">Electronics</button>
    </a>
    <a href="" class="image-item">
      <img src="IMAGES/CategoryHome_Furniture.png" alt="Home & Furniture">
      <button class="image-title">Home & Furniture</button>
    </a>
    <a href="" class="image-item">
      <img src="IMAGES/CategoryBeauty.png" alt="Beauty">
      <button class="image-title">Beauty</button>
    </a>
    <a href="" class="image-item">
      <img src="IMAGES/CategoryAppliances.png" alt="Appliances">
      <button class="image-title">Appliances</button>
    </a>
    <a href="" class="image-item">
      <img src="IMAGES/CategoryToys.png" alt="Toys & More">
      <button class="image-title">Toys & More</button>
    </a>
  </div>
</div>


<div class="carousel">
  <span class="carousel-control-left">&#10094;</span>
  <span class="carousel-control-right">&#10095;</span>
  <div class="carousel-content">
      <!-- <video autoplay loop muted playsinline class="back-video" src="images/video1.mp4"></video> -->
      <!-- <img src="images/slide image1.jpg" alt="Slide 1" />
      <img src="images/slide image2.jpg" alt="Slide 2" /> -->
      <img src="IMAGES/Offer1.jpg" alt="Banner" />
      <img src="IMAGES/Offer2.jpg" alt="Banner" />
      <img src="IMAGES/offfer3.png" alt="Banner" />
  </div>
</div>


<!-- Brands on Board Container -->
<div class="container brands-on-board">
  <h2>Brands on Board</h2>
  <div class="slider infinite-scroll">
    <div class="slide"><img src="IMAGES/h1.png" alt="Brand 1"></div>
    <div class="slide"><img src="IMAGES/h2.png" alt="Brand 2"></div>
    <div class="slide"><img src="IMAGES/h3.png" alt="Brand 3"></div>
    <div class="slide"><img src="IMAGES/h4.png" alt="Brand 4"></div>
    <div class="slide"><img src="IMAGES/h5.png" alt="Brand 5"></div>
    <div class="slide"><img src="IMAGES/h6.png" alt="Brand 6"></div>
    <div class="slide"><img src="IMAGES/h8.png" alt="Brand 8"></div>
    <div class="slide"><img src="IMAGES/h9.png" alt="Brand 9"></div>
    <div class="slide"><img src="IMAGES/h10.jpeg" alt="Brand 10"></div>
    <div class="slide"><img src="IMAGES/h11.png" alt="Brand 11"></div>
    <div class="slide"><img src="IMAGES/h12.png" alt="Brand 12"></div>
  	<div class="slide"><img src="IMAGES/h13.png" alt="Brand 13"></div>
    <div class="slide"><img src="IMAGES/h14.png" alt="Brand 13"></div>
    <div class="slide"><img src="IMAGES/h15.jpeg" alt="Brand 10"></div>
  </div>
</div>


<!-- <div class="image-container">
<h1>Top Picks</h1>
  <div class="image-slider">
    <a href="makeup.jsp" class="image-item">
      <img src="images/toppicks1.jpg" alt="Makeup">
      <button class="image-title">Lipstick</button>
    </a>
    <a href="skincare.jsp" class="image-item">
      <img src="images/toppicks2.jpg" alt="Skin Care">
      <button class="image-title">Brush Set</button>
    </a>
    <a href="haircare.jsp" class="image-item">
      <img src="images/toppicks3.jpg" alt="Hair Care">
      <button class="image-title">Y2K</button>
    </a>
    <a href="bathbody.jsp" class="image-item">
      <img src="images/toppicks4.jpg" alt="Bath & Body">
      <button class="image-title">Sunscreen</button>
    </a>
    <a href="bestsellers.jsp" class="image-item">
      <img src="images/toppicks5.jpg" alt="Best Sellers">
      <button class="image-title">Foundation</button>
    </a> -->

<script src="MyJavaScript/homepage.js" defer></script> <!-- Link to the external JavaScript file -->
<script>


document.addEventListener("DOMContentLoaded", function () {
	  const brandsSlider = document.querySelector('.brands-on-board .slider');
	  const slideWidth = 300; // Width of each slide
	  let position = 0;

	  function startInfiniteScroll() {
	    // Move the slider to the left continuously
	    position -= 1; // Adjust speed by changing the decrement value

	    if (position <= -slideWidth * 5) { // Check when to reset (number of original slides * width)
	      position = 0; // Reset to the original position
	    }

	    brandsSlider.style.transform = `translateX(${position}px)`;
	    requestAnimationFrame(startInfiniteScroll);
	  }

	  startInfiniteScroll(); // Start the continuous loop
	});
</script>
</body>
<footer class="footer">
  <div class="footer-subscribe">
    <h3>Don’t miss an update!</h3>
    <p>The latest product drops, offers and stories straight to your phone, plus 20% off your next order</p>
    <form class="subscribe-form">
      <input type="email" placeholder="*Enter your email" required>
      <button type="submit">SUBSCRIBE</button>
    </form>
  </div>
  <div class="footer-content">
    <div class="footer-section">
      <h4>Quick Links</h4>
      <ul>
        <li><a href="#">About Us</a></li>
        <li><a href="#">Terms & Conditions</a></li>
        <li><a href="#">Return Policy</a></li>
      </ul>
    </div>
    <div class="footer-section">
      <h4>Make Money with Us</h4>
      <ul>
        <li><a href="retailerRegister.jsp">Sell on RevShop</a></li>
      </ul>
    </div>
    <div class="footer-section">
      <h4>Help Desk</h4>
      <p>Call: +91 9494075192</p>
      <p>Email: yoganandanaidu222@gmail.com</p>
      <p>Monday-Saturday 10AM to 7PM</p>
    </div>
    <div class="footer-section">
      <h4>Follow us on</h4>
      <div class="social-icons">
        <a href="#"><i class="fab fa-instagram"></i></a>
        <a href="#"><i class="fab fa-twitter"></i></a>
        <a href="#"><i class="fab fa-youtube"></i></a>
      </div>
    </div>
  </div>
</footer>
<script src="Welcomepage.js"></script>
<script></script>
</html>
