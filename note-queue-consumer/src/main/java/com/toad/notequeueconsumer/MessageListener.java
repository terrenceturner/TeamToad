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

        // If the note has an id invoke update controller
        if(note.getNoteId()!=0){
            noteClient.addNote(note);
        }

        // if note does not have id then invoke add controller
        else{
            noteClient.updateNote(note);
        }
    }
}
