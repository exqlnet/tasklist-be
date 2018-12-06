package com.ncuhome.tasklist.controllers.user;

import com.ncuhome.tasklist.VO.LoginResult;
import com.ncuhome.tasklist.VO.ResultVO;
import com.ncuhome.tasklist.annotations.LoginRequired;
import com.ncuhome.tasklist.dataobject.User;
import com.ncuhome.tasklist.form.UserForm.ChangePasswordForm;
import com.ncuhome.tasklist.form.UserForm.LoginForm;
import com.ncuhome.tasklist.service.UserService;
import com.ncuhome.tasklist.util.BaseController;
import com.ncuhome.tasklist.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Slf4j
public class InfoController extends BaseController {


    @Autowired
    UserService userService;

    @PostMapping("/changePassword")
    @LoginRequired
    public ResultVO changePassword(@RequestBody @Valid ChangePasswordForm changePasswordForm){
        userService.changePassword(changePasswordForm);
        return ResultVOUtil.success("修改成功");
    }


    @GetMapping("/info")
    @LoginRequired
    public ResultVO getInfo(){
        return ResultVOUtil.success(getUser().getEmail());
    }
}
