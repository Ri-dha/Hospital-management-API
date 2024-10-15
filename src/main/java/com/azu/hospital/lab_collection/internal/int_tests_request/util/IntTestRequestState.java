package com.azu.hospital.lab_collection.internal.int_tests_request.util;

public enum IntTestRequestState {
    CREATED("Created"),
    COMPLETED("Completed");


    private String state;

    IntTestRequestState(String state) {
        this.state = state;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


}
