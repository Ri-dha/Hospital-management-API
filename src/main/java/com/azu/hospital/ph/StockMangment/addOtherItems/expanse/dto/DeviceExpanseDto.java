package com.azu.hospital.ph.StockMangment.addOtherItems.expanse.dto;

public class DeviceExpanseDto {

    private Long deviceId;

    private String deviceName;

    private String deviceType;

    private Integer deviceCount;

    private Long deviceInMainTableStoreId;

    private Long existDeviceId;

    private String deviceSerialNo;

    private String deviceBarcode;

    private String devicePlace;

    public DeviceExpanseDto() {
    }

    public DeviceExpanseDto(Long deviceId, String deviceName, String deviceType, Integer deviceCount, Long deviceInMainTableStoreId, Long existDeviceId, String deviceSerialNo, String deviceBarcode, String devicePlace) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.deviceCount = deviceCount;
        this.deviceInMainTableStoreId = deviceInMainTableStoreId;
        this.existDeviceId = existDeviceId;
        this.deviceSerialNo = deviceSerialNo;
        this.deviceBarcode = deviceBarcode;
        this.devicePlace = devicePlace;
    }

    public static DeviceExpanseDtoBuilder builder() {
        return new DeviceExpanseDtoBuilder();
    }


    public Long getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public Integer getDeviceCount() {
        return this.deviceCount;
    }

    public Long getDeviceInMainTableStoreId() {
        return this.deviceInMainTableStoreId;
    }

    public Long getExistDeviceId() {
        return this.existDeviceId;
    }

    public String getDeviceSerialNo() {
        return this.deviceSerialNo;
    }

    public String getDeviceBarcode() {
        return this.deviceBarcode;
    }

    public String getDevicePlace() {
        return this.devicePlace;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public void setDeviceCount(Integer deviceCount) {
        this.deviceCount = deviceCount;
    }

    public void setDeviceInMainTableStoreId(Long deviceInMainTableStoreId) {
        this.deviceInMainTableStoreId = deviceInMainTableStoreId;
    }

    public void setExistDeviceId(Long existDeviceId) {
        this.existDeviceId = existDeviceId;
    }

    public void setDeviceSerialNo(String deviceSerialNo) {
        this.deviceSerialNo = deviceSerialNo;
    }

    public void setDeviceBarcode(String deviceBarcode) {
        this.deviceBarcode = deviceBarcode;
    }

    public void setDevicePlace(String devicePlace) {
        this.devicePlace = devicePlace;
    }


    public static class DeviceExpanseDtoBuilder {
        private Long deviceId;
        private String deviceName;
        private String deviceType;
        private Integer deviceCount;
        private Long deviceInMainTableStoreId;
        private Long existDeviceId;
        private String deviceSerialNo;
        private String deviceBarcode;
        private String devicePlace;

        DeviceExpanseDtoBuilder() {
        }

        public DeviceExpanseDtoBuilder deviceId(Long deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public DeviceExpanseDtoBuilder deviceName(String deviceName) {
            this.deviceName = deviceName;
            return this;
        }

        public DeviceExpanseDtoBuilder deviceType(String deviceType) {
            this.deviceType = deviceType;
            return this;
        }

        public DeviceExpanseDtoBuilder deviceCount(Integer deviceCount) {
            this.deviceCount = deviceCount;
            return this;
        }

        public DeviceExpanseDtoBuilder deviceInMainTableStoreId(Long deviceInMainTableStoreId) {
            this.deviceInMainTableStoreId = deviceInMainTableStoreId;
            return this;
        }

        public DeviceExpanseDtoBuilder existDeviceId(Long existDeviceId) {
            this.existDeviceId = existDeviceId;
            return this;
        }

        public DeviceExpanseDtoBuilder deviceSerialNo(String deviceSerialNo) {
            this.deviceSerialNo = deviceSerialNo;
            return this;
        }

        public DeviceExpanseDtoBuilder deviceBarcode(String deviceBarcode) {
            this.deviceBarcode = deviceBarcode;
            return this;
        }

        public DeviceExpanseDtoBuilder devicePlace(String devicePlace) {
            this.devicePlace = devicePlace;
            return this;
        }

        public DeviceExpanseDto build() {
            return new DeviceExpanseDto(this.deviceId, this.deviceName, this.deviceType, this.deviceCount,
                    this.deviceInMainTableStoreId, this.existDeviceId, this.deviceSerialNo, this.deviceBarcode,
                    this.devicePlace);
        }


    }
}
