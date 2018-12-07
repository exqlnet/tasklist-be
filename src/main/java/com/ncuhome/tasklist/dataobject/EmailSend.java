package com.ncuhome.tasklist.dataobject;


import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
@Data
public class EmailSend {

    @Id
    @GeneratedValue
    private Integer id;

    private String email;

    private String verifyCode;

}
