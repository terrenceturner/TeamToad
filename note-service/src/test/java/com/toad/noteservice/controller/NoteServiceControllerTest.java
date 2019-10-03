package com.toad.noteservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toad.noteservice.dao.NoteDao;
import com.toad.noteservice.model.Note;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@RunWith(SpringJUnit4ClassRunner.class)
public class NoteServiceControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    NoteDao dao;

    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void addNote() throws Exception{

        Note outputNote = new Note(1,"Note");
        Note inputNote = new Note(0,"Note");
        outputNote.setNoteId(1);


        String outputJson = mapper.writeValueAsString(outputNote);
        String inputJson = mapper.writeValueAsString(inputNote);

        when(dao.addNote(inputNote)).thenReturn(outputNote);

        mvc.perform(MockMvcRequestBuilders.post("/notes/notes")
            .content(inputJson)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.noteId")
                .exists());

    }
}