package com.revshop.rev.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;

import com.revshop.rev.entity.Buyer; 
import com.revshop.rev.entity.Cart;
import com.revshop.rev.entity.CartItem;
import com.revshop.rev.entity.Category;
import com.revshop.rev.entity.Product;

@Repository
public class BuyerDAO implements BuyerDAOInterface {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int updateEmailDAO(String email, String newEmail) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Buyer buyer = session.createQuery("FROM Buyer WHERE email = :email", Buyer.class)
                                 .setParameter("email", email)
                                 .uniqueResult();

            if (buyer != null) {
                buyer.setEmail(newEmail);
                session.update(buyer);
                transaction.commit();
                return 1;
            } else {
                return 0;
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updatePassword(String email, String newPassword) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Buyer buyer = session.createQuery("FROM Buyer WHERE email = :email", Buyer.class)
                                 .setParameter("email", email)
                                 .uniqueResult();

            if (buyer != null) {
                buyer.setPassword(newPassword);
                session.update(buyer);
                transaction.commit();
                return 1;
            } else {
                return 0;
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public String createProfile(Buyer buyer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(buyer);
            transaction.commit();
            return "success";
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return "failure";
        } finally {
            session.close();
        }
    }

    @Override
    public String LoginProfile(Buyer buyer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        String res = "Fail";
        try {
            transaction = session.beginTransaction();
            Query<Buyer> query = session.createQuery("from com.revshop.rev.entity.Buyer b where b.email = :Email and b.password = :Password");
            query.setParameter("Email", buyer.getEmail());
            query.setParameter("Password", buyer.getPassword());
            Buyer result = query.uniqueResult();
            if (result != null) {
                res = "success";
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return res;
    }

    @Override
    public List<Product> getAllProducts() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Product> products = null;
        try {
            transaction = session.beginTransaction();
            Query<Product> query = session.createQuery("from com.revshop.rev.entity.Product");
            products = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return products;
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Product> products = null;
        try {
            transaction = session.beginTransaction();
            Query<Product> query = session.createQuery("from com.revshop.rev.entity p WHERE lower(p.productName) LIKE :keyword OR lower(p.description) LIKE :keyword");
            query.setParameter("keyword", "%" + keyword.toLowerCase() + "%");
            products = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return products;
    }

    @Override
    public List<Product> getProductByCategory(int categoryId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Product> products = null;
        try {
            transaction = session.beginTransaction();
            Query<Product> query = session.createQuery("from com.revshop.rev.entity p WHERE p.category.categoryId = :categoryId");
            query.setParameter("categoryId", categoryId);
            products = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return products;
    }

    @Override
    public List<Category> getAllCategory() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Category> categories = null;
        try {
            transaction = session.beginTransaction();
            Query<Category> query = session.createQuery("select c.categoryName from com.revshop.rev.entity.Category c");
            categories = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return categories;
    }

    @Override
    public int getCategoryIdByName(Category c) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Integer categoryId = null;
        try {
            transaction = session.beginTransaction();
            Query<Integer> query = session.createQuery("select c.categoryId from com.revshop.rev.entity.Category c where c.categoryName = :categoryName");
            query.setParameter("categoryName", c.getCategoryName());
            categoryId = query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return categoryId;
    }

	@Override
	public Product getProductById(long productId) {
		  Session session = sessionFactory.openSession();
		    Transaction transaction = null;
		    Product product = null;
		    try {
		        transaction = session.beginTransaction();
		        Query<Product> query = session.createQuery("from com.revshop.rev.entity.Product p where p.productId = :productId");
		        query.setParameter("productId", productId);
		        product = query.getSingleResult();
		        transaction.commit();
		    } catch (Exception e) {
		        if (transaction != null) {
		            transaction.rollback();
		        }
		        e.printStackTrace();
		    } finally {
		        session.close();
		    }
		    return product;
	}

	@Override
	public Buyer getBuyerByEmail(String email) {
		Session session = sessionFactory.openSession();
	    Transaction transaction = null;
	    Buyer buyer = null;
	    try {
	        transaction = session.beginTransaction();
	        Query<Buyer> query = session.createQuery("from com.revshop.rev.entity.Buyer b where b.email = :email");
	        query.setParameter("email", email);
	        buyer = query.getSingleResult();
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	    return buyer;
	}

	@Override
	public Buyer getBuyerById(Long buyerId) {
		Session session = sessionFactory.openSession();
	    Transaction transaction = null;
	    Buyer buyer = null;
	    try {
	        transaction = session.beginTransaction();
	        Query<Buyer> query = session.createQuery("from com.revshop.rev.entity.Buyer b where b.buyerId = :buyerId");
	        query.setParameter("buyerId", buyerId);
	        buyer = query.getSingleResult();
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	    return buyer;
	}


	public void addToCart(Cart newCart) {
	    Session session = sessionFactory.openSession();
	    Transaction transaction = null;

	    try {
	        transaction = session.beginTransaction();

	        // Retrieve the existing cart for the buyer if it exists
	        Query<Cart> query = session.createQuery("from com.revshop.rev.entity.Cart c WHERE c.buyer = :buyer");
	        query.setParameter("buyer", newCart.getBuyer());
	        Cart existingCart = query.uniqueResult();

	        if (existingCart == null) {
	            // If no existing cart, create a new one
	            session.save(newCart);
	        } else {
	            // Update existing cart
	            for (CartItem newItem : newCart.getCartItems()) {
	                boolean itemFound = false;
	                for (CartItem existingItem : existingCart.getCartItems()) {
	                    if (existingItem.getProduct().getProductId().equals(newItem.getProduct().getProductId())) {
	                        // Item exists in the cart, update the quantity
	                        existingItem.setQuantity(existingItem.getQuantity() + newItem.getQuantity());
	                        session.saveOrUpdate(existingItem);
	                        itemFound = true;
	                        break;
	                    }
	                }
	                if (!itemFound) {
	                    // If item does not exist in the cart, add it
	                    newItem.setCart(existingCart);
	                    session.save(newItem);
	                }
	            }
	            session.update(existingCart);
	        }

	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	}

}
