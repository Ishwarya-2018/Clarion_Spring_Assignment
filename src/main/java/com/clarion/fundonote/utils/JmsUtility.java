package com.clarion.fundonote.utils;
//package com.bridgelabz.fundonote.utils;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class JmsUtility {
//
//	@Autowired
//	private JavaMailSender sender;
//
//	public void sendMail(String emailTo, String token, String url) {
//		try {
//			MimeMessage message = sender.createMimeMessage();
//			MimeMessageHelper helper = new MimeMessageHelper(message);
//			helper.setFrom("ishwarya.p@clariontechnologies.co.in");
//			helper.setTo(emailTo);
//			helper.setText(url + token);
//			helper.setSubject("Verify Registration registration!");
//			sender.send(message);
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
//	}
//
//}
