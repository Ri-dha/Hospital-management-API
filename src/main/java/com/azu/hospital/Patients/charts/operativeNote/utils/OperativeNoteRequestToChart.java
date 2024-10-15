package com.azu.hospital.Patients.charts.operativeNote.utils;

import com.azu.hospital.Patients.charts.operativeNote.entity.OperativeNote;
import com.azu.hospital.Patients.charts.operativeNote.request.OperativeNoteRequest;

public class OperativeNoteRequestToChart {

    public static OperativeNote getOperativeNote(OperativeNoteRequest request){
        return new OperativeNote.Builder()
                .withOperationName(request.getOperationName())
                .withOperativeNote(request.getOperativeNote())
                .build();
    }
}
