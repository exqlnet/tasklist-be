package com.ncuhome.tasklist.dataobject;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Note {

    @Id
    @GeneratedValue
    private Integer noteId;

    @Lob
    private String content;

    private Integer userId;

}
