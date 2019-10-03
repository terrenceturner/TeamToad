package com.toad.bookservice.viewModel;

import com.toad.bookservice.util.messages.Note;

public class BookViewModel {
    private int bookId;
    private String author;
    private String title;
    private Note note;

    public BookViewModel(int bookId, String author, String title, Note note) {
        this.bookId = bookId;
        this.author = author;
        this.title = title;
        this.note = note;
    }

    public BookViewModel() {}

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }


}
