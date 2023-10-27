package com.expert.consumer;

import com.expert.model.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.kafka.support.KafkaHeaders;


@Service
public class ConsumerService {
    Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @KafkaListener(topics = "messages", groupId = "message_group_id")
    public void consume(Message message,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                        @Header(KafkaHeaders.OFFSET) Long offset) {

        System.out.println("Consuming message with offset " + offset
                + " and partition " + partition
                + ": " + message);
    }
}