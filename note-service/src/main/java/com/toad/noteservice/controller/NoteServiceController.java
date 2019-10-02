package com.toad.noteservice.controller;

import com.toad.noteservice.dao.NoteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes")
public class NoteServiceController {

    @Autowired
    NoteDao noteDao;
}
