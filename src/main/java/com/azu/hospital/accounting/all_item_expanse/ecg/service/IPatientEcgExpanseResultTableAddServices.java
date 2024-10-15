package com.azu.hospital.accounting.all_item_expanse.ecg.service;

import com.azu.hospital.ecg.internal.entity.Ecg;
import com.azu.hospital.ecg.internal.services.EcgService;

public interface IPatientEcgExpanseResultTableAddServices {

    void addPatientEcgExpanseResultTable(Long patientId , Ecg ecg);

}
