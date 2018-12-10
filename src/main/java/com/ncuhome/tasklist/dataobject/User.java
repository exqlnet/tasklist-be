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
        password = MD5Util.md5(pwd);
    }

    @OneToMany(cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinColumn(name="userId", referencedColumnName = "userId")
    private List<Task> tasks;

    public User(String email, String password) {
        this.password = MD5Util.md5(password);
        this.email = email;
        this.description = "这个人很懒，什么都没有留下。";
        this.username = "沙雕壹号";
        this.avatarPicName = "avatar_default.jpg";
    }

    public User(){
    }
}
