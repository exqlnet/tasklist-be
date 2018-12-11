package com.ncuhome.tasklist.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Data
public class Feedback {

    @Id
    @GeneratedValue
    private Integer feedbackId;

    @Lob
    private String content;

    private String type;

    private Integer userId;

    public Feedback(String content, String type,Integer userId) {
        this.content = content;
        this.userId = userId;
        this.type = type;
    }
}
