package com.toad.bookservice.service;

import com.toad.bookservice.controller.BookController;
import com.toad.bookservice.dao.BookDao;
import com.toad.bookservice.model.Book;
import com.toad.bookservice.util.feign.NoteServiceClient;
import com.toad.bookservice.util.messages.Note;
import com.toad.bookservice.viewModel.BookViewModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.amqp.core.Binding.DestinationType.EXCHANGE;

@Component
public class ServiceLayer {

    BookDao bookDao;
    NoteServiceClient noteServiceClient;
    RabbitTemplate rabbitTemplate;


    public static final String EXCHANGE = "note-exchange";
    public static final String ROUTING_KEYS = "note.incomingNote";

    @Autowired
    public ServiceLayer(BookDao bookDao,NoteServiceClient noteServiceClient,RabbitTemplate rabbitTemplate) {
        this.bookDao = bookDao;
        this.noteServiceClient=noteServiceClient;
        this.rabbitTemplate=rabbitTemplate;
    }

    public BookViewModel buildBVM(Book book){
        BookViewModel bvm = new BookViewModel();
        bvm.setBookId(book.getBookId());
        bvm.setAuthor(book.getAuthor());
        bvm.setTitle(book.getTitle());
        bvm.setBookId(book.getBookId());

        //get notes from note service
        Note note = noteServiceClient.getNote(bvm.getBookId());     //create exception handling in case of no notes returned

        bvm.setNote(note);

        return bvm;

    }

    public List<BookViewModel> getAllBooks(){
        List<Book> bList = bookDao.getAllBooks();
        List<BookViewModel> bvmList = new ArrayList<>();

        for(Book book:bList){
           BookViewModel bvm = buildBVM(book);
           bvmList.add(bvm);
        }

        return bvmList;
    };

    public BookViewModel getBookbyId(int bookId){
        return buildBVM(bookDao.getBookbyId(bookId));

    };

    public void updateBook(BookViewModel bvm){
        Book book = new Book();
        book.setAuthor(bvm.getAuthor());
        book.setBookId(bvm.getBookId());
        book.setTitle(bvm.getTitle());

        bookDao.updateBook(book);

        Note note = new Note();
        note.setBookId(bvm.getBookId());
        note.setNote(bvm.getNote().getNote());
        note.setNoteId(bvm.getNote().getNoteId());

        //send note to queue
        System.out.println("Sending message");
        rabbitTemplate.convertAndSend(EXCHANGE,ROUTING_KEYS,note);
        System.out.println("Message Sent");
    };

    public BookViewModel createBook(BookViewModel bvm){
        System.out.println(bvm.toString());
        Book book = new Book();
        book.setAuthor(bvm.getAuthor());
        book.setTitle(bvm.getTitle());

        book = bookDao.createBook(book);

        Note note = new Note();
        note.setBookId(book.getBookId());
        System.out.println(note.toString());
        note.setNote(bvm.getNote().getNote());

        //send note to queue
        System.out.println("Sending message");
        rabbitTemplate.convertAndSend(EXCHANGE,ROUTING_KEYS,note);
        System.out.println("Message Sent");

        return buildBVM(book);

    };

    public void deleteBook(int bookId){
        noteServiceClient.deleteNote(bookId); //check to make sure it's deleting  all notes associated with the book; mght need another delete for single notes
        bookDao.deleteBook(bookId);
    };
}
