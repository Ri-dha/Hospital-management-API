package com.azu.hospital.ph.StockMangment.Category;


import com.azu.hospital.exceptions.DuplicateResourceException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class CategoryServices {

    private final CategoryDao categoryDao;
    private final CategoryDtoMapper categoryDtoMapper;
    private final ExceptionsMessageReturn messageReturn;

    public CategoryServices( @Qualifier("categoryJpa")
                             CategoryDao categoryDao,
                             CategoryDtoMapper categoryDtoMapper,
                             ExceptionsMessageReturn messageReturn) {

        this.categoryDao = categoryDao;
        this.categoryDtoMapper = categoryDtoMapper;
        this.messageReturn = messageReturn;
    }

    public Page<Category> selectAllCategories(Pageable pageable) {
        return categoryDao.selectAllCategories(pageable);
    }

    public CategoryDto getCategoryById(Integer categoryId){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        return categoryDao.selectCategoryById(categoryId)
                .map(categoryDtoMapper)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messages.getString("categoryNotFound") + categoryId
                ));

    }

    public void createCategory(CategoryRegistrationRequest request){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        String categoryName = request.categoryName();
       if (categoryName != null){
           if (categoryDao.categoryExistByName(categoryName)){
               throw new DuplicateResourceException(
                        messages.getString("nameDuplicate") + categoryName               );

           }
       }else {
           throw new ResourceNotFoundException(
                     messages.getString("nameIsRequired")
           );
       }
        Category category = new Category(
                request.categoryName()
        );
        categoryDao.insertCategory(category);

    }


    public void updateCategory(Integer categoryId, CategoryUpdateRequest request){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Category category = categoryDao.selectCategoryById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messages.getString("categoryNotFound") + categoryId
                ));

        if (request.categoryName() != null) {
            category.setCategoryName(request.categoryName());
        }

        categoryDao.updateCategory(category);
    }

    public void deleteCategory(Integer categoryId) {
        checkIfCategoryExistsOrThrow(categoryId);
        categoryDao.deleteCategoryById(categoryId);
    }

    private void checkIfCategoryExistsOrThrow(Integer categoryId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        if (!categoryDao.categoryExistById(categoryId)){
            throw new ResourceNotFoundException(
                    messages.getString("categoryNotFound") + categoryId
            );
        }
    }
}
