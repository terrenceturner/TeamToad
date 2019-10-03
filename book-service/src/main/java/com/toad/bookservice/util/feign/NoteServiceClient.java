package com.toad.bookservice.util.feign;

import com.toad.bookservice.util.messages.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name="note-service")
public interface NoteServiceClient {

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.GET)
    Note getNote(int id);

    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    List<Note> getAllNote();

    @RequestMapping(value = "/notes/book/{book_id}", method = RequestMethod.GET)
    List<Note> getNotesByBook(int book_id);

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.DELETE)
    void deleteNote(int id);
}
