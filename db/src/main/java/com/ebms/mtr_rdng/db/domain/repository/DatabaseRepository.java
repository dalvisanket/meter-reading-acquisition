package com.ebms.mtr_rdng.db.domain.repository;

import com.ebms.mtr_rdng.db.domain.model.MeterRow;
import com.ebms.mtr_rdng.domain.model.MeterType;

public interface DatabaseRepository {

    long saveMeter(long meter_id, MeterType meterType);

    MeterRow getMeter(long meter_id);
}
