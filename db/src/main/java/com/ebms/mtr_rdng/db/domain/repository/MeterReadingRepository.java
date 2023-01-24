package com.ebms.mtr_rdng.db.domain.repository;

import com.ebms.mtr_rdng.db.domain.model.ConsumerMeterReadingRow;
import com.ebms.mtr_rdng.db.domain.model.ConsumerMeterRow;
import com.ebms.mtr_rdng.db.domain.model.ConsumerRow;
import com.ebms.mtr_rdng.db.domain.model.MeterReadingRow;
import com.ebms.mtr_rdng.db.domain.model.MeterRow;


import com.ebms.mtr_rdng.db.enums.ConsumerIsMeterAssigned;
import com.ebms.mtr_rdng.db.enums.ConsumerMeterIsActive;
import com.ebms.mtr_rdng.db.enums.MeterInUse;
import com.ebms.mtr_rdng.domain.model.MeterReading;
import com.ebms.mtr_rdng.domain.model.MeterType;
import com.ebms.util.exception.EntityNotFoundException;
import org.jooq.DSLContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ebms.mtr_rdng.db.tables.Consumer.CONSUMER;
import static com.ebms.mtr_rdng.db.tables.Meter.METER;
import static com.ebms.mtr_rdng.db.tables.ConsumerMeter.CONSUMER_METER;
import static com.ebms.mtr_rdng.db.tables.MeterReading.METER_READING;
import static com.ebms.mtr_rdng.db.tables.ConsumerMeterReading.CONSUMER_METER_READING;


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
    public long addNewConsumer(ConsumerRow consumer) {

        context.insertInto(CONSUMER,CONSUMER.CONSUMER_ID,CONSUMER.NAME,CONSUMER.ADDRESS,CONSUMER.CITY,CONSUMER.ZIPCODE, CONSUMER.EMAIL, CONSUMER.IS_METER_ASSIGNED)
                .values(consumer.consumer_id(),consumer.name(), consumer.address(),consumer.city(),consumer.zipcode(),consumer.email(), ConsumerIsMeterAssigned.false_)
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

        if(getConsumer(consumer_id).is_meter_assigned().equals(ConsumerIsMeterAssigned.false_.getLiteral()) && !getMeter(meter_id).in_use()){
            context.insertInto(CONSUMER_METER,CONSUMER_METER.CONSUMER_ID, CONSUMER_METER.METER_ID, CONSUMER_METER.IS_ACTIVE)
                    .values(consumer_id,meter_id,ConsumerMeterIsActive.true_)
                    .execute();

            changeIsMeterAssignedToConsumer(consumer_id, meter_id,true);

            return true;
        }
        return false;
    }

    @Override
    public boolean changeIsMeterAssignedToConsumer(long consumer_id, long meter_id, boolean keep_association){
        context.update(CONSUMER)
                .set(CONSUMER.IS_METER_ASSIGNED, keep_association? ConsumerIsMeterAssigned.true_:ConsumerIsMeterAssigned.false_)
                .where(CONSUMER.CONSUMER_ID.eq(consumer_id))
                .execute();

        context.update(METER)
                .set(METER.IN_USE,keep_association?MeterInUse.true_:MeterInUse.false_)
                .where(METER.METER_ID.eq(meter_id))
                .execute();

        context.update(CONSUMER_METER).set(CONSUMER_METER.IS_ACTIVE,keep_association? ConsumerMeterIsActive.true_:ConsumerMeterIsActive.false_)
                .where(CONSUMER_METER.CONSUMER_ID.eq(consumer_id))
                .and(CONSUMER_METER.METER_ID.eq(meter_id))
                .execute();
        return true;
    }

    @Override
    public boolean saveMeterReading(MeterReading meterReading) {
        try {
            MeterRow meter = getMeter(meterReading.meter_id());
            if(meter.in_use()) {
                context.insertInto(METER_READING, METER_READING.READING_ID, METER_READING.METER_ID, METER_READING.UNITS_CONSUMED, METER_READING.BILLING_CYCLE, METER_READING.REPORTING_DATE, METER_READING.REPORTING_EMP_ID)
                        .values(meterReading.reading_id(), meterReading.meter_id(), meterReading.unitReading(), meterReading.billingCycle().atEndOfMonth(), meterReading.reportingDate(), meterReading.reportingEmp())
                        .execute();

                ConsumerMeterRow consumerMeter = getActiveMeterConsumerAssociation(Optional.of(meterReading.meter_id()),null);
                saveConsumerMeterReading(consumerMeter.meter_id(),consumerMeter.consumer_id(),meterReading.reading_id());
                return true;
            }
            else{
                throw new RuntimeException();
            }

        }
        catch (Exception e){
            throw new EntityNotFoundException("Could not save meter reading");
        }
    }

    @Override
    public ConsumerMeterRow getActiveMeterConsumerAssociation(Optional<Long> meter_id, Optional<Long> consumer_id) {

        if(meter_id.isEmpty() && consumer_id.isEmpty()){
            throw new EntityNotFoundException("No meter id and consumer id provided");
        }

        List<ConsumerMeterRow> activeConsumerMeters;

        if(meter_id == null){

            activeConsumerMeters= context.select()
                    .from(CONSUMER_METER)
                    .where(CONSUMER_METER.CONSUMER_ID.eq(consumer_id.get()))
                    .and(CONSUMER_METER.IS_ACTIVE.eq(ConsumerMeterIsActive.true_))
                    .fetchInto(ConsumerMeterRow.class);
        }
        else{
            activeConsumerMeters = context.select()
                    .from(CONSUMER_METER)
                    .where(CONSUMER_METER.METER_ID.eq(meter_id.get()))
                    .and(CONSUMER_METER.IS_ACTIVE.eq(ConsumerMeterIsActive.true_))
                    .fetchInto(ConsumerMeterRow.class);
        }

        if(activeConsumerMeters.size() != 1){
            throw new EntityNotFoundException("Consumer Meter Association not found");
        }

        return activeConsumerMeters.get(0);
    }

    @Override
    public boolean saveConsumerMeterReading(long meter_id, long consumer_id, long reading_id) {
        context.insertInto(CONSUMER_METER_READING,CONSUMER_METER_READING.METER_ID, CONSUMER_METER_READING.CONSUMER_ID,CONSUMER_METER_READING.READING_ID)
                .values(meter_id,consumer_id,reading_id)
                .execute();
        return true;
    }

    @Override
    public List<ConsumerMeterReadingRow> getAllReadingIds(long meter_id, long consumer_id) {

        List<ConsumerMeterReadingRow> consumerMeterReadingRows = context.select()
                .from(CONSUMER_METER_READING)
                .where(CONSUMER_METER_READING.METER_ID.eq(meter_id))
                .and(CONSUMER_METER_READING.CONSUMER_ID.eq(consumer_id))
                .fetchInto(ConsumerMeterReadingRow.class);

        return consumerMeterReadingRows;
    }

    @Override
    public List<MeterReadingRow> getAllMeterReadings(long meter_id, long consumer_id) {

        List<ConsumerMeterReadingRow> consumerMeterReadingRows = getAllReadingIds(meter_id,consumer_id);

        List<MeterReadingRow> meterReadingRows = consumerMeterReadingRows.stream()
                .map(row -> {
                    List<MeterReadingRow> rows = context.select()
                            .from(METER_READING)
                            .where(METER_READING.METER_ID.eq(meter_id))
                            .and(METER_READING.READING_ID.eq(row.reading_id()))
                            .fetchInto(MeterReadingRow.class);
                    if(rows.size() != 1){
                        throw new EntityNotFoundException("Not able to find meter reading");
                    }
                    return rows.get(0);
                }).collect(Collectors.toList());

        return meterReadingRows;
    }


}
