package com.ebms.mtr_rdng;


import com.ebms.mtr_rdng.domain.model.ResidentialMeterReading;
import com.ebms.mtr_rdng.domain.model.queue.MeterReadingQueueName;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.YearMonth;

@SpringBootApplication
public class MeterReadingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeterReadingApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String, Object> kafkaTemplate){
        return args -> {

                ResidentialMeterReading residentialMeterReading = ResidentialMeterReading.builder()
                        .unitReading(12345)
                        .mId(987)
                        .readingForYearMonth(YearMonth.now())
                        .build();

                for(int i = 0; i<10; i++)
                kafkaTemplate.send(MeterReadingQueueName.METER_READING_QUEUE, residentialMeterReading);

        };
    }
}
