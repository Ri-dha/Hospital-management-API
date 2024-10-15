package com.azu.hospital.ph.main_data_store.drugs_item.home_card.dto;




public record HomeCardDto (
        Long totalItems,
        Long orders,
        Long withdrawItems,
        Long soldItems,
        Long expiredItems,
        Long totalOffers
) {}
