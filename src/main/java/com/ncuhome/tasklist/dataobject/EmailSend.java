package com.ncuhome.tasklist.dataobject;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class EmailSend {

    @Id
    @GeneratedValue
    private Integer id;

    private String email;

    private String verifyCode;

    private Date createTime = new Date(System.currentTimeMillis());

    public EmailSend(String email, String verifyCode) {
        this.email = email;
        this.verifyCode = verifyCode;
    }
}
