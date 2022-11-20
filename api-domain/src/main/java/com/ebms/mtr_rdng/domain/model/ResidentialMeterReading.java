package com.ebms.mtr_rdng.domain.model;

import com.ebms.util.builder.Builder;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.apache.kafka.common.protocol.types.Field;

import java.time.YearMonth;

@JsonDeserialize( builder = ResidentialMeterReading.ResidentialMeterReadingBuilder.class)
public class ResidentialMeterReading implements MeterReading{

    private final long mId;
    private final double unitReading;
    private final YearMonth readingForYearMonth;
    private final MeterType meterType;
    private final String reportingEmp;

    private ResidentialMeterReading(ResidentialMeterReadingBuilder builder){
        this.mId = builder.mId;
        this.unitReading = builder.unitReading;
        this.readingForYearMonth = builder.readingForYearMonth;
        this.meterType = builder.meterType;
        this.reportingEmp = builder.reportingEmp;
    }
    @Override
    @JsonGetter
    public long mId() {
        return this.mId;
    }

    @Override
    @JsonGetter
    public double unitReading() {
        return this.unitReading;
    }

    @Override
    @JsonGetter
    public YearMonth readingForYearMonth() {
        return this.readingForYearMonth;
    }

    @Override
    @JsonGetter
    public MeterType meterType() {
        return this.meterType;
    }

    @Override
    @JsonGetter
    public String reportingEmp() {
        return this.reportingEmp;
    }

    public static ResidentialMeterReadingBuilder builder(){
        return new ResidentialMeterReadingBuilder();
    }

    @JsonPOJOBuilder
    public static final class ResidentialMeterReadingBuilder implements Builder<ResidentialMeterReading>{

        private long mId;
        private double unitReading;
        private YearMonth readingForYearMonth;
        private MeterType meterType;
        private String reportingEmp;

        @JsonSetter
        public ResidentialMeterReadingBuilder mId(long mId){
            this.mId = mId;
            return this;
        }

        @JsonSetter
        public ResidentialMeterReadingBuilder unitReading(double unitReading){
            this.unitReading = unitReading;
            return this;
        }

        @JsonSetter
        public ResidentialMeterReadingBuilder readingForYearMonth(YearMonth readingForYearMonth){
            this.readingForYearMonth = readingForYearMonth;
            return this;
        }

        @JsonSetter
        public ResidentialMeterReadingBuilder meterType(MeterType meterType){
            this.meterType = meterType;
            return this;
        }

        @JsonSetter
        public ResidentialMeterReadingBuilder reportingEmp(String reportingEmp){
            this.reportingEmp = reportingEmp;
            return this;
        }

        @Override
        public ResidentialMeterReading build() {
            return new ResidentialMeterReading(this);
        }
    }
}
