package com.ncuhome.tasklist.enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum LoginEnum {
    SUCCESS(1, "登陆成功"),
    USER_NOT_FOUND(2, "用户未找到"),
    PWD_INCORRECT(3, "密码错误"),
    USER_NOT_AUTH(4, "未认证");

    private Integer code;

    private String message;
    LoginEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
