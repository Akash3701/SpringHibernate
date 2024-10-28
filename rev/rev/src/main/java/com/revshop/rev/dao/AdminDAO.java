
package com.revshop.rev.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.revshop.rev.entity.Admin;
import com.revshop.rev.entity.Buyer;
import com.revshop.rev.entity.Complaint;
import com.revshop.rev.entity.Retailer;


@Repository
public class AdminDAO implements AdminDAOInterface {
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private EntityManager entityManager;

	// method to create an admin
	@Override
	public int createAdminDAO(Admin admin) {
		
		int result =0; 
	
		Session session = sessionFactory.openSession(); // creating session 
		
		session.beginTransaction(); // Starts a new transaction
		
		try {
			
			session.save(admin);  // Saves the admin entity to the database
			
			session.getTransaction().commit(); // Commits the transaction
			
		}catch(Exception e) {
			
			session.getTransaction().rollback();
		}
		
		return result;
	}

	// LogIn method to login an admin by checking credentials
	@Override
	public int loginAdminDAO(Admin admin) {
		
		int result =0; 
		
		Session session = sessionFactory.openSession(); // Opens a new session
		
		try {
			
			 Query<Admin> query = session.createNamedQuery("LoginAdmin", Admin.class);
		     query.setParameter("email", admin.getEmail()); // Sets the email parameter
		     query.setParameter("password", admin.getPassword()); // Sets the password parameter
		     
		     List<Admin> resultList = query.getResultList(); // Executes the query
		     
		     if (!resultList.isEmpty()) {
		            result = 1;  //  successful login
		        }
			
		} catch(Exception e) {
			
			e.printStackTrace();   // Prints stack trace in case of an error
		}
		
		return result;
	}

	
	
	 // Retrieves a list of all buyers from the database
	@Override
	public List<Buyer> viewAllBuyersDAO() {
	    try {
	    	javax.persistence.Query q= entityManager.createQuery("FROM com.revshop.rev.entity.Buyer b");
	    	List<Buyer> b= q.getResultList(); // Retrieves the result list
	    	System.out.println(b.size());   // Prints the size of the list
	        return b;
	    } catch (Exception e) {
	        e.printStackTrace();  	   // Prints the size of the list     
	        return null;         // Returns null if an error occurs
	    }
	}

	// Retrieves a list of all complaints from the database
	@Override
	public List<Complaint> viewAllComplaintsDAO() {
		try {
			
			javax.persistence.Query q = entityManager.createQuery("FROM com.revshop.rev.entity.Complaint c"); 
			List<Complaint> c = q.getResultList();  // Retrieves the result list
			System.out.println("Complaints"+ c.size()); // Prints the size of the list
			return c; 
		}catch(Exception e) {
			e.printStackTrace();  // Prints the size of the list 
		}
		return null;// Returns null if an error occurs
	}


	// Retrieves a list of all new retailers requests from the database
	@Override
	public List<Retailer> viewAllNewRetailersRequestsDAO() {
	    List<Retailer> resultList = null;
	    Session session = null;

	    try {
	        session = sessionFactory.openSession(); // Opens a new session
	        Query<Retailer> query = session.createNamedQuery("NewRetailerRequests", Retailer.class);
	        resultList = query.getResultList(); // Retrieves the result list
	    } catch (Exception e) {
	        e.printStackTrace(); // Prints stack trace in case of an error
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close(); // Closes the session
	        }
	    }

	    return resultList;
	}
	
	
	// Retrieves a list of all approved retailers from the database
	@Override
	public List<Retailer> viewApprovedRetailersDAO() {
		
		 List<Retailer> resultList = null; // Stores the result list
		    Session session = null;// Stores the session

		    try {
		        session = sessionFactory.openSession(); // Opens a new session
		        Query<Retailer> query = session.createNamedQuery("ApprovedRetailers", Retailer.class);// Creates a query
		        resultList = query.getResultList();// Retrieves the result list
		    } catch (Exception e) {
		        e.printStackTrace(); // You might want to log this exception or rethrow a custom exception
		    } finally {
		        if (session != null && session.isOpen()) {
		            session.close();// Closes the session
		        }
		    }

		    return resultList;// Returns the result list
	}



// method to delete a buyer from the database
	@Override
	public int deleteBuyerDAO(Long buyerId) {
	    int result = 0; // Stores the result
	    Session session = null; // Stores the session

	    try {
	        session = sessionFactory.openSession(); // Opens a new session
	        session.beginTransaction();//	Starts a transaction
	        
	      
	        Buyer buyer = session.get(Buyer.class, buyerId); // Retrieves the buyer
	        if (buyer != null) {  	// If the buyer exists
	            session.delete(buyer);// Deletes the buyer
	            session.getTransaction().commit();// Commits the transaction
	            result = 1;// Sets the result to 1
	        } else {
	            session.getTransaction().rollback();// Rolls back the transaction
	        }
	    } catch (Exception e) {
	        if (session != null) { // If the session is not null
	            session.getTransaction().rollback(); // Rolls back the transaction
	        }
	        e.printStackTrace();
	    } finally {
	        if (session != null && session.isOpen()) { // If the session is not null and is open
	            session.close(); // Closes the session
	        }
	    }
	    return result; // Returns the result
	}

