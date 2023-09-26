package com.bbc.ubp.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbc.ubp.dao.UserDao;
import com.bbc.ubp.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public boolean authenticateUser(long employeeID, String otp) {
		
		return userDao.authenticateUser(employeeID,otp);
	}

	public void addUser(User newUser) {
        userDao.addUser(newUser);
    }

    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void updateUser(User updatedUser) {
        userDao.updateUser(updatedUser);
    }

    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

	public void saveOTPOfUser(long employeeID,String generatedOtp) {

		userDao.saveOTPOfUser(employeeID,generatedOtp);
	}
	
	

}
