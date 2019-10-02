package com.toad.bookservice.dao;

import com.toad.bookservice.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDaoJdbcTemplateImpl implements BookDao {

    //prepared statements
    public static final String INSERT_BOOK =
            "insert into book (title, author) values (?,?)";
    public static final String SELECT_BOOK_BY_ID =
            "select * from book where book_id = ?";
    public static final String SELECT_ALL_BOOKS =
            "select * from book";
    public static final String UPDATE_BOOK =
            "update book set title = ?, author = ? where book_id = ?";
    public static final String DELETE_BOOK =
            "delete from book where book_id = ?";

    @Autowired
    JdbcTemplate temp;

    public BookDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.temp = jdbcTemplate;
    }

    @Override
    public Book createBook(Book book) {
        temp.update(INSERT_BOOK,
                book.getTitle(),
                book.getAuthor());

        int id = temp.queryForObject("select last_insert_id()",Integer.class);
        book.setBookId(id);
        return book;
    }
    @Override
    public Book getBookbyId(int id) {
        return temp.queryForObject(SELECT_BOOK_BY_ID,this::MapRowToBook,id);
    }
    @Override
    public List<Book> getAllBooks() {
        return temp.query(SELECT_ALL_BOOKS,this::MapRowToBook);
    }
    @Override
    public void updateBook(Book book) {
        temp.update(UPDATE_BOOK,
                book.getTitle(),
                book.getAuthor(),
                book.getBookId());
    }
    @Override
    public void deleteBook(int id) {
        temp.update(DELETE_BOOK,id);
    }
    public Book MapRowToBook(ResultSet rs, int num) throws SQLException {
        Book book = new Book();
        book.setBookId(rs.getInt("book_id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        return book;
    }
}
