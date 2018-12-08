package com.ncuhome.tasklist.controller.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import com.ncuhome.tasklist.VO.LoginResult;
import com.ncuhome.tasklist.VO.ResultVO;
import com.ncuhome.tasklist.form.UserForm.ChangePasswordForm;
import com.ncuhome.tasklist.form.UserForm.LoginForm;
import com.ncuhome.tasklist.form.UserForm.RegisterForm;
import com.ncuhome.tasklist.form.UserForm.SendVcodeForm;
import com.ncuhome.tasklist.service.EmailService;
import com.ncuhome.tasklist.service.UserService;
import com.ncuhome.tasklist.util.BaseController;
import com.ncuhome.tasklist.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.transform.Result;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController extends BaseController {


    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @Autowired
    ResultVOUtil resultVOUtil;

    @PostMapping("/login")
    public ResultVO<LoginResult> login(@RequestBody @Valid LoginForm loginForm){
        LoginResult loginResult = userService.login(loginForm);
        return resultVOUtil.success(loginResult);
    }

    @PostMapping("/sendVcode")
    public Object sendVcode(@RequestBody SendVcodeForm sendVcodeForm){
        /*
            body:
                email : String
        */

//        JsonNode body = getBody();
//        String email = body.get("email").asText();
        String email = sendVcodeForm.getEmail();
        emailService.sendVerifyCode(email);
        return resultVOUtil.success("发送成功！");
    }

    @PostMapping("/register")
    public Object registerAccount(@RequestBody @Valid RegisterForm registerForm){
        String result = userService.register(registerForm);
        return resultVOUtil.success(result);
    }
}
