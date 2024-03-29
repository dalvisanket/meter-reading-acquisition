package com.ebms.mtr_rdng.db.domain.model;


import com.ebms.util.builder.Builder;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = ConsumerMeterRow.ConsumerMeterRowBuilder.class)
public class ConsumerMeterRow {

    private long meter_id;
    private long consumer_id;
    private String is_active;

    private ConsumerMeterRow(ConsumerMeterRowBuilder builder){
        this.meter_id = builder.meter_id;
        this.consumer_id = builder.consumer_id;
        this.is_active = builder.is_active;
    }

    public ConsumerMeterRow(){}

    @JsonGetter
    public long meter_id(){
        return this.meter_id;
    }

    @JsonGetter
    public long consumer_id(){
        return this.consumer_id;
    }

    @JsonGetter String is_active(){return this.is_active;}


    public ConsumerMeterRowBuilder builder(){
        return new ConsumerMeterRowBuilder();
    }

    @JsonPOJOBuilder
    public static final class ConsumerMeterRowBuilder implements Builder<ConsumerMeterRow>{

        private long meter_id;
        private long consumer_id;
        private String is_active;

        @JsonSetter
        public ConsumerMeterRowBuilder meter_id(long meter_id){
            this.meter_id = meter_id;
            return this;
        }

        @JsonSetter
        public ConsumerMeterRowBuilder consumer_id(long consumer_id){
            this.consumer_id = consumer_id;
            return this;
        }


        @JsonSetter
        public ConsumerMeterRowBuilder is_active(String is_active){
            this.is_active = is_active;
            return this;
        }

        @Override
        public ConsumerMeterRow build() {
            return new ConsumerMeterRow(this);
        }
    }
}
