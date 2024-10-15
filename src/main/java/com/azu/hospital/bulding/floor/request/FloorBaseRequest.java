package com.azu.hospital.bulding.floor.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class FloorBaseRequest{
        @NotNull(message = "Floor Number Require")
         private String floorNumber;
        @NotNull(message = "Floor Name Require")
        @NotEmpty(message = "Floor Name Require")
        @NotBlank(message = "Floor Name Require")
        private String floorName;


        public FloorBaseRequest() {
        }

        public FloorBaseRequest(String floorNumber, String floorName) {
                this.floorNumber = floorNumber;
                this.floorName = floorName;
        }


        public String getFloorNumber() {
                return floorNumber;
        }

        public void setFloorNumber(String floorNumber) {
                this.floorNumber = floorNumber;
        }

        public String getFloorName() {
                return floorName;
        }

        public void setFloorName(String floorName) {
                this.floorName = floorName;
        }
}
