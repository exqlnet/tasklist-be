package com.ncuhome.tasklist.service;

import com.ncuhome.tasklist.VO.NoteVO;
import com.ncuhome.tasklist.form.NoteForm.CreateNoteForm;
import com.ncuhome.tasklist.form.NoteForm.ModifyNoteForm;

import java.util.List;

public interface NoteService {

    Boolean createNote(CreateNoteForm createNoteForm);

    Boolean modifyNote(ModifyNoteForm modifyNoteForm);

    Boolean deleteNote(Integer noteId);

    List<NoteVO> listNote();

    NoteVO getNoteById(Integer noteId);
}
