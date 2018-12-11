package com.ncuhome.tasklist.service.impl;

import com.ncuhome.tasklist.VO.LoginResult;
import com.ncuhome.tasklist.dataobject.EmailSend;
import com.ncuhome.tasklist.dataobject.User;
import com.ncuhome.tasklist.enums.LoginEnum;
import com.ncuhome.tasklist.exception.UserLoginException;
import com.ncuhome.tasklist.exception.UserRegisterException;
import com.ncuhome.tasklist.form.UserForm.ChangePasswordForm;
import com.ncuhome.tasklist.form.UserForm.LoginForm;
import com.ncuhome.tasklist.form.UserForm.RegisterForm;
import com.ncuhome.tasklist.repository.EmailSendRepository;
import com.ncuhome.tasklist.repository.UserRepository;
import com.ncuhome.tasklist.service.UserService;
import com.ncuhome.tasklist.util.MD5Util;
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

    @Value("${app.secret}")
    private String secret;

    @Override
    public LoginResult login(LoginForm loginForm) {
        User user = userRepository.findByEmail(loginForm.getEmail());

        // 用户是否存在
        if (user == null) throw new UserLoginException(LoginEnum.USER_NOT_FOUND);

        if(checkPassword(user, loginForm.getPassword()))
        return new LoginResult(generateToken(user));
        else throw new UserLoginException(LoginEnum.PWD_INCORRECT);
    }

    @Override
    public String changePassword(User user, ChangePasswordForm changePasswordForm) {

        if(checkPassword(user, changePasswordForm.getPwdBefore())){
            user.setPassword(changePasswordForm.getPwdNew());
            userRepository.save(user);
        }
        else{
            throw new UserLoginException(LoginEnum.PWD_BEFORE_INCORRECT);
        }
        return "修改成功";
    }

    @Override
    public String register(RegisterForm registerForm) {
        EmailSend emailSend = emailSendRepository.findByEmail(registerForm.getEmail());

        if(emailSend == null){
            throw new UserRegisterException("error submitted info");
        }

        if(!registerForm.getVerifyCode().equals(emailSend.getVerifyCode()))
            throw new UserRegisterException("验证码错误");

        User user = new User(registerForm.getEmail(), registerForm.getPassword());

        emailSendRepository.delete(emailSend);
        userRepository.save(user);

        return "注册成功";
    }

    public Boolean checkPassword(User user, String pwd){
        return md5Util.md5(pwd).equals(user.getPassword());
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
