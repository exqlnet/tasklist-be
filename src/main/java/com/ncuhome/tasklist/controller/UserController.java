package com.ncuhome.tasklist.controller;

import com.ncuhome.tasklist.VO.LoginResult;
import com.ncuhome.tasklist.VO.ResultVO;
import com.ncuhome.tasklist.form.UserForm.ChangePasswordForm;
import com.ncuhome.tasklist.form.UserForm.LoginForm;
import com.ncuhome.tasklist.service.UserService;
import com.ncuhome.tasklist.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResultVO<LoginResult> login(@RequestBody @Valid LoginForm loginForm){
        LoginResult loginResult = userService.login(loginForm);
        return ResultVOUtil.success(loginResult);
    }

    @PostMapping("/changePassword")
    public ResultVO changePassword(@RequestBody @Valid ChangePasswordForm changePasswordForm){
        userService.changePassword(changePasswordForm);
        return ResultVOUtil.success("修改成功");
    }
}
