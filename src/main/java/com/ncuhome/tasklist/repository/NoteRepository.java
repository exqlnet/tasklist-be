package com.ncuhome.tasklist.repository;

import com.ncuhome.tasklist.dataobject.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    Note findByNoteId(Integer noteId);
}
