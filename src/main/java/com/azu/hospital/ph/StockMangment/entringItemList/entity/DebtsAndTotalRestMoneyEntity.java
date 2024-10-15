//package com.azu.hospital.ph.StockMangment.entringItemList.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import lombok.Data;
//import org.hibernate.annotations.CurrentTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import java.math.BigDecimal;
//import java.time.Instant;
//
//@Entity
//@Table(name = "debts_rest_money")
//@Data
//public class DebtsAndTotalRestMoneyEntity {
//
//    @Id
//    @SequenceGenerator(
//            name = "debts_rest_money_id_seq",
//            sequenceName = "debts_rest_money_id_seq",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "debts_rest_money_id_seq"
//    )
//    private Long id;
//
//    @NotNull(message = "Rest Money  Required")
//    private BigDecimal billTotalDebts;
//
//    @NotNull(message = "Rest Money  Required")
//    private BigDecimal totalRestMoney;
//
//    @Column(updatable = false)
//    @CurrentTimestamp
//    private Instant createdAt;
//    @Column(insertable = false)
//    @UpdateTimestamp
//    private Instant updatedAt;
//
//    public DebtsAndTotalRestMoneyEntity() {
//    }
//
//    public DebtsAndTotalRestMoneyEntity(BigDecimal billTotalDebts, BigDecimal totalRestMoney) {
//        this.billTotalDebts = billTotalDebts;
//        this.totalRestMoney = totalRestMoney;
//    }
//}
