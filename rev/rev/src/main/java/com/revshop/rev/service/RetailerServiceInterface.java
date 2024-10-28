package com.revshop.rev.service;

import java.util.List;

import com.revshop.rev.entity.Category;
import com.revshop.rev.entity.Order;
import com.revshop.rev.entity.Product;
import com.revshop.rev.entity.ProductReview;
import com.revshop.rev.entity.Retailer;

public interface RetailerServiceInterface {

	int registerRetailer(Retailer retailer);

	int loginRetailer(Retailer retailer);

	Retailer getRetailerByEmail(String email);

	boolean isEmailAlreadyUsed(String email);

	List<Order> viewBuyerOrders(Long retailerId);

	void updateOrderStatus(Long orderId, String status);

	List<ProductReview> viewAllProductReviews(Long retailerId);

	void deleteReviewById(Long reviewId);

	List<Product> viewAllProductsByRetailer(Long retailerId);

	void deleteProductById(Long productId);

	void addProduct(Product product, Long retailerId, int categoryId);

	void updateProduct(Product product, Long retailerId, int categoryId);

	Category getCategoryById(int categoryId);

	Product getProductById(Long productId);

	List<Category> getAllCategories();
}
