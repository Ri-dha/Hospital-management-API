package com.azu.hospital.ph.StockMangment.Category;

import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class CategoryDtoMapper implements Function<Category, CategoryDto> {
    @Override
    public CategoryDto apply(Category category) {
        return new CategoryDto(
                category.getCategoryId(),
                category.getCategoryName()
        );
    }
}
