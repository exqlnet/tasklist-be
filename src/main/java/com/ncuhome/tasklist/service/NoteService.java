package com.ncuhome.tasklist.service;

import com.ncuhome.tasklist.VO.NoteVO;
import com.ncuhome.tasklist.dataobject.Note;
import com.ncuhome.tasklist.form.NoteForm.CreateNoteForm;
import com.ncuhome.tasklist.form.NoteForm.ModifyNoteForm;

import java.util.List;

public interface NoteService {

    Boolean createNote(CreateNoteForm createNoteForm);

    Boolean modifyNote(Note note, ModifyNoteForm modifyNoteForm);

    Boolean deleteNote(Integer noteId);

    List<Note> listNote();

    Note getNoteById(Integer noteId);
}
