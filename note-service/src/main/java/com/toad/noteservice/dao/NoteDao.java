package com.toad.noteservice.dao;

import com.toad.noteservice.model.Note;

import java.util.List;

public interface NoteDao {

    public Note addNote(Note note);

    public Note getNote(int id);

    public List<Note> getAllNotes();

    public List<Note> getNotesByBookId(int id);

    public void updateNote(Note note);

    public void deleteNote(int id);

    public void deleteAll();
}
