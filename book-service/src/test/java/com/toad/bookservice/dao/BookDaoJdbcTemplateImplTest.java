package com.toad.bookservice.dao;

import com.toad.bookservice.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookDaoJdbcTemplateImplTest {

    @Autowired
    BookDao bookDao;

    private Book book;

    @Before
    public void setUp() throws Exception {

        bookDao.getAllBooks().forEach(book -> {
            bookDao.deleteBook(book.getBookId());
        });

        book = new Book();
        book.setTitle("Title");
        book.setAuthor("Author");

    }

    @Test
    public void createBook() {
        Book book1 = bookDao.createBook(book);

        assertEquals(book.getAuthor(),book1.getAuthor());
        assertEquals(book.getTitle(),book1.getTitle());
    }

    @Test
    public void getBookbyId() {
        Book book1 = bookDao.createBook(book);

        Book book2 =  bookDao.getBookbyId(book.getBookId());

        assertEquals(book1,book2);
    }

    @Test
    public void getAllBooks() {
        List<Book> bList = new ArrayList<>();
        bList.add(bookDao.createBook(book));

        assertEquals(1,bList.size());
    }

    @Test
    public void updateBook() {
        Book book1 = bookDao.createBook(book);

        book1.setTitle("new title");

        bookDao.updateBook(book1);
        Book book2 = bookDao.getBookbyId(book1.getBookId());

        assertEquals(book1,book2);
        
    }

    @Test
    public void deleteBook() {
        book = bookDao.createBook(book);

        bookDao.deleteBook(book.getBookId());
        List<Book> bList = bookDao.getAllBooks();

        assertEquals(0,bList.size());
    }
}