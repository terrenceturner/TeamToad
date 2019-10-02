package com.toad.bookservice.dao;

import com.toad.bookservice.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

        assertEquals(1,book1.getBookId());
        assertEquals(book.getAuthor(),book1.getAuthor());
        assertEquals(book.getTitle(),book1.getTitle());
    }

    @Test
    public void getBookbyId() {

    }

    @Test
    public void getAllBooks() {
    }

    @Test
    public void updateBook() {
    }

    @Test
    public void deleteBook() {
    }

    @Test
    public void mapRowToBook() {
    }
}