package com.ebms.mtr_rdng.db.domain.model;

import com.ebms.util.builder.Builder;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

@JsonDeserialize(builder = ConsumerRow.ConsumerRowBuilder.class)
public class ConsumerRow {

    private long consumer_id;
    private String name;
    private String address;
    private String city;
    private int zipcode;


    private ConsumerRow(ConsumerRowBuilder consumerRowBuilder){
        this.consumer_id = consumerRowBuilder.consumer_id;
        this.name = consumerRowBuilder.name;
        this.city = consumerRowBuilder.city;
        this.address = consumerRowBuilder.address;
        this.zipcode = consumerRowBuilder.zipcode;
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
    public ConsumerRowBuilder builder(){
        return new ConsumerRowBuilder();
    }


    @JsonPOJOBuilder
    public static final class ConsumerRowBuilder implements Builder<ConsumerRow>{

        private long consumer_id;
        private String name;
        private String address;
        private String city;
        private int zipcode;

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

        @Override
        public ConsumerRow build() {
            return new ConsumerRow(this);
        }
    }

}
