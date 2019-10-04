package com.toad.bookservice.controller;


import com.toad.bookservice.service.ServiceLayer;
import com.toad.bookservice.util.feign.NoteServiceClient;
import com.toad.bookservice.util.messages.Note;
import com.toad.bookservice.viewModel.BookViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import java.util.List;
import com.toad.bookservice.dao.BookDao;
import com.toad.bookservice.model.Book;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/books")
@RefreshScope
public class BookController {

    @Autowired
    NoteServiceClient noteServiceClient;

    @Autowired
    ServiceLayer service;

    public BookController(NoteServiceClient noteServiceClient, ServiceLayer service) {
        this.noteServiceClient = noteServiceClient;
        this.service = service;
    }

    public BookController(){}


    @GetMapping("/book/{id}")
    public BookViewModel getBookById(@PathVariable int id){
        return service.getBookbyId(id);
    }

    @GetMapping("/allBooks")
    public List<BookViewModel> getAllBooks (){
        return service.getAllBooks();
    }

    @PostMapping("/book")
    public BookViewModel createBook(BookViewModel bvm){
        return service.createBook(bvm);
    }

    @PutMapping("/books/{bookId}")
    public void updateBook(@PathVariable int bookId, @RequestBody BookViewModel bvm){
        service.updateBook(bvm);
    }

    @RequestMapping(value = "/notes/{id}",method = RequestMethod.GET)
    public Note getNotes(@PathVariable int id){
        return noteServiceClient.getNote(id);
    }

    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    public List<Note> getAllNote(){
        return noteServiceClient.getAllNote();
    }

    @RequestMapping(value = "/notes/book/{book_id}", method = RequestMethod.GET)
    public List<Note> getNotesByBook(int book_id){
        return noteServiceClient.getNotesByBook(book_id);
    }

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.DELETE)
    public void deleteNote(int id){
        noteServiceClient.deleteNote(id);
    }


}
