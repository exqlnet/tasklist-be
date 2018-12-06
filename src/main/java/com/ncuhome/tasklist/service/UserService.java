package com.ncuhome.tasklist.service;

import com.ncuhome.tasklist.VO.LoginResult;
import com.ncuhome.tasklist.VO.ResultVO;
import com.ncuhome.tasklist.dataobject.User;
import com.ncuhome.tasklist.form.UserForm.ChangePasswordForm;
import com.ncuhome.tasklist.form.UserForm.LoginForm;


public interface UserService {

    LoginResult login(LoginForm loginForm);

    String changePassword(ChangePasswordForm changePasswordForm);

    User verifyToken(String token);

}
