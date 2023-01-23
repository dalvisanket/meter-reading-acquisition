package com.ebms.mtr_rdng.db.domain.model;

import com.ebms.util.builder.Builder;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@JsonDeserialize(builder = ConsumerRow.ConsumerRowBuilder.class)
public class ConsumerRow {

    private long consumer_id;
    private String name;
    private String address;
    private String city;
    private int zipcode;
    private String email;
    private String is_meter_assigned;


    private ConsumerRow(ConsumerRowBuilder builder){
        this.consumer_id = builder.consumer_id;
        this.name = builder.name;
        this.city = builder.city;
        this.address = builder.address;
        this.zipcode = builder.zipcode;
        this.email = builder.email;
        this.is_meter_assigned = builder.is_meter_assigned;
    }

    public ConsumerRow(){}

    @JsonGetter
    public long consumer_id(){
        return this.consumer_id;
    }

    @JsonGetter
    public String name(){
        return this.name;
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
    public String email(){ return this.email; }

    @JsonGetter
    public String is_meter_assigned(){
        return is_meter_assigned;
    }

    public static ConsumerRowBuilder builder(){
        return new ConsumerRowBuilder();
    }


    @JsonPOJOBuilder
    public static final class ConsumerRowBuilder implements Builder<ConsumerRow>{

        private long consumer_id;
        private String name;
        private String address;
        private String city;
        private int zipcode;
        private String email;
        private String is_meter_assigned;

        @JsonSetter
        public ConsumerRowBuilder consumer_id(long consumer_id){
            this.consumer_id = consumer_id;
            return this;
        }

        @JsonSetter
        public ConsumerRowBuilder name(String name){
            this.name = name;
            return this;
        }

        @JsonSetter
        public ConsumerRowBuilder address(String address){
            this.address = address;
            return this;
        }

        @JsonSetter
        public ConsumerRowBuilder city(String city){
            this.city = city;
            return this;
        }

        @JsonSetter
        public ConsumerRowBuilder zipcode(int zipcode){
            this.zipcode = zipcode;
            return this;
        }

        @JsonSetter
        public ConsumerRowBuilder email(String email){
            this.email = email;
            return this;
        }

        @JsonSetter
        public ConsumerRowBuilder is_meter_assigned(String is_meter_assigned){
            this.is_meter_assigned = is_meter_assigned;
            return this;
        }

        @Override
        public ConsumerRow build() {
            return new ConsumerRow(this);
        }
    }

}
