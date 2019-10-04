package com.toad.notequeueconsumer.util.feign;

import com.toad.notequeueconsumer.util.messages.Note;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "note-service")
public interface NoteClient {

    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    Note addNote(Note note);

    @RequestMapping(value = "/notes", method = RequestMethod.PUT)
    void updateNote(Note note);

}
