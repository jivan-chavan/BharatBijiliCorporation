package com.bbc.ubp.service;

	import javax.mail.internet.MimeMessage;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.beans.factory.annotation.Value;
	import org.springframework.mail.javamail.JavaMailSender;
	import org.springframework.mail.javamail.MimeMessageHelper;
	import org.springframework.stereotype.Service;

import com.bbc.ubp.dao.EMailDao;
import com.bbc.ubp.utility.OTPGenerator;


	@Service
	public class EMailService {
		
		@Autowired
		private UserService userService;

		@Value("${spring.mail.username}")
		private String fromEmail;
		
		private JavaMailSender javaMailSender;
		private EMailDao eMailDao;

		@Autowired
		public EMailService(JavaMailSender javaMailSender, EMailDao eMailDao) {
			super();
			this.javaMailSender = javaMailSender;
			this.eMailDao = eMailDao;
		}


		public String sendMail(long employeeID, String to, String[] cc, String subject, String body) {
		    try {
		        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		        mimeMessage.setFrom(fromEmail);
		        
		        String generatedOtp=OTPGenerator.generateOTP();
		        
		        userService.saveOTPOfUser(employeeID, generatedOtp);
		        
		        
		        to = eMailDao.getToAddress(employeeID);
		        body = eMailDao.getBody(employeeID);
		        
		        if (to == null || body == null) {
		            return "Data not found";
		        }
		        
		        mimeMessageHelper.setTo(to);
		        mimeMessageHelper.setCc(to);
		        mimeMessageHelper.setSubject("OTP");
		        mimeMessageHelper.setText(body);
		        javaMailSender.send(mimeMessage);
		        return "Mail Send for Otp";
		    } catch (Exception e) {
		        throw new RuntimeException(e);
		    }
		}


	}