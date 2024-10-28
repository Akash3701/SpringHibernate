package com.revshop.rev.service;

import java.util.List;

import com.revshop.rev.entity.Buyer;
import com.revshop.rev.entity.Cart;
import com.revshop.rev.entity.Category;
import com.revshop.rev.entity.Product;

public interface BuyerServiceInterface {
	
	String registerBuyer(Buyer buyer);
	
	String loginBuyer(Buyer buyer);
	
	List<Product> getAllProducts();

	List<Product> searchProducts(String keyword);

	List<Product> getProductsByCategory(int c);

	List<Category> getAllCategories();

	int getIdByCategoryName(Category c);

	Product getProductById(long productId);

	Buyer getBuyerByEmail(String email);

	Cart getCartByBuyerId(Long buyerId);

	Buyer getBuyerById(Long buyerId);

	void addToCart( Cart cart);
	
	int updateEmail(String email, String newEmail); 
	
	int updatePassword(String password, String newPassword);

	
}
