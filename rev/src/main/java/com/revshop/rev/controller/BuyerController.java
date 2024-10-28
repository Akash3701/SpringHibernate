package com.revshop.rev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.revshop.rev.entity.Buyer;
import com.revshop.rev.entity.Cart;
import com.revshop.rev.entity.CartItem;
import com.revshop.rev.entity.Category;
import com.revshop.rev.entity.Product;
import com.revshop.rev.service.BuyerServiceInterface;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.revshop.rev.dao.BuyerDAOInterface;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BuyerController {

	@Autowired 
	private BuyerServiceInterface bs;
	
	
	 @RequestMapping("/register")
	    public String showRegistrationForm() {
	        return "buyerRegistration";
	    }
	 
	@PostMapping("/registerBuyer")
	public ModelAndView registerBuyer(@RequestParam String name, @RequestParam String email, @RequestParam ("password")String password,@RequestParam("contactNo") String contact,@RequestParam("city") String city)
	{
		System.out.println(name);
		System.out.println(password);
		System.out.println(email);
		System.out.println(contact);
		System.out.println(city);
		Buyer buyer = new Buyer();
		ModelAndView mv = new ModelAndView();
		buyer.setName(name);
		buyer.setEmail(email);
		buyer.setPassword(password);
		buyer.setContactNo(contact);
		buyer.setCity(city);
		
		
		String b="unsuccesfull";
		String s=bs.registerBuyer(buyer);
		System.out.println(s);
		
		if(s.equals("success")) {
		    b = "successfull";
		    mv.setViewName("redirect:/login"); // Use redirect to update the URL
		} else {
		    mv.setViewName("Buyer/buyerRegistration.jsp");
		}

		mv.setViewName("redirect:/Buyer/login.jsp");
    return mv;
	}
	

    @RequestMapping("/login")
    public String showLoginForm() {
        return "Buyer/login.jsp"; // Login page
    }

   @PostMapping("/Login")
    public ModelAndView loginBuyer(HttpServletRequest request, @RequestParam("email") String email, @RequestParam("password") String password) {
        Buyer buyer = new Buyer(); // Creating a buyer object
        buyer.setEmail(email); // Setting email
        buyer.setPassword(password); // Setting password

        String loginResult = bs.loginBuyer(buyer); // Calling the login method from the service layer

        String message = "Login Failed"; // Default message

        ModelAndView mv = new ModelAndView(); // Creating a ModelAndView object

        if ("success".equals(loginResult)) { // Checking if login is successful
            HttpSession session = request.getSession(true); // Creating a session
            Buyer loggedInBuyer = bs.getBuyerByEmail(email); // Fetching the logged-in buyer details
            session.setAttribute("buyer", loggedInBuyer); // Setting the buyer details in the session

            mv.setViewName("redirect:/Buyer/buyerHomePage"); // Redirecting to the buyer home page on successful login
        } else {
            mv.addObject("loginResult", message); // Adding the message to the model
            mv.setViewName("redirect:/Buyer/login.jsp"); // Redirecting to the login page on failure
        }
        mv.setViewName("redirect:/Buyer/buyerHomePage.jsp");
        return mv; // Returning the ModelAndView object
    }

    @RequestMapping("/Buyer/buyerHomePage")
    public String showBuyerHomePage() {
        return "buyerHomePage"; // Return the view name without ".jsp", Spring will resolve it
    }


    
    
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate(); // Invalidate the session
	    return "redirect:/login"; // Redirect to login page
	}

    @GetMapping("/buyerHomePage")
    public ModelAndView buyerHomepage() {
    	
        ModelAndView mav = new ModelAndView();
        List <Product> products = bs.getAllProducts();
        List<Category> categories=bs.getAllCategories();
        System.out.println(products);
        System.out.println(categories);
        mav.addObject("products", products);
        
        mav.addObject("categories",categories);
        mav.setViewName("buyerHomePage.jsp");
        return mav;
    }
