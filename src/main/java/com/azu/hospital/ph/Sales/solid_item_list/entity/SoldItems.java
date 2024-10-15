package com.azu.hospital.ph.Sales.solid_item_list.entity;


import com.azu.hospital.ph.Sales.entity.SoldBill;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

;



@Entity
@Table(name = "sold_item_list")
@Getter
@Setter
public class SoldItems {

    @Id
    @SequenceGenerator(
            name = "soldItem_id_seq",
            sequenceName = "soldItem_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "soldItem_id_seq"
    )
    private Long id;
    private String itemName;
    private Integer quantity;

    @Version
    private Long version;


    @ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "sold_item_id")
    private SoldBill soldBill;


    public SoldItems() {
    }



    public SoldItems(String itemName, Integer quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }


    public SoldItems(Long id, String itemName, Integer quantity) {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
    }
}
