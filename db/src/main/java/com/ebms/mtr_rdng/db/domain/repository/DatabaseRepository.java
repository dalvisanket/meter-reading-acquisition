package com.ebms.mtr_rdng.db.domain.repository;

import com.ebms.mtr_rdng.db.domain.model.ConsumerMeterReadingRow;
import com.ebms.mtr_rdng.db.domain.model.ConsumerMeterRow;
import com.ebms.mtr_rdng.db.domain.model.ConsumerRow;
import com.ebms.mtr_rdng.db.domain.model.MeterReadingRow;
import com.ebms.mtr_rdng.db.domain.model.MeterRow;
import com.ebms.mtr_rdng.domain.model.MeterReading;
import com.ebms.mtr_rdng.domain.model.MeterType;

import java.util.List;
import java.util.Optional;

public interface DatabaseRepository {

    long addNewMeter(long meter_id, MeterType meterType);

    MeterRow getMeter(long meter_id);

    List<MeterRow> getAllMeters();

    long addNewConsumer(ConsumerRow consumer);

    ConsumerRow getConsumer(long consumer_id);

    List<ConsumerRow> getAllConsumer();

    boolean assignMeterToConsumer(long consumer_id,long meter_id);

    public boolean changeIsMeterAssignedToConsumer(long consumer_id, long meter_id, boolean keep_association);

    public boolean saveMeterReading(MeterReading meterReading);

    ConsumerMeterRow getActiveMeterConsumerAssociation(Optional<Long> meter_id, Optional<Long> consumer_id);

    boolean saveConsumerMeterReading(long meter_id, long consumer_id, long reading_id);

    List<ConsumerMeterReadingRow> getAllReadingIds(long meter_id, long consumer_id);

    List<MeterReadingRow> getAllMeterReadings(long meter_id, long consumer_id);

/*    ConsumerDetails getConsumerDetails(long consumer_id);*/
}
