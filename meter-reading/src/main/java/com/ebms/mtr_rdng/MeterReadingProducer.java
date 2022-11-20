package com.ebms.mtr_rdng;

import com.ebms.mtr_rdng.domain.model.ResidentialMeterReading;
import com.ebms.mtr_rdng.domain.model.queue.MeterReadingQueueName;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.YearMonth;

public class MeterReadingProducer {
    private final KafkaTemplate kafkaTemplate;

    public MeterReadingProducer(KafkaTemplate kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(){
        this.kafkaTemplate.send(MeterReadingQueueName.METER_READING_QUEUE, ResidentialMeterReading.
                builder().
                readingForYearMonth(YearMonth.now())
                .unitReading(9.00)
                .build());
    }
}
