package com.azu.hospital.ph.StockMangment.SubCategory;

import com.azu.hospital.ph.StockMangment.Category.Category;

public record SubCategoryRegistrationRequest(
        String SubCategoryName,
        Category category
) {
}
