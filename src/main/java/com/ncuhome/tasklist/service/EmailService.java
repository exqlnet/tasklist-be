package com.ncuhome.tasklist.service;


import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailService {

    public Boolean sendEmail(String address, String content);

    public void sendVerifyCode(String address)throws MessagingException, UnsupportedEncodingException;
}
