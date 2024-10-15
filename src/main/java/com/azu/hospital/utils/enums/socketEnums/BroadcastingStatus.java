package com.azu.hospital.utils.enums.socketEnums;

import javax.management.Notification;

public enum BroadcastingStatus {

    Notification("noti");

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    BroadcastingStatus(String state) {
        this.state = state;
    }
}
