package com.ebms.mtr_rdng.db.domain.repository;

import com.ebms.mtr_rdng.db.domain.model.ConsumerRow;
import com.ebms.mtr_rdng.db.domain.model.MeterRow;
import com.ebms.mtr_rdng.domain.model.MeterReading;
import com.ebms.mtr_rdng.domain.model.MeterType;

import java.util.List;

public interface DatabaseRepository {

    long addNewMeter(long meter_id, MeterType meterType);

    MeterRow getMeter(long meter_id);

    List<MeterRow> getAllMeters();

    boolean changeMeterStatus(long meter_id, boolean in_use);

    long addNewConsumer(ConsumerRow consumer);

    ConsumerRow getConsumer(long consumer_id);

    List<ConsumerRow> getAllConsumer();

    boolean assignMeterToConsumer(long consumer_id,long meter_id);

    public boolean changeIsMeterAssignedToConsumer(long consumer_id, boolean is_meter_assigned);

    public boolean saveMeterReading(MeterReading meterReading);

/*    ConsumerDetails getConsumerDetails(long consumer_id);*/
}
