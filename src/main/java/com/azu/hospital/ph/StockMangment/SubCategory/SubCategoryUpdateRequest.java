package com.azu.hospital.ph.StockMangment.SubCategory;

import com.azu.hospital.ph.StockMangment.Category.Category;

public record SubCategoryUpdateRequest(
        String SubCategoryName,
        Category categoryId
) {
}
