package com.ncuhome.tasklist.controller.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import com.ncuhome.tasklist.VO.LoginResult;
import com.ncuhome.tasklist.VO.ResultVO;
import com.ncuhome.tasklist.dataobject.User;
import com.ncuhome.tasklist.form.UserForm.ChangePasswordForm;
import com.ncuhome.tasklist.form.UserForm.LoginForm;
import com.ncuhome.tasklist.form.UserForm.RegisterForm;
import com.ncuhome.tasklist.form.UserForm.SendVcodeForm;
import com.ncuhome.tasklist.repository.UserRepository;
import com.ncuhome.tasklist.service.EmailService;
import com.ncuhome.tasklist.service.UserService;
import com.ncuhome.tasklist.util.BaseController;
import com.ncuhome.tasklist.util.JsonUtil;
import com.ncuhome.tasklist.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public ResultVO<LoginResult> login(@RequestBody @Valid LoginForm loginForm){
        LoginResult loginResult = userService.login(loginForm);
        return resultVOUtil.success(loginResult);
    }

    @PostMapping("/sendVcode")
    public Object sendVcode(@RequestBody SendVcodeForm sendVcodeForm) throws Exception{
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

    @GetMapping("/exist")
    public Object isExist(String email){
        /*
        验证用户是否存在
            @Required String email; 邮箱
        */
        User user = userRepository.findByEmail(email);
        if(user == null){
            return resultVOUtil.error("未找到用户");
        }
        else{
            return resultVOUtil.success("用户存在");
        }
    }

    @PostMapping("/forget")
    public Object forgetAccount(@RequestBody String jsonString){
        /*
        参数:
            @Required String email;
            @Required String verifyCode;
            @Required String newPassword;
         */
        Map<String, Object> body = JsonUtil.gson.fromJson(jsonString, HashMap.class);
        User user = userRepository.findByEmail((String) body.get("email"));
        if(user == null){
            return resultVOUtil.error("用户未找到，请检查邮箱是否正确。");
        }
        String vcode = (String)body.get("verifyCode");
        String newPwd = (String)body.get("newPassword");
        String vcode_c = userService.getVerifyCode(user.getEmail());
        if(!vcode.equals(vcode_c)){
            return resultVOUtil.error("验证码错误");
        }
        else{
            userService.changePwd(user, newPwd);
            return resultVOUtil.success("密码重置成功");
        }

    }
}
