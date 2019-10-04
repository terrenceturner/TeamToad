package com.toad.notequeueconsumer;

import com.toad.notequeueconsumer.util.feign.NoteClient;
import com.toad.notequeueconsumer.util.messages.Note;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    @Autowired
    NoteClient noteClient;

    @RabbitListener(queues = NoteQueueConsumerApplication.QUEUE_NAME)
    public void receiveMessage(Note note) {

        // If the note has no id invoke add controller
        if(note.getNoteId()!=0){
            System.out.println("got note without id");
            noteClient.addNote(note);
        }

        // if note does have id then invoke update controller
        else{
            System.out.println("got note with id");
            noteClient.updateNote(note);
        }
    }
}
