package com.ebms.mtr_rdng.db.domain.repository;

import com.ebms.mtr_rdng.domain.model.MeterType;

public interface DatabaseRepository {

    long saveMeter(long meter_id, MeterType meterType);
}
