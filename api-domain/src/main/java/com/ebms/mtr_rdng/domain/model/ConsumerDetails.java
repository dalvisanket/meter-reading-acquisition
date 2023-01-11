package com.ebms.mtr_rdng.domain.model;

import com.ebms.util.builder.Builder;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;


@JsonDeserialize(builder = ConsumerDetails.ConsumerDetailsBuilder.class)
public class ConsumerDetails {
    private long consumer_id;
    private String name;
    private String email;
    private String address;
    private String city;
    private int zipcode;
    private String is_consumer_active;
    private long meter_id;

    private String meterType;
    private String is_meter_active;

    private ConsumerDetails(ConsumerDetailsBuilder builder){
        this.consumer_id = builder.consumer_id;
        this.name = builder.name;
        this.email = builder.email;
        this.address = builder.address;
        this.city = builder.city;
        this.zipcode = builder.zipcode;
        this.is_consumer_active =builder.is_consumer_active;
        this.meter_id = builder.meter_id;
        this.meterType = builder.meterType;
        this.is_meter_active = builder.is_meter_active;
    }

    @JsonGetter
    public long consumer_id(){
        return this.consumer_id;
    }

    @JsonGetter
    public String name(){
        return this.name;
    }

    @JsonGetter
    public String email(){
        return this.email;
    }

    @JsonGetter
    public String address(){
        return this.address;
    }

    @JsonGetter
    public String city(){
        return this.city;
    }

    @JsonGetter
    public int zipcode(){
        return this.zipcode;
    }

    @JsonGetter
    public boolean is_consumer_active(){
        if(this.is_consumer_active.equals("yes")) return true;
        return false;
    }

    @JsonGetter
    public long meter_id(){
        return this.meter_id;
    }

    @JsonGetter
    public String meterType(){
        return this.meterType;
    }

    @JsonGetter
    public boolean is_meter_active(){
        if(this.is_meter_active.equals("yes")) return true;
        return false;
    }

    public static ConsumerDetailsBuilder builder(){
        return new ConsumerDetailsBuilder();
    }

    @JsonPOJOBuilder
    public static final class ConsumerDetailsBuilder implements Builder<ConsumerDetails>{

        private long consumer_id;
        private String name;
        private String email;
        private String address;
        private String city;
        private int zipcode;
        private String is_consumer_active;
        private long meter_id;
        private String meterType;
        private String is_meter_active;

        @JsonSetter
        public ConsumerDetailsBuilder consumer_id(long consumer_id){
            this.consumer_id = consumer_id;
            return this;
        }

        @JsonSetter
        public ConsumerDetailsBuilder name(String name){
            this.name = name;
            return this;
        }

        @JsonSetter
        public ConsumerDetailsBuilder email(String email){
            this.email = email;
            return this;
        }

        @JsonSetter
        public ConsumerDetailsBuilder address(String address){
            this.address = address;
            return this;
        }

        @JsonSetter
        public ConsumerDetailsBuilder city(String city){
            this.city = city;
            return this;
        }

        @JsonSetter
        public ConsumerDetailsBuilder zipcode(int zipcode){
            this.zipcode = zipcode;
            return this;
        }

        @JsonSetter
        public ConsumerDetailsBuilder is_consumer_active(String is_consumer_active){
            this.is_consumer_active = is_consumer_active;
            return this;
        }

        @JsonSetter
        public ConsumerDetailsBuilder meter_id(long meter_id){
            this.meter_id = meter_id;
            return this;
        }

        @JsonSetter
        public ConsumerDetailsBuilder meterType(String meterType){
            this.meterType= meterType;
            return this;
        }

        @JsonSetter
        public ConsumerDetailsBuilder is_meter_active(String is_meter_active){
            this.is_meter_active = is_meter_active;
            return this;
        }

        @Override
        public ConsumerDetails build() {
            return new ConsumerDetails(this);
        }
    }
}
