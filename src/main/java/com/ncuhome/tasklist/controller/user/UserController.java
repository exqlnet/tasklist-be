package com.ncuhome.tasklist.baseController;

import com.ncuhome.tasklist.VO.LoginResult;
import com.ncuhome.tasklist.VO.ResultVO;
import com.ncuhome.tasklist.form.UserForm.ChangePasswordForm;
import com.ncuhome.tasklist.form.UserForm.LoginForm;
import com.ncuhome.tasklist.form.UserForm.RegisterForm;
import com.ncuhome.tasklist.service.EmailService;
import com.ncuhome.tasklist.service.UserService;
import com.ncuhome.tasklist.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.transform.Result;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {


    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @PostMapping("/login")
    public ResultVO<LoginResult> login(@RequestBody @Valid LoginForm loginForm){
        LoginResult loginResult = userService.login(loginForm);
        return ResultVOUtil.success(loginResult);
    }

    @PostMapping("/sendVerifyCode")
    public Object sendVerifyCode(@RequestBody @Valid String email){
        String content = "123456";
        emailService.sendEmail(email, content);
        return ResultVOUtil.success("ok");
    }

    @PostMapping("/register")
    public Object registerAcount(@RequestBody @Valid RegisterForm registerForm){
        registerForm.getEmail();
    }
}
