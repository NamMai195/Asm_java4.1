package com.poly.service;

import javax.servlet.ServletContext;

import com.poly.entity.User;
import com.poly.entity.Videos;

public interface EmailService {
    void sendMail(ServletContext context, User recipient, String type);
    void sendMail(ServletContext context, User recipient, String type, String newEmail, String token);
    void sendShareVideoEmail(ServletContext context, String recipientEmail, Videos video); // Thêm phương thức này
}