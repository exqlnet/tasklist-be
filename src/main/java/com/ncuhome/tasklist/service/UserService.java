package com.ncuhome.tasklist.service;

import com.ncuhome.tasklist.VO.LoginResult;
import com.ncuhome.tasklist.VO.ResultVO;
import com.ncuhome.tasklist.dataobject.User;
import com.ncuhome.tasklist.form.UserForm.ChangePasswordForm;
import com.ncuhome.tasklist.form.UserForm.LoginForm;
import com.ncuhome.tasklist.form.UserForm.RegisterForm;


public interface UserService {

    LoginResult login(LoginForm loginForm);

    String changePassword(User user, ChangePasswordForm changePasswordForm);

    User verifyToken(String token);

    String register(RegisterForm registerForm);

    String getVerifyCode(String email);

    Boolean changePwd(User user, String newPwd);

}
