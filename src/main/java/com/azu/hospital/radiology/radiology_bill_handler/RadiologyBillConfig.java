package com.azu.hospital.radiology.radiology_bill_handler;

import com.azu.hospital.radiology.radiology_bill_handler.dao.RadiologyBillDao;
import com.azu.hospital.radiology.radiology_bill_handler.entity.RadiologyBillHandler;
import com.azu.hospital.radiology.radiology_bill_handler.requests.RadiologyCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class RadiologyBillConfig implements ApplicationRunner {

    private final RadiologyBillDao radiologyBillDao;

    @Autowired
    public RadiologyBillConfig(@Qualifier("RadiologyBillJpa") RadiologyBillDao radiologyBillDao) {
        this.radiologyBillDao = radiologyBillDao;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {




    }
}
