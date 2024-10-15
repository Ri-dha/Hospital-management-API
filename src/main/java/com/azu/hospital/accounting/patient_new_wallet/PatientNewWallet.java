package com.azu.hospital.accounting.patient_new_wallet;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "patient_new_wallet")
@Getter
@Setter
public class PatientNewWallet {


    @Id
    @SequenceGenerator(
            sequenceName = "patient_new_wallet_id_sequence",
            name = "patient_new_wallet_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_new_wallet_id_sequence"
    )
    private Long id;

    private Long operationId;

    private Long userId;

    private Long patientIdm;

    private String patientName;

    private String operationName;

    private String userName;

    private String note;

    private Double operationCost;

    private Double doctorPercentage;


    private boolean isPaid = false; // use this to archive the payment after it is paid

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;



    public PatientNewWallet() {
    }

    public PatientNewWallet(Long operationId,
                            Long userId,
                            Long patientId, String patientName, String operationName,
                            String userName, String note, Double operationCost,
                            Double doctorPercentage) {
        this.operationId = operationId;
        this.userId = userId;
        this.patientIdm = patientId;
        this.patientName = patientName;
        this.operationName = operationName;
        this.userName = userName;
        this.note = note;
        this.operationCost = operationCost;
        this.doctorPercentage = doctorPercentage;

    }
}
