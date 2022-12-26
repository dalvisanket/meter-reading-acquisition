package com.ebms.mtr_rdng.controller;

import com.ebms.mtr_rdng.db.domain.model.ConsumerRow;
import com.ebms.mtr_rdng.db.domain.model.MeterRow;
import com.ebms.mtr_rdng.db.domain.repository.DatabaseRepository;
import com.ebms.mtr_rdng.domain.model.MeterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.concurrent.ThreadLocalRandom;

@RestController
public class MeterReadingController {

    @Autowired
    DatabaseRepository databaseRepository;

    @PutMapping("/new-meter")
    public long addNewMeter(){
        long newMeterId = ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);
        return databaseRepository.addNewMeter(newMeterId, MeterType.RESIDENTIAL);
    }

    @GetMapping("/get-meter/{meter_id}")
    public MeterRow getMeter(@PathVariable(name = "meter_id") long meter_id){
        return databaseRepository.getMeter(meter_id);
    }

    @PutMapping("/new-consumer")
    public long addNewConsumer(@RequestBody ConsumerRow consumer){
        long newConsumerId = ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);

        ConsumerRow newConsumer = ConsumerRow.builder()
                .consumer_id(newConsumerId)
                .name(consumer.name())
                .email(consumer.email())
                .address(consumer.address())
                .city(consumer.city())
                .zipcode(consumer.zipcode())
                .is_active("yes")
                .build();

        return databaseRepository.addNewConsumer(newConsumer);
    }
}
