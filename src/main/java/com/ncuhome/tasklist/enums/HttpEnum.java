package com.ncuhome.tasklist.enums;

import lombok.Getter;

@Getter
public enum HttpEnum {
    // Other Enums

    // Task Enums
    TASK_NOT_BTY(403, "该任务不属于你！"),
    TASK_NOT_FOUND(404, "任务未找到！"),


    // User Enums
    LOGIN_SUCCESS(200, "登陆成功"),
    USER_EXISTS(200, "用户已存在"),
    REGISTER_SUCCESS(200, "注册成功"),
    USER_NOT_FOUND(404, "用户未找到"),
    PWD_INCORRECT(403, "密码错误"),
    USER_NOT_AUTH(403, "未认证"),
    PWD_BEFORE_INCORRECT(403, "原密码错误"),
    CODE_VERIFY_FAILD(403, "邮箱验证失败");

    private Integer httpCode;

    private String message;

    HttpEnum(Integer code, String message) {
        this.httpCode = code;
        this.message = message;
    }
}
