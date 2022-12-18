package com.ebms.mtr_rdng.db.domain.model;

import com.ebms.util.builder.Builder;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;


@JsonDeserialize(builder = MeterRow.MeterRowBuilder.class)
public class MeterRow {
    private long mId;
    private String meterType;

    private MeterRow(MeterRowBuilder meterBuilder){
        this.mId = meterBuilder.mId;
        this.meterType = meterBuilder.meterType;
    }

    @JsonGetter
    public long mId(){
        return this.mId;
    }

    @JsonGetter
    public String meterType(){
        return this.meterType;
    }

    public static MeterRowBuilder builder(){
        return new MeterRowBuilder();
    }

    @JsonPOJOBuilder
    public static final class MeterRowBuilder implements Builder<MeterRow>{

        private long mId;
        private String meterType;


        @JsonSetter
        public MeterRowBuilder mId(long mId){
            this.mId = mId;
            return this;
        }

        @JsonSetter
        public MeterRowBuilder meterType(String meterType){
            this.meterType = meterType;
            return this;
        }

        @Override
        public MeterRow build() {
            return new MeterRow(this);
        }
    }
}
