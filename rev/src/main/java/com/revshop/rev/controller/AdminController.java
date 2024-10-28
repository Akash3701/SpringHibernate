package com.revshop.rev.controller;



import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.revshop.rev.entity.Admin;
import com.revshop.rev.entity.Buyer;
import com.revshop.rev.entity.Retailer;
import com.revshop.rev.entity.Complaint;
import com.revshop.rev.service.AdminServiceInterface;



@Controller
public class AdminController {
	
	
	@Autowired
	private AdminServiceInterface adminService; // injecting the service layer
	
	
    @RequestMapping("admin/register") //request mapping for registering a new admin
    public ModelAndView createAdmin(@RequestParam("email") String email, @RequestParam("password") String password) { //request parameters for email and password
    	
        Admin admin = new Admin(); //creating an admin object
        admin.setEmail(email); //setting email
        admin.setPassword(password); //setting password

        int result = adminService.createAdmin(admin); 	// calling the method createAdmin from the service layer
        String message = (result > 0) ? "Registration Successful" : "Registration Failed"; 	// checking if the result is greater than 0

        ModelAndView mv = new ModelAndView(); 		// creating a model and view object
        mv.addObject("registrationResult", message); 	// adding the message to the model
        mv.setViewName("admin/login.jsp"); 			// setting the view name
        return mv;
    }
    
    
    @RequestMapping("admin/adminlogin")
    public ModelAndView loginAdmin(HttpServletRequest request, @RequestParam("email") String email, @RequestParam("password") String password) { //request parameters for email and password
        Admin admin = new Admin(); //creating an admin object
        admin.setEmail(email);//setting email
        admin.setPassword(password); //	setting password

        int result = adminService.login(admin); // calling the method login from the service layer
        
        String message = (result > 0) ? "Welcome " + email : "Login Failed"; // checking if the result is greater than 0
        
        ModelAndView mv = new ModelAndView(); // creating a model

        if (result > 0) { 		// checking if the result is greater than 0
            HttpSession session = request.getSession(true); // creating a session
            session.setAttribute("adminEmail", email);	// setting the email in the session
            mv.setViewName("redirect:/admin/adminDashboard.jsp"); 	// setting the view name
        }else { 			// if the result is not greater than 0
        	
            mv.addObject("loginResult", message); 	// adding the message to the model
            mv.setViewName("redirect:/admin/adminLogin.jsp");  // setting the view name
        }
        return mv; // returning the model
    }
    
    
    @RequestMapping("admin/adminlogout")
    public String logoutAdmin(HttpServletRequest request) { //httpserveletrequest request
        HttpSession session = request.getSession(false);  // creating a session
        if (session != null) { // checking if the session is not null
            session.invalidate(); // invalidating the session
        }
        return "redirect:adminLogin.jsp";  // redirecting to the login page
    }
    	
    
    //
    @RequestMapping("viewBuyers")
    public ModelAndView viewBuyers() {
        List<Buyer> buyers = adminService.viewAllBuyers(); // calling the method viewAllBuyers from the service layer
        System.out.println(buyers.size());	// printing the size of the list
        ModelAndView mv = new ModelAndView(); 	// creating a model and view object
        mv.addObject("buyersList", buyers);		// adding the list to the model
        mv.setViewName("admin/getUsersList.jsp");  	// setting the view name
        return mv;				// returning the model
    }
    
    // view Retailers	
    @RequestMapping("viewRetailers")
    public ModelAndView viewRetailers() {
    	
    	List<Retailer> retailers = adminService.viewAllNewRetailersRequests();  // calling the method viewAllNewRetailersRequests from the service layer
    	System.out.println(retailers.size()); // printing the size of the list
    	
    	ModelAndView mv = new ModelAndView();  // creating a model and view object
    	mv.addObject("retailersList", retailers); 	// adding the list to the model
    	mv.setViewName("admin/getRetailersList.jsp"); // setting the view name
    	return mv; // returning the model
    }
    
    

	// view Complaints
    @RequestMapping("viewComplaints")
    public ModelAndView viewComplaints() {
    	
    	List<Complaint> complaints = adminService.viewAllComplaints();  // calling the method viewAllComplaints from the service layer
    	System.out.println(complaints.size()); // printing the size of the list
    	
    	ModelAndView mv = new ModelAndView();  // creating a model and view object
    	mv.addObject("complaintsList", complaints); // adding the list to the model
    	mv.setViewName("admin/getComplaintsList.jsp");// setting the view name
    	return mv; // returning the model
    }
    
    
    
