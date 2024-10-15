package com.azu.hospital.utils.enums.patient;

public enum BillState {

  PAID("Paid"),
  MID_PAID("Mid Paid"),
  UNPAID("Unpaid");

  private String name;

  BillState(String name) {
    this.name = name;
  }
  public String getName(){
    return  name;
  }
}
