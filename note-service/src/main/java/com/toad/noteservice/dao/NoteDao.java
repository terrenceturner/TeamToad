package com.toad.noteservice.dao;

import com.toad.noteservice.model.Note;

import java.util.List;

public interface NoteDao {

     Note addNote(Note note);

     Note getNote(int id);

     List<Note> getAllNotes();

     List<Note> getNotesByBookId(int id);

     void updateNote(Note note);

     void deleteNote(int id);

     void deleteAll();
}
