package com.azu.hospital.ph.StockMangment.SubCategory;

import com.azu.hospital.ph.StockMangment.Category.Category;
import com.azu.hospital.ph.StockMangment.Category.CategoryRepository;
import com.azu.hospital.ph.StockMangment.Category.CategoryServices;
import com.azu.hospital.exceptions.DuplicateResourceException;
import com.azu.hospital.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SubCategoryServices {

    private final SubCategoryDao subCategoryDao;
    private final SubCategoryDtoMapper subCategoryDtoMapper;
    private final CategoryServices categoryServices;
    private final CategoryRepository categoryRepository;

    public SubCategoryServices(@Qualifier("SubCategoryJpa")
            SubCategoryDao subCategoryDao,
                               SubCategoryDtoMapper subCategoryDtoMapper,
                               CategoryServices categoryServices, CategoryRepository categoryRepository) {
        this.subCategoryDao = subCategoryDao;
        this.subCategoryDtoMapper = subCategoryDtoMapper;
        this.categoryServices = categoryServices;
        this.categoryRepository = categoryRepository;
    }


    public Page<SubCategory> selectAllSubCategories(Pageable pageable) {
        return subCategoryDao.selectAllSubCategories(pageable);
    }

    public SubCategoryDto getSubCategoryById(Integer SubCategoryId){
        return subCategoryDao.selectSubCategoryById(SubCategoryId)
                .map(subCategoryDtoMapper)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "SubCategory with id [%s] not found".formatted(SubCategoryId)));
    }

    public void createSubCategory( SubCategoryRegistrationRequest request, Integer categoryId){
        String SubCategoryName = request.SubCategoryName();
       if (SubCategoryName != null){
           if (subCategoryDao.existsSubCategoryBySubCategoryName(SubCategoryName)){
               throw new DuplicateResourceException(
                       "SubCategory Name already use"
               );

           }
       }else {
           throw new ResourceNotFoundException(
                   "SubCategory Name Should Not be Null"
           );
       }
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category not found with ID: " + categoryId)
        );
        SubCategory subCategory = new SubCategory();
        subCategory.setSubCategoryName(request.SubCategoryName());
        subCategory.setCategory(category);
        subCategoryDao.insertSubCategory(subCategory);

    }


    public void updateSubCategory(Integer SubCategoryId, SubCategoryUpdateRequest request, Integer categoryId){
        SubCategory subCategory = subCategoryDao.selectSubCategoryById(SubCategoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "SubCategory not found for id: " + SubCategoryId
                ));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + categoryId));

        subCategory.setSubCategoryName(request.SubCategoryName());
                subCategory.setCategory(category);

        subCategoryDao.updateSubCategory(subCategory);
    }

    public void deleteSubCategory(Integer SubCategoryId) {
        checkIfCategoryExistsOrThrow(SubCategoryId);
        subCategoryDao.deleteSubCategoryById(SubCategoryId);
    }

    private void checkIfCategoryExistsOrThrow(Integer SubCategoryId) {
        if (!subCategoryDao.subCategoryExistById(SubCategoryId)){
            throw new ResourceNotFoundException(
                    "SubCategory with id [%s] not found".formatted(SubCategoryId)
            );
        }
    }
}
