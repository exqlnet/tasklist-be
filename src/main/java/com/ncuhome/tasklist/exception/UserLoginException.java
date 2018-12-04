package com.ncuhome.tasklist.exception;

import com.ncuhome.tasklist.enums.LoginEnum;

import javax.security.auth.login.LoginException;

public class UserLoginException extends RuntimeException{

    private Integer code;

    public UserLoginException(LoginEnum loginEnum){
        super(loginEnum.getMessage());
        this.code = loginEnum.getCode();
    }

    public UserLoginException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
