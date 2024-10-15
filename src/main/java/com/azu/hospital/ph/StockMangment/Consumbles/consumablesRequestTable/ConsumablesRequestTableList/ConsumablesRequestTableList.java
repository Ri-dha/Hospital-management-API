package com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.ConsumablesRequestTableList;

import com.azu.hospital.bulding.unit.entity.Unit;
import com.azu.hospital.bulding.ward.entity.Ward;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.entity.ConsumablesRequestTable;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "consumables-request-table-list")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class ConsumablesRequestTableList {

    @Id
    @SequenceGenerator(
            name = "consumables_requests_List_id_seq",
            sequenceName = "consumables_requests_List_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "consumables_requests_List_id_seq"
    )
    private Long requestListId;

    @Nullable
    private String requestListNote;

    private Integer requestListQuantity;

    private UnitInventoryRequestEnum requestListState = UnitInventoryRequestEnum.Waitting;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ConsumablesRequestTable> consumablesRequestTables;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_id")
    private Ward ward;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private Unit unit;

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

    public ConsumablesRequestTableList() {
    }


    public ConsumablesRequestTableList(@Nullable String requestListNote) {
        this.requestListNote = requestListNote;
    }

    @PrePersist
    public void getCrateAt(){
        this.createAt = Instant.now();
    }

    @PreUpdate
    public void getUpdate(){
        this.updateAt = Instant.now();
    }


    public Ward getWard(){
        return consumablesRequestTables
                .stream()
                .map(ConsumablesRequestTable::getWard)
                .findFirst()
                .orElse(null);
    }


    public Unit getUnit(){
        return consumablesRequestTables
                .stream()
                .map(ConsumablesRequestTable::getUnit)
                .findFirst()
                .orElse(null);
    }


    public Integer getQuantity(){
        return consumablesRequestTables
                .stream()
                .map(ConsumablesRequestTable::getQuantity)
                .findFirst()
                .orElse(null);
    }

    public ConsumablesRequestTable getConsumableRequestTable(){
        return consumablesRequestTables
                .stream()
                .findFirst()
                .orElse(null);
    }

}
