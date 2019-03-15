package com.ncuhome.tasklist.service.impl;

import com.ncuhome.tasklist.VO.LoginResult;
import com.ncuhome.tasklist.dataobject.EmailSend;
import com.ncuhome.tasklist.dataobject.User;
import com.ncuhome.tasklist.enums.HttpEnum;
import com.ncuhome.tasklist.form.UserForm.ChangePasswordForm;
import com.ncuhome.tasklist.form.UserForm.LoginForm;
import com.ncuhome.tasklist.form.UserForm.RegisterForm;
import com.ncuhome.tasklist.repository.EmailSendRepository;
import com.ncuhome.tasklist.repository.UserRepository;
import com.ncuhome.tasklist.service.UserService;
import com.ncuhome.tasklist.util.MD5Util;
import com.ncuhome.tasklist.util.ResultVOUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MD5Util md5Util;

    @Autowired
    EmailSendRepository emailSendRepository;

    private ResultVOUtil resultUtil = new ResultVOUtil();

    @Value("${app.secret}")
    private String secret;


    @Override
    public void changePassword(User user, ChangePasswordForm changePasswordForm) {

        user.setPassword(md5Util.md5(changePasswordForm.getPwdNew()));
        userRepository.save(user);
    }

    @Override
    public User register(RegisterForm registerForm) {

        User user = new User(registerForm.getEmail(), registerForm.getPassword());

        userRepository.save(user);
        emailSendRepository.deleteByEmail(registerForm.getEmail());
        return user;
    }

    public String checkPassword(User user, String pwd){
        if(md5Util.md5(pwd).equals(user.getPassword())){
            return generateToken(user);
        }
        else{
            return null;
        }
    }

    public String generateToken(User user){
        Calendar expCalendar = Calendar.getInstance();
        expCalendar.add(Calendar.HOUR, 3600);
        return  Jwts.builder()
                .claim("id", user.getUserId())
                .claim("email", user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expCalendar.getTime())
                .signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.encode(secret)).compact();
    }

    // 验证token
    public User verifyToken(String token){
        try{
            Claims claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
            String email = claims.get("email", String.class);
            User user = userRepository.findByEmail(email);
            if (user == null){
                return null;
            }
            return user;
        }catch(Exception e){
            return null;
        }
    }

    // 获取验证码
    @Override
    public String getVerifyCode(String email){
        EmailSend emailSend = emailSendRepository.findByEmail(email);
        if (emailSend == null){
            return null;
        }
        else{
            String vcode = emailSend.getVerifyCode();
            emailSendRepository.delete(emailSend);
            return vcode;
        }
    }

    // 根据用户和新密码修改密码
    @Override
    public Boolean changePwd(User user, String newPwd){
        user.setPassword(md5Util.md5(newPwd));
        return true;
    }
}
