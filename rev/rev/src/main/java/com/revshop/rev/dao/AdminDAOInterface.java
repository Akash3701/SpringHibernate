package com.revshop.rev.dao;



import java.util.List;

import com.revshop.rev.entity.Admin;
import com.revshop.rev.entity.Buyer;
import com.revshop.rev.entity.Complaint;
import com.revshop.rev.entity.Retailer;


public interface AdminDAOInterface  {

	int createAdminDAO(Admin admin);

	int loginAdminDAO(Admin admin); //for login

	List<Buyer> viewAllBuyersDAO();  // to view all the buyers

	List<Complaint> viewAllComplaintsDAO(); //to view all the complaints

	List<Retailer> viewAllNewRetailersRequestsDAO(); //to view all the new retailer requests

	List<Retailer> viewApprovedRetailersDAO(); //to view all the approved retailer requests

	int deleteBuyerDAO(Long buyerId); //to delete a buyer

	int deleteRetailerRequestDAO(Long retailerId); //to delete a retailer request

	int approveRetailerRequestDAO(Long retailerId); //to approve a retailer request

	int blockRetailerDAO(Long retailerId); //to block a retailer

	int unblockRetailerDAO(Long retailerId); //to unblock a retailer

	int blockBuyerDAO(Long buyerId); //to block a buyer

	int unblockBuyerDAO(Long buyerId); //to unblock a buyer

	int deleteApprovedRetailerDAO(Long retailerId); //to delete an approved retailer





}
