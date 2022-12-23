package com.ebms.mtr_rdng.controller;

import com.ebms.mtr_rdng.db.domain.repository.DatabaseRepository;
import com.ebms.mtr_rdng.domain.model.MeterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
public class MeterReadingController {

    @Autowired
    DatabaseRepository databaseRepository;

    @PutMapping("/new-meter")
    public long addNewMeter(){
        long newMeterId = ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);
        return databaseRepository.saveMeter(newMeterId, MeterType.RESIDENTIAL);
    }
}
