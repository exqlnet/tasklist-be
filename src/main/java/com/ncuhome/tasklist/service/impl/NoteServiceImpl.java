package com.ncuhome.tasklist.service.impl;

import com.ncuhome.tasklist.dataobject.Note;
import com.ncuhome.tasklist.dataobject.User;
import com.ncuhome.tasklist.form.NoteForm.CreateNoteForm;
import com.ncuhome.tasklist.form.NoteForm.ModifyNoteForm;
import com.ncuhome.tasklist.repository.NoteRepository;
import com.ncuhome.tasklist.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Slf4j
public class NoteServiceImpl implements NoteService {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    HttpServletRequest request;

    @Autowired
    User currentUser;

    @Override
    public Boolean createNote(CreateNoteForm createNoteForm) {
        Note note = new Note(createNoteForm);
        User user = (User) request.getAttribute("user");
        note.setUserId(user.getUserId());
        noteRepository.save(note);
        return true;
    }

    @Override
    public Boolean modifyNote(Note note, ModifyNoteForm modifyNoteForm) {
        note.setContent(modifyNoteForm.getContent());
        noteRepository.save(note);
        return true;
    }

    @Override
    public Boolean deleteNote(Integer noteId) {
        Note note = noteRepository.findByNoteId(noteId);
        if (note == null){
            return false;
        }
        noteRepository.delete(note);
        return true;
    }

    @Override
    public List<Note> listNote() {
        User user = (User) request.getAttribute("user");
        List<Note> noteList = noteRepository.findByUserId(user.getUserId());
        return noteList;
    }

    @Override
    public Note getNoteById(Integer noteId) {
        Note note = noteRepository.findByNoteId(noteId);
        return note;
    }
}
