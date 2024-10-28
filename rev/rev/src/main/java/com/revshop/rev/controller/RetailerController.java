package com.revshop.rev.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.revshop.rev.entity.Category;
import com.revshop.rev.entity.Order;
import com.revshop.rev.entity.Product;
import com.revshop.rev.entity.ProductReview;
import com.revshop.rev.entity.Retailer;
import com.revshop.rev.service.RetailerServiceInterface;

@Controller
@RequestMapping("/retailer")
public class RetailerController {

	@Autowired
	private RetailerServiceInterface retailerService;

	private static final String UPLOAD_DIR = "C:/Users/dell/Downloads/rev/rev/src/main/webapp/ALL-IMAGES";
	// Show registration form
	@RequestMapping("/register")
	public ModelAndView showRegistrationForm(@RequestParam(required = false) String emailError) {
		ModelAndView modelAndView = new ModelAndView("registration.jsp");
		if (emailError != null) {
			modelAndView.addObject("emailError", emailError);
		}
		return modelAndView;
	}

	// Register a new retailer
	@PostMapping("/register")
	public ModelAndView registerRetailer(@RequestParam String business_name, @RequestParam String contact_no,
			@RequestParam String email, @RequestParam("password") String password) {
		ModelAndView modelAndView = new ModelAndView("registration.jsp");

		if (retailerService.isEmailAlreadyUsed(email)) {
			modelAndView.addObject("emailError", "Email is already in use. Please choose another.");
			modelAndView.addObject("success", false);
			return modelAndView;
		}

		Retailer retailer = new Retailer();
		retailer.setBusinessName(business_name);
		retailer.setContactNo(contact_no);
		retailer.setEmail(email);
		retailer.setPassword(password);

		int result = retailerService.registerRetailer(retailer);

		if (result == 1) {
			return new ModelAndView("redirect:/retailer/login");
		} else {
			modelAndView.setViewName("registration.jsp");
			modelAndView.addObject("failed", true); // Fix the success/fail condition
		}
		return modelAndView;
	}

	// Show login form
	@RequestMapping("/login")
	public String showLoginForm() {
		return "login.jsp";
	}

	// Login a retailer and set session
	@PostMapping("/login")
	public void loginRetailer(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Retailer retailer = new Retailer();
		retailer.setEmail(email);
		retailer.setPassword(password);

		int result = retailerService.loginRetailer(retailer);

		if (result == 1) {
			Retailer loggedInRetailer = retailerService.getRetailerByEmail(email);
			session.setAttribute("retailer", loggedInRetailer); // Set the authenticated retailer
			response.sendRedirect(request.getContextPath() + "/retailer/home"); // Redirect to homepage
		} else if (result == 2) { // Assuming result 2 is for blocked users
			response.sendRedirect(request.getContextPath() + "/retailer/login?error=blocked");
		} else if (result == 3) { // Assuming result 3 is for not-approved users
			response.sendRedirect(request.getContextPath() + "/retailer/login?error=not-approved");
		} else {
			response.sendRedirect(request.getContextPath() + "/retailer/login?error=failed");
		}
	}

	// Check if email is already used
	@GetMapping("/check-email")
	public ModelAndView checkEmail(@RequestParam String email) {
		ModelAndView modelAndView = new ModelAndView("emailCheckResult");

		boolean isUsed = retailerService.isEmailAlreadyUsed(email);
		modelAndView.addObject("emailUsed", isUsed);

		return modelAndView;
	}

