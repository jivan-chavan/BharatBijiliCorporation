package com.bbc.ubp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbc.ubp.service.EMailService;



@RestController
@RequestMapping("/ubp/employee/email")
@CrossOrigin(origins = "http://localhost:4200")
public class EMailController {

	private EMailService emailService;

	@Autowired
	public EMailController(EMailService emailService) {
		super();
		this.emailService = emailService;
	}
	
	@GetMapping("send/{employeeid}")
	public String sendMail(@PathVariable("employeeid") String employeeID, String to, String[] cc,String subject, String body) {
		return emailService.sendMail(employeeID,to,cc,subject,body);
	}
	
	
}