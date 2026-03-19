package ru.bank.email.lina.configuration.core.handller;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailEventHandller {
    String topic = "emailTopic";
    @KafkaListener(topics = "emailTopic")
    public void hendllerUserEvent(String message) {
        System.out.println("Пришло с другого модуля" + message);
    }

}
