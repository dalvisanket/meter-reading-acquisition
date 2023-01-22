package com.ebms.mtr_rdng.service;

import com.ebms.mtr_rdng.db.domain.repository.DatabaseRepository;
import com.ebms.mtr_rdng.domain.model.MeterReading;

import com.ebms.mtr_rdng.domain.model.ResidentialMeterReading;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;


@Service
public class MeterReadingService {

    @Autowired
    DatabaseRepository databaseRepository;

    @Autowired
    KafkaTemplate<String,Object> kafkaTemplate;

    @KafkaListener(topics = "${meter.reading.topic}", groupId = "def", containerFactory = "userFactory")
    void meterReading(MeterReading meterReading){
        long newReadingId = ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);
        if(meterReading instanceof ResidentialMeterReading){
            ResidentialMeterReading residentialMeterReading = (ResidentialMeterReading) meterReading;
            residentialMeterReading = residentialMeterReading.toBuilder().reading_id(newReadingId).build();
            databaseRepository.saveMeterReading(residentialMeterReading);
            kafkaTemplate.send("BILLING-DEV",residentialMeterReading);
        }

    }

}
