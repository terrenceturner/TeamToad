package com.toad.noteservice.dao;

import com.toad.noteservice.dao.NoteDao;
import com.toad.noteservice.model.Note;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteDaoTest {

    @Autowired
    NoteDao noteDao;

    @Before
    public void setUp() throws Exception {
        //clean out db
        noteDao.deleteAll();

    }

    @Test
    public void addNote() {
        Note note = new Note(1, "nice note");
        note = noteDao.addNote(note);
        Note fromDao = noteDao.getNote(note.getNoteId());
        assertEquals(fromDao, note);
    }

    @Test
    public void getNote() {
        Note note = new Note(1, "nice note");
        note = noteDao.addNote(note);
        Note fromDao = noteDao.getNote(note.getNoteId());
        assertEquals(fromDao, note);
    }

    @Test
    public void getAllNotes() {

        Note note = new Note(1, "nice note");
        noteDao.addNote(note);

        note = new Note(2, "cool note");
        noteDao.addNote(note);

        List<Note> notes = noteDao.getAllNotes();
        assertEquals(2, notes.size());
    }

    @Test
    public void getNotesByBookId() {

        Note note = new Note(1, "nice note");
        noteDao.addNote(note);

        note = new Note(1, "cool note");
        noteDao.addNote(note);

        note = new Note(2, "bad note");
        noteDao.addNote(note);

        List<Note> notes = noteDao.getNotesByBookId(1);
        assertEquals(2, notes.size());

        notes = noteDao.getNotesByBookId(2);
        assertEquals(1, notes.size());


    }

    @Test
    public void updateNote() {
        Note note = new Note(1, "nice note");
        note = noteDao.addNote(note);
        note.setBookId(5);
        noteDao.updateNote(note);
        Note fromNoteDao = noteDao.getNote(note.getNoteId());
        assertEquals(note, fromNoteDao);
    }

    @Test
    public void deleteNote() {
        Note note = new Note(1, "nice note");
        note = noteDao.addNote(note);
        noteDao.deleteNote(note.getNoteId());
        Note fromDao = noteDao.getNote(note.getNoteId());
        assertNull(fromDao);
    }
}