package com.revshop.rev.dao;

import java.util.List;

import com.revshop.rev.entity.Buyer;
import com.revshop.rev.entity.Cart;
import com.revshop.rev.entity.Category;
import com.revshop.rev.entity.Product;

public interface BuyerDAOInterface {



//	String registerBuyerDAO(Buyer buyer);
//
//	String loginBuyerDAO(Buyer buyer);
//
//	List<Product> getAllProductsDAO();
//
//	List<Product> searchProductsDAO(String keyword);
//
//	List<Product> getProductsByCategoryDAO(int c);
//
//	List<Category> getAllCategoriesDAO();
//
//	int getIdByCategoryNameDAO(Category c);
//
//	Product getProductByIdDAO(long productId);
//
//	Buyer getBuyerByEmailDAO(String email);
//
//	Cart getCartByBuyerIdDAO(Long buyerId);
//
//	Buyer getBuyerByIdDAO(Long buyerId);
//
//	void addToCartDAO(Cart cart);
//	
	int updateEmailDAO(String email, String newEmail);

	int updatePassword(String password, String newPassword);

	String createProfile(Buyer buyer);

	String LoginProfile(Buyer buyer);

	List getAllProducts();

	List<Product> searchProducts(String keyword);

	List<Product> getProductByCategory(int c);

	List<Category> getAllCategory();

	int getCategoryIdByName(Category c);

	Product getProductById(long productId);

	Buyer getBuyerByEmail(String email);

	Buyer getBuyerById(Long buyerId);

	void addToCart(Cart cart);
}
