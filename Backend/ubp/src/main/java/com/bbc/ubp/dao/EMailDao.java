package com.bbc.ubp.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bbc.ubp.entity.User;


@Repository
public class EMailDao {

	private SessionFactory sessionFactory;
	@Autowired
	public EMailDao(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	public String getToAddress(long employeeID) {
		try(Session session = sessionFactory.openSession()){
			 User user=session.get(User.class, employeeID);
			 if (user != null) {
	            	return user.getEmail();
	            }else {
	            	return null;
	            }
		}
	}

	public String getBody(long employeeID) {
		try(Session session = sessionFactory.openSession()){
			 
			 User user=session.get(User.class, employeeID);
			 if (user != null) {
	            	return user.getOtp();
	            }else {
	            	return null;
	            }
		}
}
}