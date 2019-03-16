package com.ncuhome.tasklist.service.impl;

import com.ncuhome.tasklist.dataobject.EmailSend;
import com.ncuhome.tasklist.repository.EmailSendRepository;
import com.ncuhome.tasklist.repository.UserRepository;
import com.ncuhome.tasklist.service.EmailService;
import com.ncuhome.tasklist.util.MailUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.jca.context.SpringContextResourceAdapter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
@Log4j2
public class EmailServiceImpl implements EmailService {

    @Autowired
    EmailSendRepository emailSendRepository;

    @Autowired
    UserRepository userRepository;

    @Value("${spring.profiles.active}")
    private String profile;

    @Override
    public Boolean sendEmail(String address, String content) {
        return true;
    }

    @Override
    @Async
    public void sendVerifyCode(String address) throws MessagingException, UnsupportedEncodingException {
//        // 检查用户
//        User user = userRepository.findByEmail(address);
//        if(user != null){
//            throw new UserRegisterException("用户已存在");
//        }
        String vcode = generateVcode();

        log.info(profile);
        if(profile.equals("test")){
            vcode = "123456";
            log.info(vcode);
        }
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
