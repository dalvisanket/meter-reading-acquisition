package com.ebms.mtr_rdng.db.domain.model;

import com.ebms.util.builder.Builder;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = EmployeeRow.EmployeeRowBuilder.class)
public class EmployeeRow {

    private int emp_id;
    private String name;
    private String designation;

    private EmployeeRow(EmployeeRowBuilder employeeRowBuilder){
        this.emp_id = employeeRowBuilder.emp_id;
        this.name = employeeRowBuilder.name;
        this.designation = employeeRowBuilder.designation;
    }


    @JsonGetter
    public int empi_id(){
        return this.emp_id;
    }

    @JsonGetter
    public String name(){
        return this.name;
    }

    @JsonGetter
    public String designation(){
        return this.designation;
    }

    public EmployeeRowBuilder builder(){
        return new EmployeeRowBuilder();
    }

    @JsonPOJOBuilder
    public static final class EmployeeRowBuilder implements Builder<EmployeeRow>{

        private int emp_id;
        private String name;
        private String designation;

        @JsonSetter
        public EmployeeRowBuilder emp_id(int emp_id){
            this.emp_id =emp_id;
            return this;
        }

        @JsonSetter
        public EmployeeRowBuilder name(String name){
            this.name = name;
            return this;
        }

        @JsonSetter
        public EmployeeRowBuilder designation(String designation){
            this.designation = designation;
            return this;
        }

        public EmployeeRow build(){
            return new EmployeeRow(this);
        }
    }


}
