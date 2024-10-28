package com.revshop.rev.service;



import java.util.List;

import com.revshop.rev.entity.Admin;
import com.revshop.rev.entity.Buyer;
import com.revshop.rev.entity.Complaint;
import com.revshop.rev.entity.Retailer;

public interface AdminServiceInterface {
	
	int createAdmin(Admin admin); // service for creating an admin

	int login(Admin admin); // service for login an admin
	
	List<Buyer> viewAllBuyers();  // service for viewing all buyers
	
	List<Retailer> viewAllNewRetailersRequests();  // service for viewing all new retailer requests
	
	List<Complaint> viewAllComplaints();  // service for viewing all complaints
	
	List<Retailer> ApprovedRetailers(); //	service for viewing all approved retailers
	
	int deleteBuyer(Long buyerId); // service for deleting a buyer
	
	int deleteRetailerRequest(Long retailerId); // service for deleting a retailer request
	
	int approveRetailerRequest(Long retailerId); // service for approving a retailer request

	int blockRetailer(Long retailerId); // service for block a retailer
	
	int unblockRetailer(Long retailerId); // service for unblock a retailer
	
	int blockBuyer(Long buyerId); // service for block a buyer
	
	int unblockBuyer(Long buyerId); // service for unblock a buyer
	
	int deleteApprovedRetailer(Long retailerId); // service for deleting a approved retailer
	
	
}
