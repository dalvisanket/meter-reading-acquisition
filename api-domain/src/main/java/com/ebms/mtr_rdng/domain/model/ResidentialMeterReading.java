package com.ebms.mtr_rdng.domain.model;

import com.ebms.util.builder.Builder;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDate;
import java.time.YearMonth;

@JsonDeserialize( builder = ResidentialMeterReading.ResidentialMeterReadingBuilder.class)
public class ResidentialMeterReading implements MeterReading{

    private final long reading_id;
    private final long meter_id;
    private final int unitReading;
    private final YearMonth billingCycle;
    private final LocalDate reportingDate;
    private final MeterType meterType;
    private final long reportingEmp;

    private ResidentialMeterReading(ResidentialMeterReadingBuilder builder){
        this.reading_id = builder.reading_id;
        this.meter_id = builder.meter_id;
        this.unitReading = builder.unitReading;
        this.billingCycle = builder.billingCycle;
        this.reportingDate = builder.reportingDate;
        this.meterType = builder.meterType;
        this.reportingEmp = builder.reportingEmp;
    }

    @Override
    @JsonGetter
    public long reading_id() {
        return this.reading_id;
    }

    @Override
    @JsonGetter
    public long meter_id() {
        return this.meter_id;
    }

    @Override
    @JsonGetter
    public int unitReading() {
        return this.unitReading;
    }

    @Override
    @JsonGetter
    public YearMonth billingCycle() {
        return this.billingCycle;
    }


    @Override
    @JsonGetter
    public LocalDate reportingDate() {
        return this.reportingDate;
    }

    @Override
    @JsonGetter
    public MeterType meterType() {
        return this.meterType;
    }

    @Override
    @JsonGetter
    public long reportingEmp() {
        return this.reportingEmp;
    }

    public static ResidentialMeterReadingBuilder builder(){
        return new ResidentialMeterReadingBuilder();
    }

    public ResidentialMeterReadingBuilder toBuilder(){
        return new ResidentialMeterReadingBuilder()
                .reading_id(this.reading_id)
                .meter_id(this.meter_id)
                .unitReading(this.unitReading)
                .billingCycle(this.billingCycle)
                .reportingDate(this.reportingDate)
                .meterType(this.meterType)
                .reportingEmp(this.reportingEmp);
    }

    @JsonPOJOBuilder
    public static final class ResidentialMeterReadingBuilder implements Builder<ResidentialMeterReading>{

        private long reading_id;
        private long meter_id;
        private int unitReading;
        private YearMonth billingCycle;
        private LocalDate reportingDate;
        private MeterType meterType;
        private long reportingEmp;


        @JsonSetter
        public ResidentialMeterReadingBuilder reading_id(long reading_id){
            this.reading_id = reading_id;
            return this;
        }

        @JsonSetter
        public ResidentialMeterReadingBuilder meter_id(long meter_id){
            this.meter_id = meter_id;
            return this;
        }

        @JsonSetter
        public ResidentialMeterReadingBuilder unitReading(int unitReading){
            this.unitReading = unitReading;
            return this;
        }

        @JsonSetter
        public ResidentialMeterReadingBuilder billingCycle(YearMonth billingCycle){
            this.billingCycle = billingCycle;
            return this;
        }

        @JsonSetter
        public ResidentialMeterReadingBuilder reportingDate(LocalDate reportingDate){
            this.reportingDate = reportingDate;
            return this;
        }

        @JsonSetter
        public ResidentialMeterReadingBuilder meterType(MeterType meterType){
            this.meterType = meterType;
            return this;
        }

        @JsonSetter
        public ResidentialMeterReadingBuilder reportingEmp(long reportingEmp){
            this.reportingEmp = reportingEmp;
            return this;
        }

        @Override
        public ResidentialMeterReading build() {
            return new ResidentialMeterReading(this);
        }
    }
}
