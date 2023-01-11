package com.ebms.mtr_rdng.service;

import com.ebms.mtr_rdng.domain.model.MeterReading;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class MeterReadingService {

    @KafkaListener(topics = "${meter.reading.topic}", groupId = "def", containerFactory = "userFactory")
    void meterReading(MeterReading data){
        System.out.println("Listener received : " + data.mId() + data.billingCycle() + data.unitReading());
    }

}
