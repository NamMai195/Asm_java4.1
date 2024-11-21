package com.poly.service;

import javax.servlet.ServletContext;

import com.poly.entity.User;

public interface EmailService {
 void sendMail(ServletContext context,User recipient,String type);
 void sendMail(ServletContext context, User recipient, String type, String newEmail, String token);
}
