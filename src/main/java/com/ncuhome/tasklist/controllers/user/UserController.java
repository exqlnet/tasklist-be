package com.ncuhome.tasklist.baseController;

import com.ncuhome.tasklist.VO.LoginResult;
import com.ncuhome.tasklist.VO.ResultVO;
import com.ncuhome.tasklist.form.UserForm.ChangePasswordForm;
import com.ncuhome.tasklist.form.UserForm.LoginForm;
import com.ncuhome.tasklist.service.UserService;
import com.ncuhome.tasklist.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
