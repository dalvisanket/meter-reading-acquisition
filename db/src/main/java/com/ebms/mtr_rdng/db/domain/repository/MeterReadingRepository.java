package com.ebms.mtr_rdng.db.domain.repository;

import com.ebms.mtr_rdng.db.domain.model.MeterRow;
import static com.ebms.mtr_rdng.db.tables.Meter.METER;

import com.ebms.mtr_rdng.domain.model.MeterType;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MeterReadingRepository implements DatabaseRepository{

    @Autowired
    private DSLContext context;

    public MeterReadingRepository(DSLContext context){
        this.context = context;
    }

    @Override
    public long saveMeter(long meter_id, MeterType meterType) {

        context.insertInto(METER,METER.METER_ID,METER.METER_TYPE)
                .values(meter_id,meterType.name())
                .execute();

        return getMeter(meter_id).mId();
    }

    @Override
    public MeterRow getMeter(long meter_id) {
        List<MeterRow> meters = context.select(METER.METER_ID,METER.METER_TYPE)
                .from(METER)
                .where(METER.METER_ID.eq(meter_id)).fetchInto(MeterRow.class);
         return meters.get(0);
    }
}
