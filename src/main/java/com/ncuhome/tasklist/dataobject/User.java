package com.ncuhome.tasklist.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ncuhome.tasklist.util.MD5Util;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class User{

    @Id
    @GeneratedValue
    private Integer userId;

    private String password;

    private String username;

    private String description;

    private String avatarPicName;

    private String email;

    public void setPassword(String pwd){
        MD5Util md5Util = new MD5Util();
        password = md5Util.md5(pwd);
    }

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Task> tasks;
}
