package com.toad.bookservice.util.feing;

import com.toad.bookservice.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "note-service")
@RequestMapping("/notes")
public interface NoteClient {

    @GetMapping(value = "/{id}")
    public Note getNote(int id);

    @GetMapping
    public List<Note> getAllNote();

    @GetMapping(value = "/book/{book_id}")
    public List<Note> getNotesByBook(int book_id);

    @DeleteMapping(value = "/{id}")
    public void deleteNote(int id);

}
