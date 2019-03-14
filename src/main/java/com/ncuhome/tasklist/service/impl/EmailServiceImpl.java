package com.ncuhome.tasklist.service.impl;

import com.ncuhome.tasklist.dataobject.EmailSend;
import com.ncuhome.tasklist.repository.EmailSendRepository;
import com.ncuhome.tasklist.repository.UserRepository;
import com.ncuhome.tasklist.service.EmailService;
import com.ncuhome.tasklist.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    EmailSendRepository emailSendRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Boolean sendEmail(String address, String content) {
        return true;
    }

    @Override
    public Boolean sendVerifyCode(String address) throws MessagingException, UnsupportedEncodingException {
//        // 检查用户
//        User user = userRepository.findByEmail(address);
//        if(user != null){
//            throw new UserRegisterException("用户已存在");
//        }
        String vcode = generateVcode();
        // 检查邮件格式并发送邮件
        MailUtil.sendMail(address, "验证码 - 时现", vcode);
        // 将验证码存入数据库
        EmailSend check = emailSendRepository.findByEmail(address);
        if (check != null){
            check.setVerifyCode(vcode);
            emailSendRepository.save(check);
        }
        else{
            EmailSend emailSend = new EmailSend(address, vcode);
            emailSendRepository.save(emailSend);
        }
        return true;
    }

    public static String generateVcode(){
        String vcode = "";
        for(int i = 0; i < 6; i++){
            String e = String.valueOf((int)Math.floor(Math.random()*10));
            vcode = vcode + e;
        }
        return vcode;
    }
}