	// Logout retailer and invalidate session
	@GetMapping("/logout")
	public void logoutRetailer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession existingSession = request.getSession(false);
		if (existingSession != null) {
			existingSession.invalidate(); // Invalidate the session
		}
		response.sendRedirect(request.getContextPath() + "/retailer/login"); // Redirect to login page
	}

	@GetMapping("/home")
	public ModelAndView showHomePage(HttpSession session) {
		Retailer retailer = (Retailer) session.getAttribute("retailer");
		if (retailer == null) {
			return new ModelAndView("redirect:/retailer/login");
		}

		ModelAndView modelAndView = new ModelAndView("HomePage.jsp");
		modelAndView.addObject("retailerName", retailer.getBusinessName());
		return modelAndView;
	}

	// View orders placed by buyers
	@GetMapping("/orders")
	public ModelAndView viewBuyerOrders(HttpSession session) {
		Retailer retailer = (Retailer) session.getAttribute("retailer");
		if (retailer == null) {
			return new ModelAndView("redirect:/retailer/login");
		}

		ModelAndView modelAndView = new ModelAndView("orders.jsp");
		try {
			List<Order> orders = retailerService.viewBuyerOrders(retailer.getRetailerId());
			if (orders != null && !orders.isEmpty()) {
				modelAndView.addObject("orders", orders);
			} else {
				modelAndView.addObject("message", "No orders found.");
			}
		} catch (Exception e) {
			modelAndView.addObject("message", "Failed to retrieve orders.");
			e.printStackTrace();
		}

		return modelAndView;
	}

	// Update order status
	@PostMapping("/updateOrderStatus")
	public String updateOrderStatus(@RequestParam Long orderId, @RequestParam String status, HttpSession session,
			RedirectAttributes redirectAttributes) {
		Retailer retailer = (Retailer) session.getAttribute("retailer");
		if (retailer == null) {
			return "redirect:/retailer/login";
		}

		try {
			retailerService.updateOrderStatus(orderId, status);
			redirectAttributes.addFlashAttribute("message", "Order status updated successfully.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Failed to update order status.");
			e.printStackTrace();
		}

		return "redirect:/retailer/orders";
	}

	// View all product reviews
	@GetMapping("/reviews")
	public ModelAndView viewAllProductReviews(HttpSession session) {
		Retailer retailer = (Retailer) session.getAttribute("retailer");
		if (retailer == null) {
			return new ModelAndView("redirect:/retailer/login");
		}

		ModelAndView modelAndView = new ModelAndView("productReviews.jsp");
		try {
			List<ProductReview> reviews = retailerService.viewAllProductReviews(retailer.getRetailerId());
			if (reviews != null && !reviews.isEmpty()) {
				modelAndView.addObject("reviews", reviews);
			} else {
				modelAndView.addObject("message", "No reviews found.");
			}
		} catch (Exception e) {
			modelAndView.addObject("message", "Failed to retrieve reviews.");
		}

		return modelAndView;
	}

	// Delete a product review
	@PostMapping("/deleteReview")
	public ModelAndView deleteReview(@RequestParam Long reviewId, HttpSession session) {
		Retailer retailer = (Retailer) session.getAttribute("retailer");
		if (retailer == null) {
			return new ModelAndView("redirect:/retailer/login");
		}

		try {
			retailerService.deleteReviewById(reviewId); 
			return new ModelAndView("redirect:/retailer/reviews"); 
		} catch (Exception e) {
			ModelAndView modelAndView = new ModelAndView("productReviews.jsp");
			modelAndView.addObject("message", "Failed to delete review.");
			return modelAndView;
		}
	}

	// View all products by Retailer ID
	@GetMapping("/products")
	public ModelAndView viewAllProductsByRetailer(HttpSession session) {
		Retailer retailer = (Retailer) session.getAttribute("retailer");
		if (retailer == null) {
			return new ModelAndView("redirect:/retailer/login");
		}

		ModelAndView modelAndView = new ModelAndView("products.jsp");
		try {
			List<Product> products = retailerService.viewAllProductsByRetailer(retailer.getRetailerId());

			if (products != null && !products.isEmpty()) {
				modelAndView.addObject("products", products);
			} else {
				modelAndView.addObject("message", "No products found.");
			}
		} catch (Exception e) {
			modelAndView.addObject("message", "Failed to retrieve products.");
			e.printStackTrace();
		}

		return modelAndView;
	}

	@PostMapping("/deleteProduct")
	public String deleteProduct(@RequestParam Long productId, RedirectAttributes redirectAttributes) {
		try {
			retailerService.deleteProductById(productId);
			redirectAttributes.addFlashAttribute("message", "Product deleted successfully.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Failed to delete product.");
			e.printStackTrace();
		}
		return "redirect:/retailer/products";
	}

	@RequestMapping("/addProduct")
	public ModelAndView showAddProductForm(HttpSession session) {
		Retailer retailer = (Retailer) session.getAttribute("retailer");
		if (retailer == null) {
			return new ModelAndView("redirect:/retailer/login");
		}

		ModelAndView modelAndView = new ModelAndView("addProduct.jsp");
		return modelAndView;
	}

	@PostMapping("/addProduct")
	public String addProduct(@RequestParam String productName, @RequestParam String description,
			@RequestParam double price, @RequestParam int stockQuantity, @RequestParam("image") MultipartFile image,
			@RequestParam Long retailerId, @RequestParam int categoryId, RedirectAttributes redirectAttributes,
			HttpSession session) {
		Retailer retailer = (Retailer) session.getAttribute("retailer");
		if (retailer == null) {
			return "redirect:/retailer/login";
		}

		try {
			File uploadDir = new File(UPLOAD_DIR);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

			String fileName = image.getOriginalFilename();
			String filePath = UPLOAD_DIR + "/" + fileName;
			File file = new File(filePath);
			image.transferTo(file);

			Product product = new Product();
			product.setProductName(productName);
			product.setDescription(description);
			product.setPrice(price);
			product.setStockQuantity(stockQuantity);
			product.setImage(fileName);

			retailerService.addProduct(product, retailerId, categoryId);

			redirectAttributes.addFlashAttribute("message", "Product added successfully.");
		} catch (IOException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Failed to upload product image.");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Failed to add product.");
		}

		return "redirect:/retailer/products";
	}

	@RequestMapping("/updateProduct")
	public ModelAndView showUpdateProductForm(@RequestParam Long productId, HttpSession session) {
		Retailer retailer = (Retailer) session.getAttribute("retailer");
		if (retailer == null) {
			return new ModelAndView("redirect:/retailer/login");
		}

		ModelAndView modelAndView = new ModelAndView("updateProduct.jsp");
		try {
			Product product = retailerService.getProductById(productId);
			List<Category> categories = retailerService.getAllCategories();
			if (product != null) {
				modelAndView.addObject("product", product);
				modelAndView.addObject("categories", categories);
			} else {
				modelAndView.addObject("message", "Product not found.");
			}
		} catch (Exception e) {
			modelAndView.addObject("message", "Failed to retrieve product details.");
			e.printStackTrace();
		}

		return modelAndView;
	}

	@PostMapping("/updateProduct")
	public String updateProduct(@RequestParam Long productId, @RequestParam String productName,
			@RequestParam String description, @RequestParam double price, @RequestParam int stockQuantity,
			@RequestParam("image") MultipartFile image, @RequestParam int categoryId,
			RedirectAttributes redirectAttributes, HttpSession session) {
		Retailer retailer = (Retailer) session.getAttribute("retailer");
		if (retailer == null) {
			return "redirect:/retailer/login";
		}

		try {
			Product product = retailerService.getProductById(productId);
			if (product != null) {
				product.setProductName(productName);
				product.setDescription(description);
				product.setPrice(price);
				product.setStockQuantity(stockQuantity);

				if (!image.isEmpty()) {
					File uploadDir = new File(UPLOAD_DIR);
					if (!uploadDir.exists()) {
						uploadDir.mkdirs();
					}

					String fileName = image.getOriginalFilename();
					String filePath = UPLOAD_DIR + "/" + fileName;
					File file = new File(filePath);
					image.transferTo(file);

					product.setImage(fileName);
				}

				Category category = retailerService.getCategoryById(categoryId);
				if (category != null) {
					product.setCategory(category);
				} else {
					redirectAttributes.addFlashAttribute("message", "Invalid category ID.");
					return "redirect:/retailer/products";
				}

				retailerService.updateProduct(product, retailer.getRetailerId(), categoryId);

				redirectAttributes.addFlashAttribute("message", "Product updated successfully.");
			} else {
				redirectAttributes.addFlashAttribute("message", "Product not found.");
			}
		} catch (IOException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Failed to upload product image.");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Failed to update product.");
		}

		return "redirect:/retailer/products";
	}
}