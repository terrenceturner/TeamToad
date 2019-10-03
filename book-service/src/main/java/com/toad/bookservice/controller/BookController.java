package com.toad.bookservice.controller;

import com.toad.bookservice.dao.BookDao;
import com.toad.bookservice.model.Book;
import com.toad.bookservice.service.ServiceLayer;
import com.toad.bookservice.util.feing.NoteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
@RefreshScope
public class BookController {

    @Autowired
    private final NoteClient noteClient;

    @Autowired
    private ServiceLayer serviceLayer;

    public BookController(NoteClient noteClient) {
        this.noteClient = noteClient;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        serviceLayer
    }


}
