package com.ncuhome.tasklist.form.UserForm;


import lombok.Data;

@Data
public class RegisterForm {
    private String email;

    private String verifyCode;

    private String password;

}