    // view Approved Retailers
    @RequestMapping("viewApprovedRetailers")
    public ModelAndView viewApprovedRetailers() {
    	
    	List<Retailer> retailers = adminService.ApprovedRetailers(); // calling the method ApprovedRetailers from the service layer
    	System.out.println(retailers.size()); // printing the size of the list
    	
    	ModelAndView mv = new ModelAndView();  // creating a model and view object
    	mv.addObject("retailersList", retailers); 	// adding the list to the model
    	mv.setViewName("admin/getApprovedRetailersList.jsp");	// setting the view name
    	return mv; 	// returning the model
    }
    

	// delete Buyer
    @RequestMapping("deleteBuyer")
    public String deleteBuyer(@RequestParam("buyerId") Long buyerId) { //taking the buyerId as a parameter
        int result = adminService.deleteBuyer(buyerId); // calling the method deleteBuyer from the service layer
        if (result > 0) { // checking if the result is greater than 0
            return "redirect:/viewBuyers"; // redirecting to the viewBuyers
        } else {
            return "error.jsp"; // returning the error.jsp
        }
    }
    
    
	// delete Retailer Request
    @RequestMapping("deleteRetailerRequest")
    public String deleteRetailerRequest(@RequestParam("retailerId") Long retailerId) { 	// taking the retailerId as a parameter
        int result = adminService.deleteRetailerRequest(retailerId); 	// calling the method deleteRetailerRequest from the service layer
        if (result > 0) { 	// checking if the result is greater than 0
            return "redirect:/viewRetailers"; 	// redirecting to the viewRetailers
        } else {
            return "error.jsp";     // returning the error.jsp  
		}
    }

    

	// approve Retailer Request
    @RequestMapping("approveRetailerRequest")
    public String approveRetailerRequest(@RequestParam("retailerId") Long retailerId) { 	// taking the retailerId as a parameter
        int result = adminService.approveRetailerRequest(retailerId); 	// calling the method approveRetailerRequest from the service layer
        if (result > 0) {	// checking if the result is greater than 0
            return "redirect:/viewRetailers";	// redirecting to the viewRetailers
        } else {
            return "error.jsp"; 	// returning the error.jsp
        }
    }
    
    
    // block Retailer
    @RequestMapping("blockRetailer")
    public String blockRetailer(@RequestParam("retailerId") Long retailerId) { 	// taking the retailerId as a parameter
        int result = adminService.blockRetailer(retailerId);	// calling the method blockRetailer from the service layer
        if (result > 0) {	// checking if the result is greater than 0
            return "redirect:/viewApprovedRetailers";	// redirecting to the viewApprovedRetailers
        } else {
            return "error.jsp"; 	// returning the error.jsp
        }
    }

    
 // unblock retailer
    @RequestMapping("unblockRetailer")
    public String unblockRetailer(@RequestParam("retailerId") Long retailerId) { // taking the retailerId as a parameter
        int result = adminService.unblockRetailer(retailerId); // calling the method unblockRetailer from the service layer
        if (result > 0) { // checking if the result is greater than 0
            return "redirect:/viewApprovedRetailers";// redirecting to the viewApprovedRetailers
        } else {
            return "error.jsp"; // returning the error.jsp
        }
    }
    
    
    // block buyer
    @RequestMapping("blockBuyer")
    public String blockBuyer(@RequestParam("buyerId") Long buyerId) { // taking the buyerId as a parameter
        int result = adminService.blockBuyer(buyerId); // calling the method blockBuyer from the service layer
        if (result > 0) { // checking if the result is greater than 0
            return "redirect:/viewBuyers"; // redirecting to the viewBuyers
        } else {
            return "error.jsp"; 	// returning the error.jsp
        }
    }

    // unblock buyer
    @RequestMapping("unblockBuyer")
    public String unblockBuyer(@RequestParam("buyerId") Long buyerId) { // taking the buyerId as a parameter
        int result = adminService.unblockBuyer(buyerId); // calling the method unblockBuyer from the service layer
        if (result > 0) { // checking if the result is greater than 0
            return "redirect:/viewBuyers";  // redirecting to the viewBuyers
        } else {
            return "error.jsp"; // returning the error.jsp
        }
    }


    // delete approved retailer
    @RequestMapping("deleteApprovedRetailer")
    public String deleteApprovedRetailer(@RequestParam("retailerId") Long retailerId) { // taking the retailerId as a parameter
        int result = adminService.deleteApprovedRetailer(retailerId); // calling the method deleteApprovedRetailer from the service layer
        if (result > 0) { // checking if the result is greater than 0
            return "redirect:/viewApprovedRetailers";  // redirecting to the viewApprovedRetailers
        } else {
            return "error.jsp";  // returning the error.jsp
        }
    }

}
