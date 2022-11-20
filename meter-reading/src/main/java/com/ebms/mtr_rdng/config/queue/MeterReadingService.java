package com.ebms.mtr_rdng.config.queue;

import com.ebms.mtr_rdng.domain.model.MeterReading;
import com.ebms.mtr_rdng.domain.model.queue.MeterReadingQueueName;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class MeterReadingService {

    @KafkaListener(topics = MeterReadingQueueName.METER_READING_QUEUE, groupId = "def", containerFactory = "userFactory")
    void meterReading(MeterReading data){
        System.out.println("Listener received : " + data.mId() + data.readingForYearMonth() + data.unitReading());
    }

}
