package com.ncuhome.tasklist.dataobject;


import com.ncuhome.tasklist.form.NoteForm.CreateNoteForm;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Data
@Entity
public class Note {

    @Id
    @GeneratedValue
    private Integer noteId;

    @Lob
    private String content;

    private Integer userId;

    public Note(CreateNoteForm createNoteForm){
        BeanUtils.copyProperties(createNoteForm, this);
    }

    public Note(){}

}

