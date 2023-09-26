package com.bbc.ubp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbc.ubp.entity.User;
import com.bbc.ubp.service.UserService;



@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	    @Autowired
	    private UserService userService; 
	    

	    @PostMapping("/login")
	    public String login(@RequestBody User user) {
	      	    	
	        if (userService.authenticateUser(user.getEmployeeID(), user.getOtp())) {
	            return "Login Successful.";
	        } else {
	            return "Login Failed.";
	        }
	    }
	    @PostMapping("/add")
	    public void addUser(@RequestBody User newUser) {
	        userService.addUser(newUser);
	    }

	    @GetMapping("/{id}")
	    public User getUser(@PathVariable("id") long id) {
	        return userService.getUserById(id);
	    }

	    @GetMapping("/all")
	    public List<User> getAllUsers() {
	        return userService.getAllUsers();
	    }

	    @PutMapping("/update")
	    public void updateUser(@RequestBody User updatedUser) {
	        userService.updateUser(updatedUser);
	    }

	    @DeleteMapping("/delete/{id}")
	    public void deleteUser(@PathVariable("id") long id) {
	        userService.deleteUser(id);
	    }


	}