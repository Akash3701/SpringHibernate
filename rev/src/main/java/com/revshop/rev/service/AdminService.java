package com.revshop.rev.service;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revshop.rev.dao.AdminDAOInterface;
import com.revshop.rev.entity.Admin;
import com.revshop.rev.entity.Buyer;
import com.revshop.rev.entity.Complaint;
import com.revshop.rev.entity.Retailer;

@Service
public class AdminService implements AdminServiceInterface {
	
	
	
	@Autowired
	public AdminDAOInterface adminDAO; //we are injecting the dao interface here.

	//we are implementing the methods from the interface.
	
	@Override
	public int createAdmin(Admin admin) { 
		
		return adminDAO.createAdminDAO(admin); // calling the method createAdminDAO from the dao interface.
	}

// this method is used to login.
	@Override
	public int login(Admin admin) {
		
		return adminDAO.loginAdminDAO(admin); // calling the method  from the dao interface.
	}

// this method is used to view all the buyers.
	@Override
	public List<Buyer> viewAllBuyers() {
		
		return adminDAO.viewAllBuyersDAO(); // returning the list of buyers from the dao interface.
	}
	
	
// this method is used to view all the new retailer requests.
	@Override
	public List<Retailer> viewAllNewRetailersRequests() {
		
		return adminDAO.viewAllNewRetailersRequestsDAO(); // returning the list of retailers from the dao interface.
	}

	
// 	this method is used to view all the complaints.
	@Override
	public List<Complaint> viewAllComplaints() {
		
		return adminDAO.viewAllComplaintsDAO(); // returning the list of complaints from the dao interface.
	}

// this method is used to view all the approved retailers.
	@Override
	public List<Retailer> ApprovedRetailers() {
		
		return adminDAO.viewApprovedRetailersDAO(); // returning the list of approved retailers from the dao interface.
	}
	
	
//  this method is used to delete the buyer.			
	@Override
	public int deleteBuyer(Long buyerId) {
	    return adminDAO.deleteBuyerDAO(buyerId); // returning the delete buyer method from the dao interface.
	}

// this method is used to delete the retailer request.
	@Override
	public int deleteRetailerRequest(Long retailerId) {
	    return adminDAO.deleteRetailerRequestDAO(retailerId); // returning the delete retailer method from the dao interface.
	}

// this method is used to approve the retailer
	@Override
	public int approveRetailerRequest(Long retailerId) {
	    return adminDAO.approveRetailerRequestDAO(retailerId); // returning the approve retailer method from the dao interface.
	}
	
	
// this method is used to block the retailer
	@Override
	public int blockRetailer(Long retailerId) {
	    return adminDAO.blockRetailerDAO(retailerId);  	// returning the block retailer method from the dao interface.
	}

	// this method is used to unblock the retailer
	@Override
	public int unblockRetailer(Long retailerId) {
	    return adminDAO.unblockRetailerDAO(retailerId); // returning the unblock retailer method from the dao interface.
	}

	
	//
	@Override
	public int blockBuyer(Long buyerId) {
	    return adminDAO.blockBuyerDAO(buyerId); // returning the block buyer method from the dao interface.
	}
	
	//
	@Override
	public int unblockBuyer(Long buyerId) {
	    return adminDAO.unblockBuyerDAO(buyerId); // returning the unblock buyer method from the dao interface.
	}
	
	// this method is used to delete the approved retailer
	@Override
	public int deleteApprovedRetailer(Long retailerId) { 
		
	    return adminDAO.deleteApprovedRetailerDAO(retailerId); 	// returning the delete approved retailer method from the dao interface.
	}


}
