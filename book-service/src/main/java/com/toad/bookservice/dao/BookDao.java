package com.toad.bookservice.dao;

import com.toad.bookservice.model.Book;

import java.util.List;

public interface BookDao {

    public List<Book> getAllBooks();

    public Book getBookbyId(int bookId);

    public void updateBook(int bookId);

    public Book createBook(Book book);

    public void deleteBook(int bookId);

}
