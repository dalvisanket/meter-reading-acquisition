package com.ebms.mtr_rdng.db.domain.model;

import com.ebms.util.builder.Builder;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = ConsumerMeterReadingRow.ConsumerMeterReadingRowBuilder.class)
public class ConsumerMeterReadingRow {

    private long meter_id;

    private long consumer_id;

    private long reading_id;

    private ConsumerMeterReadingRow(ConsumerMeterReadingRowBuilder builder){
        this.meter_id = builder.meter_id;
        this.consumer_id = builder.consumer_id;
        this.reading_id = builder.reading_id;
    }
    public ConsumerMeterReadingRow(){}

    @JsonGetter
    public long meter_id(){
        return this.meter_id;
    }

    @JsonGetter
    public long consumer_id(){
        return this.consumer_id;
    }

    @JsonGetter
    public long reading_id(){
        return this.reading_id;
    }

    @JsonPOJOBuilder
    public static final class ConsumerMeterReadingRowBuilder implements Builder<ConsumerMeterReadingRow>{

        private long meter_id;

        private long consumer_id;

        private long reading_id;

        @JsonSetter
        public ConsumerMeterReadingRowBuilder meter_id(long meter_id){
            this.meter_id = meter_id;
            return this;
        }

        @JsonSetter
        public ConsumerMeterReadingRowBuilder consumer_id(long consumer_id){
            this.consumer_id = consumer_id;
            return this;
        }


        @JsonSetter
        public ConsumerMeterReadingRowBuilder reading_id(long reading_id){
            this.reading_id = reading_id;
            return this;
        }

        @Override
        public ConsumerMeterReadingRow build() {
            return new ConsumerMeterReadingRow(this);
        }
    }
}
