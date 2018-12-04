package com.ncuhome.tasklist.service.impl;

import com.ncuhome.tasklist.VO.LoginResult;
import com.ncuhome.tasklist.VO.ResultVO;
import com.ncuhome.tasklist.dataobject.User;
import com.ncuhome.tasklist.enums.LoginEnum;
import com.ncuhome.tasklist.exception.UserLoginException;
import com.ncuhome.tasklist.form.LoginForm;
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

    @Value("${app.secret}")
    private String secret;

    @Override
    public LoginResult checkPassword(LoginForm loginForm) {
        User user = userRepository.findByEmail(loginForm.getEmail());
        log.info("{}", loginForm);
        log.info("{}", user);

        if(user == null){
            throw new UserLoginException(LoginEnum.USER_NOT_FOUND);
        }
        if(!md5Util.md5(loginForm.getPassword()).equals(user.getPassword())){
            throw new UserLoginException(LoginEnum.PWD_INCORRECT);
        }
        else{
            return new LoginResult(generateToken(user));
        }
    }

    @Override
    public User changePassword(String email, String pwdHashBefore, String pwd) {

        return null;
    }

    public String generateToken(User user){
        Calendar expCalendar = Calendar.getInstance();
        expCalendar.add(Calendar.HOUR, 1);
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
                throw new UserLoginException(LoginEnum.USER_NOT_FOUND);
            }
            return user;
        }catch(Exception e){
            throw new UserLoginException(LoginEnum.USER_NOT_AUTH);
        }
    }
}
