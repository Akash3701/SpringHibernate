package com.revshop.rev.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revshop.rev.entity.Category;
import com.revshop.rev.entity.Order;
import com.revshop.rev.entity.Product;
import com.revshop.rev.entity.ProductReview;
import com.revshop.rev.entity.Retailer;


@Repository
@Transactional
public class RetailerDAO implements RetailerDAOInterface {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public int registerRetailerDAO(Retailer retailer) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.persist(retailer);
			transaction.commit();
			return 1; // Return 1 to indicate success
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return 0; // Return 0 to indicate failure
		} finally {
			session.close();
		}
	}

	@Override
	public int loginRetailerDAO(Retailer retailer) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		int result = 0;
		try {
			transaction = session.beginTransaction();
			Query<Retailer> query = session.createQuery(
					"from com.revshop.rev.entity.Retailer where email = :email and password = :password",
					Retailer.class);
			query.setParameter("email", retailer.getEmail());
			query.setParameter("password", retailer.getPassword());
			Retailer existingRetailer = query.uniqueResult();

			if (existingRetailer != null && !existingRetailer.getIsBlocked() && existingRetailer.isApproved()) {
				result = 1; // Success
			} else if (existingRetailer != null && existingRetailer.getIsBlocked()) {
				result = 2; // Blocked
			} else if (existingRetailer != null && !existingRetailer.isApproved()) {
				result = 3; // Not approved
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
		return result;
	}

	@Override
	public Retailer findRetailerByEmailDAO(String email) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Retailer retailer = null;
		try {
			transaction = session.beginTransaction();
			Query<Retailer> query = session
					.createQuery("FROM com.revshop.rev.entity.Retailer WHERE email = :email", Retailer.class);
			query.setParameter("email", email);
			retailer = query.uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return retailer;
	}

	@Override
	public boolean isEmailAlreadyUsedDAO(String email) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		boolean result = false;
		try {
			transaction = session.beginTransaction();
			Query<Long> query = session.createQuery(
					"SELECT COUNT(r) FROM com.revshop.rev.entity.Retailer r WHERE r.email = :email", Long.class);
			query.setParameter("email", email);
			Long count = query.getSingleResult();
			result = count > 0;
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public List<Order> findAllBuyersOrdersDAO(Long retailerId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<Order> orders = null;
		try {
			transaction = session.beginTransaction();
			Query<Order> query = session.createQuery(
					"SELECT o FROM com.revshop.rev.entity.Order o LEFT JOIN FETCH o.orderItems oi LEFT JOIN FETCH oi.product WHERE o.retailer.retailerId = :retailerId",
					Order.class);
			query.setParameter("retailerId", retailerId);
			orders = query.getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return orders;
	}

	@Override
	public void updateOrderStatusDAO(Long orderId, String status) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			Order order = session.get(Order.class, orderId);
			if (order != null) {
				order.setOrderStatus(status);
				session.merge(order);
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

	@Override
	public List<ProductReview> viewAllProductReviewsDAO(Long retailerId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<ProductReview> reviews = null;
		try {
			transaction = session.beginTransaction();
			Query<ProductReview> query = session.createQuery(
					"SELECT pr FROM com.revshop.rev.entity.ProductReview pr WHERE pr.retailer.retailerId = :retailerId",
					ProductReview.class);
			query.setParameter("retailerId", retailerId);
			reviews = query.getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return reviews;
	}

	@Override
	public void deleteReviewByIdDAO(Long reviewId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			ProductReview review = session.get(ProductReview.class, reviewId);
			if (review != null) {
				session.remove(review);
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

	@Override
	public Order findOrderByIdDAO(Long orderId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Order order = null;
		try {
			transaction = session.beginTransaction();
			order = session.get(Order.class, orderId);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return order;
	}

	@Override
	public List<Product> viewAllProductsByRetailerDAO(Long retailerId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<Product> products = null;
		try {
			transaction = session.beginTransaction();
			Query<Product> query = session.createQuery(
					"FROM com.revshop.rev.entity.Product p WHERE p.retailer.retailerId = :retailerId",
					Product.class);
			query.setParameter("retailerId", retailerId);
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
	public void deleteProductByIdDAO(Long productId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			Product product = session.get(Product.class, productId);
			if (product != null) {
				session.remove(product);
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

	@Override
	public void addProductDAO(Product product, Long retailerId, int categoryId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			Retailer retailer = session.get(Retailer.class, retailerId);
			if (retailer == null) {
				throw new IllegalArgumentException("Retailer not found with ID: " + retailerId);
			}

			Category category = session.get(Category.class, categoryId);
			if (category == null) {
				throw new IllegalArgumentException("Category not found with ID: " + categoryId);
			}

			product.setRetailer(retailer);
			product.setCategory(category);

			session.persist(product);

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

	@Override
	public void updateProductDAO(Product product, Long retailerId, int categoryId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			Product existingProduct = session.get(Product.class, product.getProductId());
			if (existingProduct == null) {
				throw new IllegalArgumentException("Product not found with ID: " + product.getProductId());
			}

			existingProduct.setProductName(product.getProductName());
			existingProduct.setPrice(product.getPrice());
			existingProduct.setDescription(product.getDescription());
			existingProduct.setStockQuantity(product.getStockQuantity());
			existingProduct.setImage(product.getImage());

			Retailer retailer = session.get(Retailer.class, retailerId);
			if (retailer == null) {
				throw new IllegalArgumentException("Retailer not found with ID: " + retailerId);
			}
			existingProduct.setRetailer(retailer);

			Category category = session.get(Category.class, categoryId);
			if (category == null) {
				throw new IllegalArgumentException("Category not found with ID: " + categoryId);
			}
			existingProduct.setCategory(category);

			session.merge(existingProduct);

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

	@Override
	public Category getCategoryByIdDAO(int categoryId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Category category = null;
		try {
			transaction = session.beginTransaction();
			category = session.get(Category.class, categoryId);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return category;
	}

	@Override
	public Product getProductById(Long productId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Product product = null;
		try {
			transaction = session.beginTransaction();
			product = session.get(Product.class, productId);
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
	public List<Category> getAllCategoriesDAO() {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<Category> categories = null;
		try {
			transaction = session.beginTransaction();
			Query<Category> query = session.createQuery("FROM com.revshop.rev.entity.Category", Category.class);
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
}