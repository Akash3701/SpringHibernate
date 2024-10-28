package com.revshop.rev.dao;

import java.util.List;

import com.revshop.rev.entity.Category;
import com.revshop.rev.entity.Order;
import com.revshop.rev.entity.Product;
import com.revshop.rev.entity.ProductReview;
import com.revshop.rev.entity.Retailer;

public interface RetailerDAOInterface {

	int registerRetailerDAO(Retailer retailer);

	int loginRetailerDAO(Retailer retailer);

	Retailer findRetailerByEmailDAO(String email);

	boolean isEmailAlreadyUsedDAO(String email);

	List<Order> findAllBuyersOrdersDAO(Long retailerId);

	Order findOrderByIdDAO(Long orderId);

	void updateOrderStatusDAO(Long orderId, String status);

	List<ProductReview> viewAllProductReviewsDAO(Long retailerId);

	void deleteReviewByIdDAO(Long reviewId);

	List<Product> viewAllProductsByRetailerDAO(Long retailerId);

	void deleteProductByIdDAO(Long productId);

	void addProductDAO(Product product, Long retailerId, int categoryId);

	void updateProductDAO(Product product, Long retailerId, int categoryId);

	Category getCategoryByIdDAO(int categoryId);

	Product getProductById(Long productId);

	List<Category> getAllCategoriesDAO();
}
