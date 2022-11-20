package com.ebms.mtr_rdng.domain.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.YearMonth;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public interface MeterReading {
    long mId();
    double unitReading();
    YearMonth readingForYearMonth();

    MeterType meterType();

    String reportingEmp();
}
