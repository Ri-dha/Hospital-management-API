package com.azu.hospital.ph.StockMangment.entringItemList.dto;


import com.azu.hospital.ph.StockMangment.Consumbles.Dto.ConsumableForBillDto;
import com.azu.hospital.ph.StockMangment.addOtherItems.dto.OtherItemForBillDto;
import com.azu.hospital.ph.lab_inventory.lab_main_table.dto.MainLabStoreDtoV2;
import com.azu.hospital.ph.lab_inventory.lab_main_table.dto.MainLabTubeStoreDto;
import com.azu.hospital.ph.lab_inventory.lab_main_table.entity.MainLabTubeStore;
import com.azu.hospital.ph.main_data_store.drugs_item.dto.DrugsItemForBillDto;
import com.azu.hospital.utils.enums.StockBillState;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class StockBillDto {

    private Long billId;

    private String pharmacyName;
    private String pharmacy;

    private String supplierDetails;

    private BigDecimal billNumber;

    private String mobile;

    private String location;

    private String billDate;

    private String billEntryDate;

    private BigDecimal billTotalMoneyPaid;

    private BigDecimal billTotalPrice;

    private BigDecimal billTotalDiscount;

    private BigDecimal billTotalDebts;

    private BigDecimal BillTotalAfterDiscount;

    private BigDecimal totalRestMoney;

    private String billDescriptions;
    private StockBillState state;
    private List<DrugsItemForBillDto> drugs;

    private List<ConsumableForBillDto> consumables;

    private List<OtherItemForBillDto> items;
    private List<MainLabStoreDtoV2> mainLabTubeStores;

    private Instant createAt;
    private Instant updateAt;




    public StockBillDto(
            Long billId,
            String pharmacyName,
            String pharmacy,
            String supplierDetails,
            BigDecimal billNumber,
            String billDate,
            String billEntryDate,
            BigDecimal billTotalMoneyPaid,
            BigDecimal billTotalPrice,
            BigDecimal billTotalDiscount,
            BigDecimal billTotalDebts,
            BigDecimal billTotalAfterDiscount,
            BigDecimal totalRestMoney,
            String billDescriptions,
            StockBillState state,
            List<DrugsItemForBillDto> drugs,
            List<ConsumableForBillDto> consumables,
            List<OtherItemForBillDto> items,
            List<MainLabStoreDtoV2> mainLabTubeStores,
            String location ,
            String mobile,
            Instant createAt,
            Instant updateAt
    ) {
        this.billId = billId;
        this.pharmacyName = pharmacyName;
        this.pharmacy = pharmacy;
        this.supplierDetails = supplierDetails;
        this.billNumber = billNumber;
        this.billDate = billDate;
        this.billEntryDate = billEntryDate;
        this.billTotalMoneyPaid = billTotalMoneyPaid;
        this.billTotalPrice = billTotalPrice;
        this.billTotalDiscount = billTotalDiscount;
        this.billTotalDebts = billTotalDebts;
        BillTotalAfterDiscount = billTotalAfterDiscount;
        this.totalRestMoney = totalRestMoney;
        this.billDescriptions = billDescriptions;
        this.state = state;
        this.drugs = drugs;
        this.consumables = consumables;
        this.items = items;
        this.mainLabTubeStores = mainLabTubeStores;
        this.location = location ;
        this.mobile = mobile;
        this.createAt = createAt;
        this.updateAt = updateAt;

    }

    public static StockBillDtoBuilder builder() {
        return new StockBillDtoBuilder();
    }


    public static class StockBillDtoBuilder {
        private Long billId;
        private String pharmacyName;
        private String pharmacy;
        private String supplierDetails;
        private BigDecimal billNumber;
        private String billDate;
        private String billEntryDate;
        private BigDecimal billTotalMoneyPaid;
        private BigDecimal billTotalPrice;
        private BigDecimal billTotalDiscount;
        private BigDecimal billTotalDebts;
        private BigDecimal BillTotalAfterDiscount;
        private BigDecimal totalRestMoney;
        private String billDescriptions;
        private StockBillState state;
        private List<DrugsItemForBillDto> drugs;
        private List<ConsumableForBillDto> consumables;
        private List<OtherItemForBillDto> items;

        private List<MainLabStoreDtoV2> mainLabTubeStores;
        private String mobile;
        private String location;
        private Instant creatAt;
        private Instant updateAt;

        StockBillDtoBuilder() {
        }

        public StockBillDtoBuilder billId(Long billId) {
            this.billId = billId;
            return this;
        }

        public StockBillDtoBuilder pharmacyName(String pharmacyName) {
            this.pharmacyName = pharmacyName;
            return this;
        }

        public StockBillDtoBuilder pharmacy(String pharmacy) {
            this.pharmacy = pharmacy;
            return this;
        }


        public StockBillDtoBuilder supplierDetails(String supplierDetails) {
            this.supplierDetails = supplierDetails;
            return this;
        }

        public StockBillDtoBuilder billNumber(BigDecimal billNumber) {
            this.billNumber = billNumber;
            return this;
        }

        public StockBillDtoBuilder billDate(String billDate) {
            this.billDate = billDate;
            return this;
        }

        public StockBillDtoBuilder billEntryDate(String billEntryDate) {
            this.billEntryDate = billEntryDate;
            return this;
        }

        public StockBillDtoBuilder billTotalMoneyPaid(BigDecimal billTotalMoneyPaid) {
            this.billTotalMoneyPaid = billTotalMoneyPaid;
            return this;
        }

        public StockBillDtoBuilder billTotalPrice(BigDecimal billTotalPrice) {
            this.billTotalPrice = billTotalPrice;
            return this;
        }

        public StockBillDtoBuilder billTotalDiscount(BigDecimal billTotalDiscount) {
            this.billTotalDiscount = billTotalDiscount;
            return this;
        }

        public StockBillDtoBuilder billTotalDebts(BigDecimal billTotalDebts) {
            this.billTotalDebts = billTotalDebts;
            return this;
        }

        public StockBillDtoBuilder BillTotalAfterDiscount(BigDecimal BillTotalAfterDiscount) {
            this.BillTotalAfterDiscount = BillTotalAfterDiscount;
            return this;
        }

        public StockBillDtoBuilder totalRestMoney(BigDecimal totalRestMoney) {
            this.totalRestMoney = totalRestMoney;
            return this;
        }

        public StockBillDtoBuilder billDescriptions(String billDescriptions) {
            this.billDescriptions = billDescriptions;
            return this;
        }
        public StockBillDtoBuilder state(StockBillState state) {
            this.state = state;
            return this;
        }

        public StockBillDtoBuilder drugs(List<DrugsItemForBillDto> drugs) {
            this.drugs = drugs;
            return this;
        }

        public StockBillDtoBuilder consumables(List<ConsumableForBillDto> consumables) {
            this.consumables = consumables;
            return this;
        }

        public StockBillDtoBuilder items(List<OtherItemForBillDto> items) {
            this.items = items;
            return this;
        }
        public StockBillDtoBuilder mainLabTubeStores(List<MainLabStoreDtoV2> mainLabTubeStores) {
            this.mainLabTubeStores = mainLabTubeStores;
            return this;
        }
        public StockBillDtoBuilder mobile(String mobile) {
            this.mobile = mobile;
            return this;
        }
        public StockBillDtoBuilder location(String location) {
            this.location = location;
            return this;
        }

        public StockBillDtoBuilder creatAt(Instant createAt) {
            this.creatAt = createAt;
            return this;
        }
        public StockBillDtoBuilder updateAt(Instant updateAt) {
            this.updateAt = updateAt;
            return this;
        }

        public StockBillDto build() {
            return new StockBillDto(
                    this.billId,
                    this.pharmacyName,
                    this.pharmacy,
                    this.supplierDetails,
                    this.billNumber,
                    this.billDate,
                    this.billEntryDate,
                    this.billTotalMoneyPaid,
                    this.billTotalPrice,
                    this.billTotalDiscount,
                    this.billTotalDebts,
                    this.BillTotalAfterDiscount,
                    this.totalRestMoney,
                    this.billDescriptions,
                    this.state,
                    this.drugs,
                    this.consumables,
                    this.items ,
                    this.mainLabTubeStores,
                    this.mobile,
                    this.location,
                    this.creatAt,
                    this.updateAt
            );
        }

    }
}
