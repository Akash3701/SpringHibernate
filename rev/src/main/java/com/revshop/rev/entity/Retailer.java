package com.revshop.rev.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "retailers")
@NamedQueries({
    @NamedQuery(
        name = "NewRetailerRequests", 
        query = "FROM Retailer WHERE isApproved = false"
    ),
    @NamedQuery(
        name = "ApprovedRetailers", 
        query = "FROM Retailer WHERE isApproved = true"
    )
})
public class Retailer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long retailerId;

	private String businessName;
	private String email;
	private String password;
	private String contactNo;
	private boolean isApproved;
	private boolean isBlocked; 

	@OneToMany(mappedBy = "retailer", cascade = CascadeType.ALL)
	private List<Product> products;

	@OneToMany(mappedBy = "retailer", cascade = CascadeType.ALL)
	private List<Order> orders;

	@OneToMany(mappedBy = "retailer", cascade = CascadeType.ALL)
	private List<Complaint> complaints;

	@OneToMany(mappedBy = "retailer", cascade = CascadeType.ALL)
	private List<Notification> notifications;

	public Long getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(Long retailerId) {
		this.retailerId = retailerId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(List<Complaint> complaints) {
		this.complaints = complaints;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }
	

}
