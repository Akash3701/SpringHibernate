package com.revshop.rev.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;

	private String categoryName;
	
    @OneToMany(mappedBy = "category") // Ensure 'category' matches the Product entity property
    private List<Product> products;

	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public  List<Product> getProducts() {
		return products;
	}
	public  void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
	

	

}
