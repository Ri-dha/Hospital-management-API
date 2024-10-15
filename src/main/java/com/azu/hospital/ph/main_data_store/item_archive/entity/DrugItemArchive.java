package com.azu.hospital.ph.main_data_store.item_archive.entity;

import com.azu.hospital.ph.main_data_store.drugs_item.entity.DrugsItem;
import com.azu.hospital.ph.mediciens.type.MedicineType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Currency;



@Entity
@Table(
        name = "drug_item_archive_id_seq"
)
@Data
public class DrugItemArchive {

    @Id
    @SequenceGenerator(
            name = "drug_item_archive_id_seq",
            sequenceName = "drug_item_archive_id_seq",
            allocationSize = 60
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String itemName;
    private String drugScientificName;
    private String itemCompany;
    private String barcode;
    private String dose;
    private String type;
    private BigDecimal price;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private DrugsItem drugsItem;


    public DrugItemArchive() {
    }




    public static class DrugItemArchiveBuilder {

        private Long id;
        private String itemName;
        private String itemCompany;
        private String barcode;
        private String drugScientificName;
        private String dose;
        private String type;
        private BigDecimal price;

        public DrugItemArchiveBuilder() {
        }

        public DrugItemArchiveBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public DrugItemArchiveBuilder itemName(String itemName) {
            this.itemName = itemName;
            return this;
        }

        public DrugItemArchiveBuilder itemCompany(String itemCompany) {
            this.itemCompany = itemCompany;
            return this;
        }



        public DrugItemArchiveBuilder barcode(String barcode) {
            this.barcode = barcode;
            return this;
        }


        public DrugItemArchiveBuilder drugScientificName(String drugScientificName) {
            this.drugScientificName = drugScientificName;
            return this;
        }

        public DrugItemArchiveBuilder dose(String dose) {
            this.dose = dose;
            return this;
        }

        public DrugItemArchiveBuilder type(String type) {
            this.type = type;
            return this;
        }

        public DrugItemArchiveBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }


        public DrugItemArchive build() {
            DrugItemArchive drugsItem = new DrugItemArchive();
            drugsItem.setId(id);
            drugsItem.setItemName(itemName);
            drugsItem.setItemCompany(itemCompany);
            drugsItem.setBarcode(barcode);
            drugsItem.setDrugScientificName(drugScientificName);
            drugsItem.setDose(dose);
            drugsItem.setType(type);
            drugsItem.setPrice(price);
            return drugsItem;
        }
    }


}
