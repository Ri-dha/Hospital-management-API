package com.azu.hospital.ph.StockMangment.SubCategory;

import com.azu.hospital.ph.StockMangment.Category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SubCategoryDao {
    Page<SubCategory> selectAllSubCategories(Pageable pageable);
    Optional<SubCategory> selectSubCategoryById(Integer SubCategoryId);
    void insertSubCategory(SubCategory subCategory);
    void updateSubCategory(SubCategory update);
    void deleteSubCategoryById(Integer SubCategoryId);
    boolean subCategoryExistById(Integer SubCategoryId);
    boolean existsSubCategoryBySubCategoryName(String SubCategoryName);
}