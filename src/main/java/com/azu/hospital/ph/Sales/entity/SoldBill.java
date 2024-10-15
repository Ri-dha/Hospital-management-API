package com.azu.hospital.ph.Sales.entity;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.ph.Sales.solid_item_list.entity.SoldItems;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.entity.DrugRequestHandlerList;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "sold_bill", schema = "public")
@Getter
@Setter
public class SoldBill {

    @Id
    @SequenceGenerator(
            name = "sold_bill_id_seq",
            sequenceName = "sold_bill_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sold_bill_id_seq"
    )
    private Long billId;


    private Instant dateOfPurchase = Instant.now();

    private BigDecimal billTotalPrice;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy ="soldBill")
    private List<SoldItems> itemsList = new ArrayList<>();

    private boolean isArchived = false;
    private LocalDate dateOfPayment = LocalDate.now();


    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Patient Patient;

    @Column(updatable = false)
    private Instant createAt;
    private Instant updateAt;

    public SoldBill() {
    }

    public SoldBill(Long billId,
                    BigDecimal billTotalPrice, List<SoldItems> itemsList) {
        this.billId = billId;
        this.billTotalPrice = billTotalPrice;
        this.itemsList = itemsList;
    }

    public SoldBill(BigDecimal fullPrice) {
        this.billTotalPrice = fullPrice;
    }


    @PrePersist
    public void setCreateAt(){
        this.createAt = Instant.now();
    }

    @PreUpdate
    public void setUpdateAt(){
        this.updateAt = Instant.now();
    }


}
