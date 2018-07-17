package com.bingo.logops.common.kafka;

import com.bingo.logops.common.log.LogService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class ConsumerListener {

    @KafkaListener(topics = {

    })
    public void listenerLog(ConsumerRecord<?,?> record){
        LogService.saveLog(record.topic(),record.value());
    }
}
