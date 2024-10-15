package com.azu.hospital.ph.StockMangment.Offers.Dao;

import com.azu.hospital.utils.image.Image;
import com.azu.hospital.ph.StockMangment.Company.Company;
import com.azu.hospital.ph.StockMangment.Offers.list.ConsumablesOffer;
import com.azu.hospital.ph.StockMangment.Offers.list.DrugsOffer;
import com.azu.hospital.ph.StockMangment.Supplier.Supplier;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @SequenceGenerator(
            name = "offer_id_seq",
            sequenceName = "offer_id_seq"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "offer_id_seq"
    )

    private Integer offerId;

    private String offerTitle;

    private String offerImage;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "offer_id")
    private List<DrugsOffer> offerDrugs;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "offer_id")
    private List<ConsumablesOffer> offerConsumables;


    private String store;

    private String company;


    public Offer() {
    }

    public Offer(Integer offerId, String offerTitle) {
        this.offerId = offerId;
        this.offerTitle = offerTitle;
    }

    public Offer(
            Integer offerId,
            String offerTitle,
            String offerImage,
            String store, String company) {
        this.offerId = offerId;
        this.offerTitle = offerTitle;
        this.offerImage = offerImage;
        this.store = store;
        this.company = company;
    }

    public Offer(String offerTitle, String offerImage, String store, String company) {
        this.offerTitle = offerTitle;
        this.offerImage = offerImage;
        this.store = store;
        this.company = company;
    }

    public Offer(Integer offerId, String offerTitle, String offerImage, List<DrugsOffer> offerDrugs,
                 List<ConsumablesOffer> offerConsumables, String store, String company) {
        this.offerId = offerId;
        this.offerTitle = offerTitle;
        this.offerImage = offerImage;
        this.offerDrugs = offerDrugs;
        this.offerConsumables = offerConsumables;
        this.store = store;
        this.company = company;
    }

    public Offer(String offerTitle, String offerImage, List<DrugsOffer> offerDrugs,
                 List<ConsumablesOffer> offerConsumables, String store, String company) {
        this.offerTitle = offerTitle;
        this.offerImage = offerImage;
        this.offerDrugs = offerDrugs;
        this.offerConsumables = offerConsumables;
        this.store = store;
        this.company = company;
    }

    public Offer(String offerTitle) {
        this.offerTitle = offerTitle;
    }
}
