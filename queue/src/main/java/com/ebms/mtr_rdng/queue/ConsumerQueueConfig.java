package com.ebms.mtr_rdng.queue;

import com.ebms.mtr_rdng.domain.model.MeterReading;
import com.ebms.mtr_rdng.service.MeterReadingService;
import com.ebms.util.queue.deserializer.ConsumerCustomDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConsumerQueueConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${meter.reading.topic}")
    private String meterReadingTopic;

    public Map<String,Object> consumerConfig(){
        return new HashMap<String,Object>(){{
            put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        }};
    }

    @Bean
    public ConsumerCustomDeserializer consumerCustomDeserializer(){
        return new ConsumerCustomDeserializer(new HashMap<String,Class<?>>(){{
            put(meterReadingTopic, MeterReading.class);
        }});
    }

    @Bean
    public ConsumerFactory<String, MeterReading> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfig(),new StringDeserializer(),consumerCustomDeserializer());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, MeterReading>>
    userFactory(ConsumerFactory consumerFactory){
        ConcurrentKafkaListenerContainerFactory<String, MeterReading> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

}
