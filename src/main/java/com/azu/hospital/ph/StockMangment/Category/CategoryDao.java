package com.azu.hospital.ph.StockMangment.Category;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryDao {
    Page<Category> selectAllCategories(Pageable pageable);
    Optional<Category> selectCategoryById(Integer categoryId);
    void insertCategory(Category category);
    void updateCategory(Category update);
    void deleteCategoryById(Integer categoryId);
    boolean categoryExistById(Integer categoryId);
    boolean categoryExistByName(String categoryName);
}