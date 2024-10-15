package com.azu.hospital.ph.StockMangment.Category;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository("categoryJpa")
@Qualifier("categoryJpa")
public class CategoryJPADataAccesses implements CategoryDao {
    private final CategoryRepository categoryRepository;

    public CategoryJPADataAccesses(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Category> selectAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Optional<Category> selectCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public void insertCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Category update) {
        categoryRepository.save(update);
    }

    @Override
    public void deleteCategoryById(Integer categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public boolean categoryExistById(Integer categoryId) {
        return categoryRepository.existsById(categoryId);
    }

    @Override
    public boolean categoryExistByName(String categoryName) {
        return categoryRepository.existsCategoryByCategoryName(categoryName);
    }
}
