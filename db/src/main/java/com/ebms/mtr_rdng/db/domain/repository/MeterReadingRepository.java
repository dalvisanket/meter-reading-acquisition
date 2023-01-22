package com.ebms.mtr_rdng.db.domain.repository;

import com.ebms.mtr_rdng.db.domain.model.ConsumerRow;
import com.ebms.mtr_rdng.db.domain.model.MeterRow;


import com.ebms.mtr_rdng.db.enums.ConsumerIsActive;
import com.ebms.mtr_rdng.db.enums.ConsumerIsMeterAssigned;
import com.ebms.mtr_rdng.db.enums.MeterInUse;
import com.ebms.mtr_rdng.domain.model.MeterReading;
import com.ebms.mtr_rdng.domain.model.MeterType;
import com.ebms.util.exception.EntityNotFoundException;
import org.jooq.DSLContext;

import java.util.List;

import static com.ebms.mtr_rdng.db.tables.Consumer.CONSUMER;
import static com.ebms.mtr_rdng.db.tables.Meter.METER;
import static com.ebms.mtr_rdng.db.tables.ConsumerMeter.CONSUMER_METER;
import static com.ebms.mtr_rdng.db.tables.MeterReading.METER_READING;


public class MeterReadingRepository implements DatabaseRepository{

    private DSLContext context;

    public MeterReadingRepository(DSLContext context){
        this.context = context;
    }

    @Override
    public long addNewMeter(long meter_id, MeterType meterType) {

        context.insertInto(METER,METER.METER_ID,METER.METER_TYPE,METER.IN_USE)
                .values(meter_id,meterType.name(), MeterInUse.false_)
                .execute();

        return getMeter(meter_id).meter_id();
    }

    @Override
    public MeterRow getMeter(long meter_id) {
        List<MeterRow> meters = context.select()
                .from(METER)
                .where(METER.METER_ID.eq(meter_id))
                .fetchInto(MeterRow.class);
        if(meters.size() !=1){
            throw new EntityNotFoundException("No Meter found with meter id: " + meter_id);
        }
         return meters.get(0);
    }

    @Override
    public List<MeterRow> getAllMeters() {
        return context.select()
                .from(METER)
                .fetchInto(MeterRow.class);
    }

    @Override
    public boolean changeMeterStatus(long meter_id, boolean in_use) {
        context.update(METER).set(METER.IN_USE,in_use?MeterInUse.true_:MeterInUse.false_).where(METER.METER_ID.eq(meter_id)).execute();
        return true;
    }

    @Override
    public long addNewConsumer(ConsumerRow consumer) {

        context.insertInto(CONSUMER,CONSUMER.CONSUMER_ID,CONSUMER.NAME,CONSUMER.ADDRESS,CONSUMER.CITY,CONSUMER.ZIPCODE, CONSUMER.EMAIL, CONSUMER.IS_ACTIVE,CONSUMER.IS_METER_ASSIGNED)
                .values(consumer.consumer_id(),consumer.name(), consumer.address(),consumer.city(),consumer.zipcode(),consumer.email(), ConsumerIsActive.true_, ConsumerIsMeterAssigned.false_)
                .execute();

        return getConsumer(consumer.consumer_id()).consumer_id();
    }

    @Override
    public ConsumerRow getConsumer(long consumer_id){
        List<ConsumerRow> consumers = context.select()
                .from(CONSUMER)
                .where(CONSUMER.CONSUMER_ID.eq(consumer_id))
                .fetchInto(ConsumerRow.class);

        if(consumers.size() !=1){
            throw new EntityNotFoundException("No Consumer found with consumer id: " + consumer_id);
        }
        return consumers.get(0);
    }

    @Override
    public List<ConsumerRow> getAllConsumer() {
        List<ConsumerRow> consumerRows = context.select()
                .from(CONSUMER)
                .fetchInto(ConsumerRow.class);
        return consumerRows;
    }

    @Override
    public boolean assignMeterToConsumer(long consumer_id, long meter_id) {

        if(context.select().from(CONSUMER_METER).where(CONSUMER_METER.CONSUMER_ID.eq(consumer_id)).fetch().size() > 0){
            throw new RuntimeException();
        }

       if(getConsumer(consumer_id).is_active().equals(ConsumerIsActive.true_) && !getMeter(meter_id).in_use()){
           context.insertInto(CONSUMER_METER,CONSUMER_METER.CONSUMER_ID, CONSUMER_METER.METER_ID)
                   .values(consumer_id,meter_id)
                   .execute();

           changeMeterStatus(meter_id,true);
           changeIsMeterAssignedToConsumer(consumer_id,true);

           return true;
       }
        return false;
    }

    @Override
    public boolean changeIsMeterAssignedToConsumer(long consumer_id, boolean is_meter_assigned){
        context.update(CONSUMER)
                .set(CONSUMER.IS_METER_ASSIGNED, is_meter_assigned? ConsumerIsMeterAssigned.true_:ConsumerIsMeterAssigned.false_)
                .where(CONSUMER.CONSUMER_ID.eq(consumer_id))
                .execute();
        return true;
    }

    @Override
    public boolean saveMeterReading(MeterReading meterReading) {
        try {
            getMeter(meterReading.meter_id());
            context.insertInto(METER_READING,METER_READING.READING_ID, METER_READING.METER_ID, METER_READING.UNITS_CONSUMED,METER_READING.BILLING_CYCLE, METER_READING.REPORTING_DATE,METER_READING.REPORTING_EMP_ID)
                    .values(meterReading.reading_id(),meterReading.meter_id(),meterReading.unitReading(), meterReading.billingCycle().atEndOfMonth(),meterReading.reportingDate(),meterReading.reportingEmp())
                    .execute();
            return true;
        }
        catch (Exception e){
            throw new EntityNotFoundException("Could not save meter reading");
        }
    }


}
