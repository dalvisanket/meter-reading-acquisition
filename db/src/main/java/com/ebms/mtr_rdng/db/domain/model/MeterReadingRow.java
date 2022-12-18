package com.ebms.mtr_rdng.db.domain.model;

import com.ebms.util.builder.Builder;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDate;

@JsonDeserialize( builder = MeterReadingRow.MeterReadingRowBuilder.class)
public class MeterReadingRow {

    private long reading_id;
    private long meter_id;
    private int units_consumed;
    private LocalDate billing_cycle;
    private LocalDate reporting_date;
    private long reporting_emp_id;

    private MeterReadingRow(MeterReadingRowBuilder builder){
        this.reading_id = builder.reading_id;
        this.meter_id = builder.meter_id;
        this.units_consumed = builder.units_consumed;
        this.billing_cycle = builder.billing_cycle;
        this.reporting_date = builder.reporting_date;
        this.reporting_emp_id = builder.reporting_emp_id;
    }


    @JsonGetter
    public long reading_id(){
        return this.reading_id;
    }


    @JsonGetter
    public long meter_id(){
        return this.meter_id;
    }

    @JsonGetter
    public int units_consumed(){
        return this.units_consumed;
    }

    @JsonGetter
    public LocalDate billing_cycle() { return this.billing_cycle; }

    @JsonGetter
    public LocalDate reporting_date(){
        return this.reporting_date;
    }

    @JsonGetter
    public long reporting_emp_id(){
        return this.reporting_emp_id;
    }

    public MeterReadingRowBuilder builder(){
        return new MeterReadingRowBuilder();
    }


    @JsonPOJOBuilder
    public static final class MeterReadingRowBuilder implements Builder<MeterReadingRow>{

        private long reading_id;
        private long meter_id;
        private int units_consumed;
        private LocalDate billing_cycle;
        private LocalDate reporting_date;
        private long reporting_emp_id;

        @JsonSetter
        public MeterReadingRowBuilder reading_id( long reading_id){
            this.reading_id =reading_id;
            return this;
        }

        @JsonSetter
        public MeterReadingRowBuilder meter_id( long meter_id){
            this.meter_id =meter_id;
            return this;
        }

        @JsonSetter
        public MeterReadingRowBuilder units_consumed( int units_consumed){
            this.units_consumed =units_consumed;
            return this;
        }

        @JsonSetter
        public MeterReadingRowBuilder billing_cycle( LocalDate billing_cycle){
            this.billing_cycle =billing_cycle;
            return this;
        }

        @JsonSetter
        public MeterReadingRowBuilder reporting_date( LocalDate reporting_date){
            this.reporting_date =reporting_date;
            return this;
        }

        @JsonSetter
        public MeterReadingRowBuilder reporting_emp_id( long reporting_emp_id){
            this.reporting_emp_id =reporting_emp_id;
            return this;
        }


        @Override
        public MeterReadingRow build() {
            return new MeterReadingRow(this);
        }
    }
}
