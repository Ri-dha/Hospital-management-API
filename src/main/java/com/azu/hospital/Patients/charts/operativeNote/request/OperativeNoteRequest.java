package com.azu.hospital.Patients.charts.operativeNote.request;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperativeNoteRequest {

    private String operationName;
    private String operativeNote;


    public OperativeNoteRequest() {
    }

    public OperativeNoteRequest(String operationName, String operativeNote) {
        this.operationName = operationName;
        this.operativeNote = operativeNote;
    }
}
