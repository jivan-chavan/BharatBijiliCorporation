package com.bbc.ubp.dao;



import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bbc.ubp.entity.User;

@Repository
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean authenticateUser(String employeeID, String otp) {
		Session session=sessionFactory.openSession();
		Criteria criteria=session.createCriteria(User.class);
		criteria.add(Restrictions.eq("employeeID",employeeID));
		User user = (User) criteria.uniqueResult();
	    
	    if (user != null) {
	        if (user.getOtp().equals(otp)) {
	            session.close(); 
	            return true;
	        } else {
	            session.close(); 
	            return false;
	        }
	    } else {
	        session.close(); 
	        return false;
	    }
	}
	
	    public void addUser(User newUser) {
	        Session session = sessionFactory.openSession();
	        session.beginTransaction();
	        session.save(newUser);
	        session.getTransaction().commit();
	        session.close();
	    }

	    public User getUserById(long id) {
	        Session session = sessionFactory.openSession();
	        User user = session.get(User.class, id);
	        session.close();
	        return user;
	    }

	    public List<User> getAllUsers() {
	        Session session = sessionFactory.openSession();
	        Criteria criteria = session.createCriteria(User.class);
	        List<User> users = criteria.list();
	        session.close();
	        return users;
	    }

	    public void updateUser(User updatedUser) {
	        Session session = sessionFactory.openSession();
	        session.beginTransaction();
	        session.update(updatedUser);
	        session.getTransaction().commit();
	        session.close();
	    }

	    public void deleteUser(long id) {
	        Session session = sessionFactory.openSession();
	        session.beginTransaction();
	        User user = session.get(User.class, id);
	        if (user != null) {
	            session.delete(user);
	        }
	        session.getTransaction().commit();
	        session.close();
	    }

		public void saveOTPOfUser(String employeeID, String generatedOtp) {

			 Session session = sessionFactory.openSession();
		        session.beginTransaction();
		        Criteria criteria=session.createCriteria(User.class);
				criteria.add(Restrictions.eq("employeeID",employeeID));
				User user = (User) criteria.uniqueResult();		      
				user.setOtp(generatedOtp);
		        session.update(user);
		        session.getTransaction().commit();
		        session.close();
		}
	}


