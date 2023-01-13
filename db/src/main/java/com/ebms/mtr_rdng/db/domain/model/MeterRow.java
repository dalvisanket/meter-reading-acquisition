package com.ebms.mtr_rdng.db.domain.model;

import com.ebms.util.builder.Builder;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = MeterRow.MeterRowBuilder.class)
public class MeterRow {

    private long meter_id;

    private String meterType;

    private String in_use;

    private MeterRow(MeterRowBuilder meterBuilder){
        this.meter_id = meterBuilder.meter_id;
        this.meterType = meterBuilder.meterType;
        this.in_use = meterBuilder.in_use;
    }

    public MeterRow(){}
    @JsonGetter
    public long meter_id(){
        return this.meter_id;
    }

    @JsonGetter
    public String meterType(){
        return this.meterType;
    }

    @JsonGetter
    public boolean in_use(){
        if(this.in_use.equals("true")) return true;
        return false;
    }

    public static MeterRowBuilder builder(){
        return new MeterRowBuilder();
    }

    @JsonPOJOBuilder
    public static final class MeterRowBuilder implements Builder<MeterRow>{

        private long meter_id;
        private String meterType;
        private String in_use;

        @JsonSetter
        public MeterRowBuilder meter_id(long meter_id){
            this.meter_id = meter_id;
            return this;
        }

        @JsonSetter
        public MeterRowBuilder meterType(String meterType){
            this.meterType = meterType;
            return this;
        }

        @JsonSetter
        public  MeterRowBuilder in_use(String in_use){
            this.in_use = in_use;
            return this;
        }

        @Override
        public MeterRow build() {
            return new MeterRow(this);
        }
    }
}
