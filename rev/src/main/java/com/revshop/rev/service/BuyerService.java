package com.revshop.rev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revshop.rev.dao.BuyerDAOInterface;
import com.revshop.rev.entity.Buyer;
import com.revshop.rev.entity.Cart;
import com.revshop.rev.entity.Category;
import com.revshop.rev.entity.Product;

@Service
public class BuyerService implements BuyerServiceInterface {
	
	@Autowired
	private BuyerDAOInterface dao;
	
	@Override
	public String registerBuyer(Buyer buyer) {
		
		return dao.createProfile(buyer);
	
	}
	@Override
	public String loginBuyer(Buyer buyer) {
	
		return dao.LoginProfile(buyer);
	}
	public List getAllProducts() {
		// TODO Auto-generated method stub
		
		return dao.getAllProducts();	
	}
	@Override
	public List<Product> searchProducts(String keyword) {
		// TODO Auto-generated method stub
		return dao.searchProducts(keyword);
	}
	@Override
	public List<Product> getProductsByCategory(int c) {
		// TODO Auto-generated method stub
		return dao.getProductByCategory(c);
	}
	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return dao.getAllCategory();
	}
	@Override
	public int getIdByCategoryName(Category c) {
		// TODO Auto-generated method stub
		return dao.getCategoryIdByName(c);
	}
	public Product getProductById(long productId) {
		// TODO Auto-generated method stub
		return dao.getProductById(productId);
	}
	@Override
	public Buyer getBuyerByEmail(String email) {
		// TODO Auto-generated method stub
		return dao.getBuyerByEmail(email);
	}
	@Override
	public Cart getCartByBuyerId(Long buyerId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Buyer getBuyerById(Long buyerId) {
		
		return dao.getBuyerById(buyerId);
	}
	@Override
	public void addToCart(Cart cart) {
		// TODO Auto-generated method stub
		dao.addToCart(cart);
		
	}

	@Override
	public int updateEmail(String email, String newEmail) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int updatePassword(String password, String newPassword) {
		// TODO Auto-generated method stub
		return 0;
	}


}
