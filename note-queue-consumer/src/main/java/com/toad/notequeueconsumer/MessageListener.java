package com.toad.notequeueconsumer;

import com.toad.notequeueconsumer.util.messages.Note;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    @RabbitListener(queues = NoteQueueConsumerApplication.QUEUE_NAME)
    public void receiveMessage(Note note) {

        // If the note has an id invoke update controller
        // if note does not have id then invoke add controller
    }
}
