package com.ncuhome.tasklist.dataobject;

import com.ncuhome.tasklist.util.MD5Util;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class User {

    @Autowired
    MD5Util md5Util;

    @Id
    @GeneratedValue
    private Integer user_id;

    private String password;

    private String email;

    void setPassword(String pwd){
        password = md5Util.md5(pwd);
    }
}
