package com.ncuhome.tasklist.service.impl;

import com.ncuhome.tasklist.VO.NoteVO;
import com.ncuhome.tasklist.form.NoteForm.CreateNoteForm;
import com.ncuhome.tasklist.form.NoteForm.ModifyNoteForm;
import com.ncuhome.tasklist.repository.NoteRepository;
import com.ncuhome.tasklist.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    NoteRepository noteRepository;


    @Override
    public Boolean createNote(CreateNoteForm createNoteForm) {
        
        return null;
    }

    @Override
    public Boolean modifyNote(ModifyNoteForm modifyNoteForm) {
        return null;
    }

    @Override
    public Boolean deleteNote(Integer noteId) {
        return null;
    }

    @Override
    public List<NoteVO> listNote() {
        return null;
    }

    @Override
    public NoteVO getNoteById(Integer noteId) {
        return null;
    }
}
