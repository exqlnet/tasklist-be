package com.ncuhome.tasklist.service;

import com.ncuhome.tasklist.VO.LoginResult;
import com.ncuhome.tasklist.dataobject.User;
import com.ncuhome.tasklist.form.LoginForm;


public interface UserService {

    LoginResult checkPassword(LoginForm loginForm);

    User changePassword(String email, String pwdHashBefore, String pwd);

}
