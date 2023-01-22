package com.ebms.mtr_rdng.domain.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDate;
import java.time.YearMonth;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public interface MeterReading {

    long reading_id();
    long meter_id();
    int unitReading();
    YearMonth billingCycle();

    LocalDate reportingDate();

    MeterType meterType();

    long reportingEmp();

}