//
    @GetMapping("/search")
    public ModelAndView searchProducts(@RequestParam("keyword") String keyword) {
        ModelAndView mav = new ModelAndView(); // Replace with the actual view name, like buyerHomepage.jsp
        System.out.println(keyword);
        List<Category> categories=bs.getAllCategories();
        List<Product> products = bs.searchProducts(keyword);
        mav.addObject("products", products);
        mav.addObject("categories",categories);
        mav.setViewName("buyerHomePage.jsp");
        return mav;
    }
    @GetMapping("/category-products")
    public ModelAndView viewByCategory(@RequestParam("categoryName") String categoryName) {
    	Category c=new Category();
    	c.setCategoryName(categoryName);
    	  List<Category> categories=bs.getAllCategories();
        ModelAndView mav = new ModelAndView();
        System.out.println(c.getCategoryName());
        int catId=bs.getIdByCategoryName(c);
        System.out.println(catId);
        c.setCategoryId(catId);
        List<Product> products = bs.getProductsByCategory(c.getCategoryId());
        mav.addObject("products", products);
        mav.addObject("categories",categories);
        mav.setViewName("buyerHomePage.jsp");
        return mav;
    
    }
    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("productId") long productId, @RequestParam("quantity") int quantity, HttpSession session) {
        Long buyerId = (Long) session.getAttribute("buyerId");

        if (buyerId == null) {
            // Handle case where buyerId is null
            return "redirect:/login"; // or an appropriate error page
        }

        Product product = bs.getProductById(productId);
        Cart cart = bs.getCartByBuyerId(buyerId);

        if (cart == null) {
            cart = new Cart();
            cart.setBuyer(bs.getBuyerById(buyerId));
            cart.setCartItems(new ArrayList<>()); // Initialize the cartItems list
        }

        // Check if the item already exists in the cart
        boolean itemExists = false;
        for (CartItem item : cart.getCartItems()) {
            if (item.getProduct().getProductId().equals(productId)) {
                // Item already exists, update quantity
                item.setQuantity(item.getQuantity() + quantity);
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            // Item doesn't exist, add new item
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setCart(cart);
            cart.getCartItems().add(cartItem);
        }

        bs.addToCart(cart); // Use DAO method to handle both inserts and updates

        return "redirect:/buyerHomePage.jsp";
    }

    @PostMapping("/remove-from-cart")
    public String removeFromCart(@RequestParam("cartItemId") Long cartItemId, HttpSession session) {
        Long buyerId = (Long) session.getAttribute("buyerId");

        if (buyerId == null) {
            // Handle case where buyerId is null
            return "redirect:/login"; // or an appropriate error page
        }

        Cart cart = bs.getCartByBuyerId(buyerId);

        if (cart != null) {
            CartItem itemToRemove = cart.getCartItems().stream()
                    .filter(item -> item.getCartItemId().equals(cartItemId))
                    .findFirst().orElse(null);

            if (itemToRemove != null) {
                cart.getCartItems().remove(itemToRemove);
                bs.addToCart(cart); // Save the updated cart
            }
        }

        return "redirect:/buyerHomePage";
    }

    @PostMapping("/update-cart")
    public String updateCart(@RequestParam("cartItemId") Long cartItemId, @RequestParam("quantity") int quantity, HttpSession session) {
        Long buyerId = (Long) session.getAttribute("buyerId");

        if (buyerId == null) {
            // Handle case where buyerId is null
            return "redirect:/login"; // or an appropriate error page
        }

        Cart cart = bs.getCartByBuyerId(buyerId);

        if (cart != null) {
            CartItem itemToUpdate = cart.getCartItems().stream()
                    .filter(item -> item.getCartItemId().equals(cartItemId))
                    .findFirst().orElse(null);

            if (itemToUpdate != null) {
                itemToUpdate.setQuantity(quantity); // Update the quantity
                bs.addToCart(cart); // Save the updated cart with new quantity
            }
        }

        return "redirect:/buyerHomePage"; // Redirect back to the buyer homepage or cart page
    }


}
