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

    @Id
    @GeneratedValue
    private Integer userId;

    private String password;

    private String email;

    public void setPassword(String pwd){
        MD5Util md5Util = new MD5Util();
        password = md5Util.md5(pwd);
    }
}
