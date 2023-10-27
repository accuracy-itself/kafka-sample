package com.expert.producer;
import com.expert.model.Message;
import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.admin.NewPartitions;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.admin.AdminClient;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Service
public class ProducerService {
    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    Logger logger = LoggerFactory.getLogger(ProducerService.class);

    public void produce(Message message, String key) throws ExecutionException, InterruptedException {
        System.out.println("Producing the message: " + message);
        RecordMetadata metadata = kafkaTemplate.send("messages", key, message).get().getRecordMetadata();

        logger.info(String.valueOf(metadata.partition()));
        logger.info(String.valueOf(metadata.offset()));
    }

    /*@PostConstruct
    public void initialize() throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers","localhost:9092");
        NewPartitions newPartitions = NewPartitions.increaseTo(2);
        AdminClient adminClient = AdminClient.create(props);
        adminClient.createPartitions(Collections.singletonMap("messages", newPartitions)).all().get();
    }*/
}