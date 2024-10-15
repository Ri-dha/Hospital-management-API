package com.azu.hospital.utils.enums.socketEnums;

public enum BroadcastingBodyMessages {

    ShowOrder("Click to show order");


    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    BroadcastingBodyMessages(String message) {
        this.message = message;
    }
}
