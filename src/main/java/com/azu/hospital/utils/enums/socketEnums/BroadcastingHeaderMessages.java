package com.azu.hospital.utils.enums.socketEnums;

public enum BroadcastingHeaderMessages {

    newOrder("You have a new order");


    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    BroadcastingHeaderMessages(String message) {
        this.message = message;
    }

}
