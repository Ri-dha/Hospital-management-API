package com.azu.hospital.ph.StockMangment.SubCategory;

import com.azu.hospital.ph.StockMangment.Category.Category;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class SubCategoryDtoMapper implements Function<SubCategory, SubCategoryDto> {
    @Override
    public SubCategoryDto apply(SubCategory subCategory) {
        return new SubCategoryDto(
                subCategory.getSubCategoryId(),
                subCategory.getSubCategoryName()
        );
    }
}
