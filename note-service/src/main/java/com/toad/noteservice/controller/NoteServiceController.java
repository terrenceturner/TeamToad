package com.toad.noteservice.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toad.noteservice.dao.NoteDao;
import com.toad.noteservice.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/notes")
public class NoteServiceController {

    // Properties
    @Autowired
    private NoteDao noteDao;

    // Constructors
    public NoteServiceController(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Note addNote(@RequestBody Note note) {
        return noteDao.addNote(note);
    }

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Note getNote(@PathVariable int id) {
        return noteDao.getNote(id);
    }

    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Note> getAllNote() {
        return noteDao.getAllNotes();
    }

    @RequestMapping(value = "/notes/book/{book_id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Note> getNotesByBook(@PathVariable int book_id) {
        return noteDao.getNotesByBookId(book_id);
    }

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updatedNote(@RequestBody Note note) {
        noteDao.updateNote(note);
    }

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteNote(@PathVariable int id) {
        noteDao.deleteNote(id);
    }

}
