package com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.entity;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.bulding.unit.entity.Unit;
import com.azu.hospital.bulding.ward.entity.Ward;
import com.azu.hospital.ph.StockMangment.Consumbles.consumableExistTable.entity.ConsumableExpanseTable;
import com.azu.hospital.ph.StockMangment.Consumbles.entity.Consumables;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "consumables-request-table")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class ConsumablesRequestTable {

    @Id
    @SequenceGenerator(
            name = "consumables_requests_id_seq",
            sequenceName = "consumables_requests_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "consumables_requests_id_seq"
    )
    private Long requestId;

    private Integer quantity;

    @Nullable
    private String note;

    @NotNull(message = "requestStatus required")
    private UnitInventoryRequestEnum requestStatus;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Consumables> existTable;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ConsumableExpanseTable> expanseTables;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_id")
    private Ward ward;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @Column(columnDefinition = "TEXT")
    private String rejectCause;





    @Column(updatable = false)
    private Instant createAt;

    @Column(insertable = false)
    private Instant updateAt;


    @CreatedBy
    @Column(
            updatable = false
    )
    private Long createdBy;
    @LastModifiedBy
    @Column(
            insertable = false
    )
    private Long LastModifiedBy;

    public ConsumablesRequestTable() {
    }

    public ConsumablesRequestTable(Integer quantity,
                                   UnitInventoryRequestEnum requestStatus,
                                   String rejectCause
                                  ) {
        this.quantity = quantity;
        this.requestStatus = requestStatus;
        this.rejectCause = rejectCause;

    }

    @PrePersist
    public void getCrateAt(){
        this.createAt = Instant.now();
    }

    @PreUpdate
    public void getUpdate(){
        this.updateAt = Instant.now();
    }



    public String getConsumableName(){
       return existTable
                .stream()
                .map(Consumables::getConsumableName)
                .findAny()
                .orElse(null);
    }
}
