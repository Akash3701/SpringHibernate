package com.revshop.rev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revshop.rev.dao.RetailerDAOInterface;
import com.revshop.rev.entity.Category;
import com.revshop.rev.entity.Order;
import com.revshop.rev.entity.Product;
import com.revshop.rev.entity.ProductReview;
import com.revshop.rev.entity.Retailer;

@Service
public class RetailerService implements RetailerServiceInterface {
	
	@Autowired
	private RetailerDAOInterface retailerDAO;

	@Autowired
	private EmailService emailService;

	@Override
	public int registerRetailer(Retailer retailer) {
	    // Check if the email is already in use
	    if (retailerDAO.isEmailAlreadyUsedDAO(retailer.getEmail())) {
	        return -1; // Return -1 if email is already used
	    }

	    // Register the retailer and check if registration was successful
	    int result = retailerDAO.registerRetailerDAO(retailer);
	    if (result == 1) {
	        // Send registration email using the simplified email service method
	        emailService.sendRegistrationEmail(retailer.getEmail());
	    } 
	    return result;
	}


	@Override
	public int loginRetailer(Retailer retailer) {
		return retailerDAO.loginRetailerDAO(retailer);
	}

	@Override
	public Retailer getRetailerByEmail(String email) {
		return retailerDAO.findRetailerByEmailDAO(email);
	}

	@Override
	public boolean isEmailAlreadyUsed(String email) {
		return retailerDAO.isEmailAlreadyUsedDAO(email);
	}

	@Override
	public List<Order> viewBuyerOrders(Long retailerId) {
		return retailerDAO.findAllBuyersOrdersDAO(retailerId);
	}
	
	

	@Override
	public void updateOrderStatus(Long orderId, String status) {
	    // Update the order status in the database
	    retailerDAO.updateOrderStatusDAO(orderId, status);

	    // Retrieve the updated order details
	    Order order = retailerDAO.findOrderByIdDAO(orderId);
	    if (order != null) {
	        // Get the buyer's email from the order
	        String buyerEmail = order.getBuyer().getEmail();

	        // Send an order status update email using the email service
	        emailService.sendOrderStatusUpdateEmail(buyerEmail, orderId, status);
	    }
	}


	@Override
	public List<ProductReview> viewAllProductReviews(Long retailerId) {
		return retailerDAO.viewAllProductReviewsDAO(retailerId);
	}

	@Override
	public void deleteReviewById(Long reviewId) {
		retailerDAO.deleteReviewByIdDAO(reviewId);
	}

	@Override
	public List<Product> viewAllProductsByRetailer(Long retailerId) {
		return retailerDAO.viewAllProductsByRetailerDAO(retailerId);
	}

	@Override
	public void deleteProductById(Long productId) {
		retailerDAO.deleteProductByIdDAO(productId);
	}

	@Override
	public void addProduct(Product product, Long retailerId, int categoryId) {
		retailerDAO.addProductDAO(product, retailerId, categoryId);
	}

	@Override
	public void updateProduct(Product product, Long retailerId, int categoryId) {
		retailerDAO.updateProductDAO(product,retailerId,categoryId);
	}

	@Override
	public Category getCategoryById(int categoryId) {
		return retailerDAO.getCategoryByIdDAO(categoryId);
	}

	@Override
	public Product getProductById(Long productId) {
		return retailerDAO.getProductById(productId);
	}

	@Override
	public List<Category> getAllCategories() {
		return retailerDAO.getAllCategoriesDAO();
	}
}
