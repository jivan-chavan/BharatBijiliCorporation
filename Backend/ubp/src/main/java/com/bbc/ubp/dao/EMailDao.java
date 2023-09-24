package com.bbc.ubp.dao;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

	public String getToAddress(String employeeID) {
		try(Session session = sessionFactory.openSession()){
			Criteria criteria=session.createCriteria(User.class);
			 criteria.add(Restrictions.eq("employeeID", employeeID));
			 User user=(User) criteria.uniqueResult();
			 if (user != null) {
	            	return user.getEmail();
	            }else {
	            	return null;
	            }
		}
	}

	public String getBody(String employeeID) {
		try(Session session = sessionFactory.openSession()){
			 Criteria criteria=session.createCriteria(User.class);
			 criteria.add(Restrictions.eq("employeeID", employeeID));
			 User user=(User) criteria.uniqueResult();
			 if (user != null) {
	            	return user.getOtp();
	            }else {
	            	return null;
	            }
		}
}
}