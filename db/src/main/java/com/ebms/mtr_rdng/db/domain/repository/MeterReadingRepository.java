package com.ebms.mtr_rdng.db.domain.repository;

import com.ebms.mtr_rdng.db.tables.Meter;
import com.ebms.mtr_rdng.domain.model.MeterType;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MeterReadingRepository implements DatabaseRepository{

    @Autowired
    private DSLContext context;

    public MeterReadingRepository(DSLContext context){
        this.context = context;
    }

    @Override
    public long saveMeter(long meter_id, MeterType meterType) {

        context.insertInto(Meter.METER,Meter.METER.METER_ID,Meter.METER.METER_TYPE)
                .values(meter_id,meterType.name())
                .execute();

        return meter_id;
    }
}
