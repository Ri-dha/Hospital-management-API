package com.azu.hospital.ph.StockMangment.SubCategory;

import com.azu.hospital.ph.StockMangment.Category.CategoryRepository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository("SubCategoryJpa")
@Qualifier("SubCategoryJpa")
public class SubCategoryJPADataAccesses implements SubCategoryDao {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;

    public SubCategoryJPADataAccesses(SubCategoryRepository subCategoryRepository,
                                      CategoryRepository categoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<SubCategory> selectAllSubCategories(Pageable pageable) {
        return subCategoryRepository.findAll(pageable);
    }

    @Override
    public Optional<SubCategory> selectSubCategoryById(Integer SubCategoryId) {
        return subCategoryRepository.findById(SubCategoryId);
    }

    @Override
    public void insertSubCategory( SubCategory subCategory) {

        subCategoryRepository.save(subCategory);
    }

    @Override
    public void updateSubCategory(SubCategory update) {

        subCategoryRepository.save(update);
    }
    @Override
    public void deleteSubCategoryById(Integer SubCategoryId) {
        subCategoryRepository.deleteById(SubCategoryId);
    }

    @Override
    public boolean subCategoryExistById(Integer SubCategoryId) {
        return subCategoryRepository.existsById(SubCategoryId);
    }

    @Override
    public boolean existsSubCategoryBySubCategoryName(String SubCategoryName) {
        return subCategoryRepository.existsSubCategoryBySubCategoryName(SubCategoryName);
    }
}
