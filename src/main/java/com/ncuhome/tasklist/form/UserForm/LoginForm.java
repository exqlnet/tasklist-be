package com.ncuhome.tasklist.form.UserForm;


import lombok.Data;

import javax.validation.Valid;

@Data
public class LoginForm {

    @Valid
    private String email;

    @Valid
    private String password;

}
