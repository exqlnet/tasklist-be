package com.ncuhome.tasklist.VO;


import lombok.Data;

@Data
public class LoginResult {

    public LoginResult(String token) {
        this.token = token;
    }

    private String token;
}
