package com.ebms.mtr_rdng.controller;

import com.ebms.mtr_rdng.db.domain.repository.DatabaseRepository;
import com.ebms.mtr_rdng.domain.model.MeterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeterReadingController {

    @Autowired
    DatabaseRepository databaseRepository;

    @GetMapping("/meter")
    public long getMeter(){
        return databaseRepository.saveMeter((long)Math.random(), MeterType.RESIDENTIAL);
    }
}
