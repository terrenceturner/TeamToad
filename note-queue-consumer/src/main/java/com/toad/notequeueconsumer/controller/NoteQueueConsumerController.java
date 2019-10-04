package com.toad.notequeueconsumer.controller;

import com.toad.notequeueconsumer.util.feign.NoteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;

@RestController
@RefreshScope
public class NoteQueueConsumerController {

    @Autowired
    private final NoteClient noteClient;

    public NoteQueueConsumerController(NoteClient noteClient) {
        this.noteClient = noteClient;
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public Book addBook(){

        return noteClient.addNote(note);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
    public Book updateBook(){
        return noteClient.updateNote(note);
    }


}
