package com.bbc.ubp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
	public class User {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long employeeID;
	    private String email;
	    private String otp;
	    private String phoneNumber;
		public User() {
			// TODO Auto-generated constructor stub
		}
		public User(long employeeID, String email, String otp, String phoneNumber) {
			super();
			this.employeeID = employeeID;
			this.email = email;
			this.otp = otp;
			this.phoneNumber = phoneNumber;
		}
		public long getEmployeeID() {
			return employeeID;
		}
		public void setEmployeeID(long employeeID) {
			this.employeeID = employeeID;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getOtp() {
			return otp;
		}
		public void setOtp(String otp) {
			this.otp = otp;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		
	    
	}


