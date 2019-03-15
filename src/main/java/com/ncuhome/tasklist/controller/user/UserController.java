package com.ncuhome.tasklist.controller.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import com.ncuhome.tasklist.VO.LoginResult;
import com.ncuhome.tasklist.VO.ResultVO;
import com.ncuhome.tasklist.dataobject.EmailSend;
import com.ncuhome.tasklist.dataobject.User;
import com.ncuhome.tasklist.enums.HttpEnum;
import com.ncuhome.tasklist.form.UserForm.ChangePasswordForm;
import com.ncuhome.tasklist.form.UserForm.LoginForm;
import com.ncuhome.tasklist.form.UserForm.RegisterForm;
import com.ncuhome.tasklist.form.UserForm.SendVcodeForm;
import com.ncuhome.tasklist.repository.EmailSendRepository;
import com.ncuhome.tasklist.repository.UserRepository;
import com.ncuhome.tasklist.service.EmailService;
import com.ncuhome.tasklist.service.UserService;
import com.ncuhome.tasklist.util.BaseController;
import com.ncuhome.tasklist.util.JsonUtil;
import com.ncuhome.tasklist.util.MailUtil;
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

    @Autowired
    EmailSendRepository emailSendRepository;

    @PostMapping("/login")
    public ResultVO login(@RequestBody @Valid LoginForm loginForm){
        // 寻找用户
        User user = userRepository.findByEmail(loginForm.getEmail());
        if(user == null){return resultVOUtil.fromEnum(HttpEnum.USER_NOT_FOUND);}

        // 验证密码
        String token = userService.checkPassword(user, loginForm.getPassword());
        if(token == null){return resultVOUtil.fromEnum(HttpEnum.PWD_INCORRECT);}

        // 返回Token
        Map<String, String> data = new HashMap<>();
        data.put("token", token);
        return resultVOUtil.fromEnum(HttpEnum.LOGIN_SUCCESS, data);
    }

    @PostMapping("/sendVcode")
    public Object sendVcode(@RequestBody SendVcodeForm sendVcodeForm) throws Exception{
        /*
            body:
                email : String
        */

//        JsonNode body = getBody();
//        String email = body.get("email").asText();
        // 检查邮件格式
        if(!MailUtil.checkEmail(sendVcodeForm.getEmail())){
            return resultVOUtil.error("邮件格式不正确！");
        }
        String email = sendVcodeForm.getEmail();
        emailService.sendVerifyCode(email);
        return resultVOUtil.success("发送成功！");
    }

    @PostMapping("/register")
    public Object registerAccount(@RequestBody @Valid RegisterForm registerForm){

        EmailSend emailSend = emailSendRepository.findByEmail(registerForm.getEmail());
        if(emailSend == null || (!emailSend.getVerifyCode().equals(registerForm.getVerifyCode()))){
            return resultVOUtil.fromEnum(HttpEnum.CODE_VERIFY_FAILED);
        }

        User user = userService.register(registerForm);
        return resultVOUtil.fromEnum(HttpEnum.REGISTER_SUCCESS, user.getEmail());
    }

    @GetMapping("/exist")
    public Object isExist(String email){
        /*
        验证用户是否存在
            @Required String email; 邮箱
        */
        User user = userRepository.findByEmail(email);
        if(user == null){
            return resultVOUtil.fromEnum(HttpEnum.USER_NOT_FOUND, false);
        }
        else{
            return resultVOUtil.fromEnum(HttpEnum.USER_EXISTS, true);
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
            return resultVOUtil.fromEnum(HttpEnum.USER_NOT_FOUND);
        }
        String vcode = (String)body.get("verifyCode");
        String newPwd = (String)body.get("newPassword");
        String vcode_c = userService.getVerifyCode(user.getEmail());
        if(!vcode.equals(vcode_c)){
            return resultVOUtil.fromEnum(HttpEnum.CODE_VERIFY_FAILED);
        }
        else{
            userService.changePwd(user, newPwd);
            return resultVOUtil.success(HttpEnum.PWD_RESET_SUCCESS);
        }

    }
}
