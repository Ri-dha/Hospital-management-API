package com.azu.hospital.consultant.consultantPatient.ConsultantRx.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "consultant_rxs")
@Data
public class ConsultantRx {

    @Id
    @SequenceGenerator(name = "consultant_rx_id_seq" , sequenceName = "consultant_rx_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


}
