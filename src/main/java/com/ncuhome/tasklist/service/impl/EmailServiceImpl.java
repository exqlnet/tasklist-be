package com.ncuhome.tasklist.service.impl;

import com.ncuhome.tasklist.dataobject.EmailSend;
import com.ncuhome.tasklist.exception.UserRegisterException;
import com.ncuhome.tasklist.form.UserForm.RegisterForm;
import com.ncuhome.tasklist.repository.EmailSendRepository;
import com.ncuhome.tasklist.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    EmailSendRepository emailSendRepository;

    @Override
    public Boolean sendEmail(String address, String content) {
        return true;
    }

    @Override
    public Boolean sendVerifyCode(String address) {
        // 检查邮件格式并发送邮件
        // TODO
        // 将验证码存入数据库
        String verifyCode = "666666";
        EmailSend emailSend = new EmailSend(address, verifyCode);
        emailSendRepository.save(emailSend);
        return true;
    }
}
