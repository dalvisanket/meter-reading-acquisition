package com.ebms.mtr_rdng.db.domain.repository;

import com.ebms.mtr_rdng.db.domain.model.ConsumerRow;
import com.ebms.mtr_rdng.db.domain.model.MeterRow;
import com.ebms.mtr_rdng.domain.model.MeterType;

public interface DatabaseRepository {

    long addNewMeter(long meter_id, MeterType meterType);

    MeterRow getMeter(long meter_id);

    boolean changeMeterStatus(long meter_id, boolean in_use);

    long addNewConsumer(ConsumerRow consumer);

    ConsumerRow getConsumer(long consumer_id);

    boolean assignMeterToConsumer(long consumer_id,long meter_id);

/*    ConsumerDetails getConsumerDetails(long consumer_id);*/
}
