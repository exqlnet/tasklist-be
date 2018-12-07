package com.ncuhome.tasklist.service.impl;

import com.ncuhome.tasklist.service.EmailService;

public class EmailServiceImpl implements EmailService {
    @Override
    public Boolean sendEmail(String address, String content) {
        return null;
    }

    @Override
    public Boolean sendVerifyCode(String address, String verifyCode) {

        return true;
    }
}
