package com.ebms.mtr_rdng.controller;

import com.ebms.mtr_rdng.db.domain.model.ConsumerRow;
import com.ebms.mtr_rdng.db.domain.model.MeterRow;
import com.ebms.mtr_rdng.db.domain.repository.DatabaseRepository;
import com.ebms.mtr_rdng.domain.model.MeterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class MeterReadingController {

    @Autowired
    DatabaseRepository databaseRepository;

    @PostMapping("/new-meter")
    public ResponseEntity<String> addNewMeter(){
        long newMeterId = ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);
        try{
            long savedMeterId = databaseRepository.addNewMeter(newMeterId, MeterType.RESIDENTIAL);
            return new ResponseEntity<>("Created new Meter with meter id : " + savedMeterId, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity("Error creating a new Meter",HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/get-meter/{meter_id}")
    public ResponseEntity getMeter(@PathVariable(name = "meter_id") long meter_id){
        try{
            MeterRow meter = databaseRepository.getMeter(meter_id);
            return new ResponseEntity<>(meter,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Meter with meter id: "+meter_id+" does not exist",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all-meters")
    public ResponseEntity<List<MeterRow>> getAllMeter(){
        return new ResponseEntity<>(databaseRepository.getAllMeters(),HttpStatus.OK);
    }

    @PostMapping("/new-consumer")
    public ResponseEntity addNewConsumer(@RequestBody ConsumerRow consumer){
        long newConsumerId = ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);

        ConsumerRow newConsumer = ConsumerRow.builder()
                .consumer_id(newConsumerId)
                .name(consumer.name())
                .email(consumer.email())
                .address(consumer.address())
                .city(consumer.city())
                .zipcode(consumer.zipcode())
                .build();

        try{
            long customerId = databaseRepository.addNewConsumer(newConsumer);
            return new ResponseEntity<>("Inserted new Customer with customer id : " + customerId, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error creating a new Customer",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all-consumers")
    public ResponseEntity<List<ConsumerRow>> getAllConsumers(){
        return new ResponseEntity<>(databaseRepository.getAllConsumer(),HttpStatus.OK);
    }

    @PostMapping("/assign-meter/{meter_id}/{consumer_id}")
    public ResponseEntity assignMeterToConsumer(@PathVariable(name = "meter_id") long meter_id, @PathVariable(name = "consumer_id") long consumer_id){
        try {
            MeterRow meter = databaseRepository.getMeter(meter_id);
            if(meter.in_use()){
                return new ResponseEntity("Meter with meter id:" +meter_id+ " already in use",HttpStatus.BAD_REQUEST);
            }


            boolean res = databaseRepository.assignMeterToConsumer(consumer_id,meter_id);
            if(res){
                return new ResponseEntity("Meter with meter id : " +meter_id+ " successfully assigned to consumer with consumer id : "+ consumer_id,HttpStatus.OK );
            }
            throw new RuntimeException();
        }
        catch (Exception e){
            return new ResponseEntity("Error assigning meter to consumer", HttpStatus.BAD_REQUEST);
        }


    }

}
