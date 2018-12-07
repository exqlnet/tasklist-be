package com.ncuhome.tasklist.service;


import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    public Boolean sendEmail(String address, String content);

    public Boolean sendVerifyCode(String address, String verifyCode);

}
