package com.poly.service.impl;

import javax.security.auth.Subject;
import javax.servlet.ServletContext;

import com.poly.entity.User;
import com.poly.entity.Videos;
import com.poly.service.EmailService;
import com.poly.util.EmailUtil;
import com.poly.util.TokenGenerator;

public class EmailServiceImpl implements EmailService {
	private static final String Email_Welcome_Subject = "Wekcome to Online Entertaiment";
	private static final String Email_Forgot_Password = "Online Entertaiment: New Password";
	private static final String Email_EDIT_USER = "Online Entertaiment: EDIT USER";
	
	@Override
	public void sendMail(ServletContext context, User recipient, String type) {
//		  ServletContext context1 = context.getInitParameter();
		String host = context.getInitParameter("host");
		String port = context.getInitParameter("port");
		String user = context.getInitParameter("user");
		String pass = context.getInitParameter("pass");
		try {
			String content = null;
			String subject = null;
			switch (type) {
			case "welcome": {
				subject = Email_Welcome_Subject;
				content = "Dear " + recipient.getUsername() + ",hope you have a good time";

				break;
			}
			case "forgot": {
				subject = Email_Forgot_Password;
				content = "Dear " + recipient.getUsername() + ",hope you have a good time" + ", You password is here: "
						+ recipient.getPassword()+" You username is:"+recipient.getId();
				break;
			}
//			case "update": {
//				  subject = Email_Forgot_Password; 
//				  content = "Dear " + recipient.getUsername() + ",\n\n" 
//				          + "Hope you have a good time.\n\n"
//				          + "Your Token: " + TokenGenerator.generateToken(10); // Sử dụng TokenGenerator.generateToken(10)
//				  break;
//				}
			default: {
				subject = "online Entertaiment";
				content = "Hello How are you??";
				break;
			}

			}
			EmailUtil.sendEmail(host,port,user,pass,recipient.getEmail(), subject, content);
		} catch (Exception e) {
			System.err.println("Lỗi khi gửi email: " + e.getMessage());
			e.printStackTrace();
		}
		

	}
	 @Override
	    public void sendShareVideoEmail(ServletContext context, String recipientEmail, Videos video) {
	        String host = context.getInitParameter("host");
	        String port = context.getInitParameter("port");
	        String user = context.getInitParameter("user");
	        String pass = context.getInitParameter("pass");

	        try {
	            String subject = "Chia sẻ video từ Online Entertainment";
	            String content = "Dear " + recipientEmail + ",\n\n"
	                    + "Bạn được chia sẻ video:\n\n"
	                    + video.getTitle() + "\n\n"
	                    + "Xem video tại đây:\n\n"
	                    + "https://www.youtube.com/watch/" + video.getHref() + "\n\n" 
	                    + "Trân trọng,\n"
	                    + "Your Website";

	            EmailUtil.sendEmail(host, port, user, pass, recipientEmail, subject, content);
	        } catch (Exception e) {
	            System.err.println("Lỗi khi gửi email chia sẻ video: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
	@Override
	public void sendMail(ServletContext context, User recipient, String type, String newEmail, String token) {
	    // ... (Lấy thông tin host, port, user, pass từ context)
		String host = context.getInitParameter("host");
		String port = context.getInitParameter("port");
		String user = context.getInitParameter("user");
		String pass = context.getInitParameter("pass");
	    try {
	        String content = null;
	        String subject = null;
	        switch (type) {
	            // ... (Các case khác)
	            case "update": {
	                subject = Email_EDIT_USER;
	                content = "Dear " + recipient.getUsername() + ",\n\n"
	                        + "Bạn đã yêu cầu thay đổi địa chỉ email cho tài khoản của mình thành " + newEmail + ".\n\n"
	                        + "Để xác nhận thay đổi, vui lòng nhập mã token sau đây vào trang web:\n\n"
	                        + token + "\n\n" // Hiển thị token để người dùng nhập
	                        + "Nếu bạn không yêu cầu thay đổi này, vui lòng bỏ qua email này.\n\n"
	                        + "Trân trọng,\n"
	                        + "Your Website";
	                EmailUtil.sendEmail(host, port, user, pass, newEmail, subject, content); // Gửi đến email mới
	                break;
	            }
	            case "changepass": {
	                subject = Email_EDIT_USER;
	                content = "Dear " + recipient.getUsername() + ",\n\n"
	                        + "Bạn đã yêu cầu thay đổi password cho tài khoản của mình thành " + newEmail + ".\n\n"
	                        + "Để xác nhận thay đổi, vui lòng nhập mã token sau đây vào trang web:\n\n"
	                        + token + "\n\n" // Hiển thị token để người dùng nhập
	                        + "Nếu bạn không yêu cầu thay đổi này, vui lòng bỏ qua email này.\n\n"
	                        + "Trân trọng,\n"
	                        + "Your Website";
	                EmailUtil.sendEmail(host, port, user, pass, recipient.getEmail(), subject, content); // Gửi đến email mới
	                break;
	            }
	            // ... (Các case khác)
	        }
	        
	    } catch (Exception e) {
	        // ... (Xử lý lỗi)
	    }
	}

}