//	method to block a retailer from the database
	@Override
	public int deleteRetailerRequestDAO(Long retailerId) {
	    int result = 0;// Stores the result
	    Session session = null; 	// Stores the session

	    try {
	        session = sessionFactory.openSession(); // Opens a new session
	        session.beginTransaction();//	Starts a transaction

	        // Load the retailer by retailerId and delete
	        Retailer retailer = session.get(Retailer.class, retailerId);
	        if (retailer != null) {// If the retailer exists
	            session.delete(retailer);// Deletes the retailer
	            session.getTransaction().commit();// Commits the transaction	
	            result = 1;// Sets the result to 1
	        } else {
	            session.getTransaction().rollback();// Rolls back the transaction
	        }
	    } catch (Exception e) {
	        if (session != null) { // If the session is not null
	            session.getTransaction().rollback();// 	Rolls back the transaction
	        }
	        e.printStackTrace();
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close(); // Closes the session
	        }
	    }
	    return result;
	}

	
	// method to approve a retailer from the database
	@Override
	public int approveRetailerRequestDAO(Long retailerId) {
	    int result = 0; // Stores the result
	    Session session = null;

	    try {
	        session = sessionFactory.openSession();
	        session.beginTransaction();//	

	        Retailer retailer = session.get(Retailer.class, retailerId); // Retrieves the retailer
	        if (retailer != null) {
	            retailer.setApproved(true); // Approves the retailer
	            session.update(retailer);// Updates the retailer
	            session.getTransaction().commit();// Commits the transaction
	            result = 1;// Sets the result to 1
	        } else {
	            session.getTransaction().rollback();// Rolls back the transaction
	        }
	    } catch (Exception e) {
	        if (session != null) {
	            session.getTransaction().rollback();// Rolls back the transaction
	        }
	        e.printStackTrace();
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close();// Closes the session
	        }
	    }
	    return result; //
	}

	
	//
	@Override
	public int blockRetailerDAO(Long retailerId) {
	    int result = 0;// Stores the result
	    Session session = null; // Stores the session

	    try {
	        session = sessionFactory.openSession();
	        session.beginTransaction();//	Starts a transaction

	        // Load the retailer by retailerId and block
	        Retailer retailer = session.get(Retailer.class, retailerId);
	        if (retailer != null) { // If the retailer exists
	            retailer.setIsBlocked(true);  // Blocks the retailer
	            session.update(retailer); //	Updates the retailer
	            session.getTransaction().commit();  // Commits the transaction
	            result = 1; // Sets the result to 1
	        } else {
	            session.getTransaction().rollback();// Rolls back the transaction
	        }
	    } catch (Exception e) {
	        if (session != null) {
	            session.getTransaction().rollback();//	
	        }
	        e.printStackTrace();
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close();// Closes the session
	        }
	    }
	    return result;	// Returns the result
	}
	
	//	
	@Override
	public int unblockRetailerDAO(Long retailerId) {
	    int result = 0;
	    Session session = null;

	    try {
	        session = sessionFactory.openSession();
	        session.beginTransaction();

	        Retailer retailer = session.get(Retailer.class, retailerId);
	        if (retailer != null) {
	            retailer.setIsBlocked(false);
	            session.update(retailer);
	            session.getTransaction().commit();
	            result = 1;
	        } else {
	            session.getTransaction().rollback();
	        }
	    } catch (Exception e) {
	        if (session != null) {
	            session.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }
	    return result; // Returns the result
	}

	
	// method to block a buyer from the database
	@Override
	public int blockBuyerDAO(Long buyerId) {
	    int result = 0;
	    Session session = null;

	    try {
	        session = sessionFactory.openSession();
	        session.beginTransaction();

	        // Load the retailer by retailerId and block
	        Buyer buyer = session.get(Buyer.class, buyerId);
	        if (buyer != null) {
	        	buyer.setIsBlocked(true);
	            session.update(buyer);
	            session.getTransaction().commit();
	            result = 1;
	        } else {
	            session.getTransaction().rollback();
	        }
	    } catch (Exception e) {
	        if (session != null) {
	            session.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }
	    return result; // Returns the result
	}
	
	// method to unblock a buyer from the database
	@Override
	public int unblockBuyerDAO(Long buyerId) {
	    int result = 0;
	    Session session = null;

	    try {
	        session = sessionFactory.openSession();
	        session.beginTransaction();

	        // Load the retailer by retailerId and unblock
	        Buyer buyer = session.get(Buyer.class, buyerId);
	        if (buyer != null) {
	            buyer.setIsBlocked(false);
	            session.update(buyer);
	            session.getTransaction().commit();
	            result = 1;
	        } else {
	            session.getTransaction().rollback();
	        }
	    } catch (Exception e) {
	        if (session != null) {
	            session.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }
	    return result;
	}


	// method to delete a retailer from the database

	@Override
	public int deleteApprovedRetailerDAO(Long retailerId) {
		  int result = 0;
		    Session session = null;

		    try {
		        session = sessionFactory.openSession();
		        session.beginTransaction();

		        // Load the retailer by retailerId and delete
		        Retailer retailer = session.get(Retailer.class, retailerId);
		        if (retailer != null) {
		            session.delete(retailer);
		            session.getTransaction().commit();
		            result = 1;
		        } else {
		            session.getTransaction().rollback();
		        }
		    } catch (Exception e) {
		        if (session != null) {
		            session.getTransaction().rollback();
		        }
		        e.printStackTrace();
		    } finally {
		        if (session != null && session.isOpen()) {
		            session.close();
		        }
		    }
		    return result;
		}
	}
