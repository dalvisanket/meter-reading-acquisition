package com.ebms.mtr_rdng.domain.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public interface MeterReading {
    long mId();
    double unitReading();
    YearMonth billingCycle();

    LocalDate reportingDate();

    MeterType meterType();

    String reportingEmp();
}
