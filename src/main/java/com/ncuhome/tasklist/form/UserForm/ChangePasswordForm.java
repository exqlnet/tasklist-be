package com.ncuhome.tasklist.form.UserForm;

import lombok.Data;

import javax.validation.Valid;


@Data
public class ChangePasswordForm {

    @Valid
    private String email;

    @Valid
    private String pwdBefore;

    @Valid
    private String pwdNew;

}
