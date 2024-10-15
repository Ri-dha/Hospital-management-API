package com.azu.hospital.Rx;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class RxList {

    @Id
    @GeneratedValue()
    private Integer id;
    private String name;

    public RxList() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public RxList(String name) {
        this.name = name;
    }



}
