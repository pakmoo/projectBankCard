package ru.bank.card.lina.core.configuration;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSendTemplates {
    private KafkaTemplate<String, String> test;

    public KafkaSendTemplates(KafkaTemplate<String, String> test) {
        this.test = test;
    }

    public void sendMyFirstMessage (String message){
        test.send("emailTopic", message);
    }
}
