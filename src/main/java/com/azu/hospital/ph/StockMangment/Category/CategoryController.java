package com.azu.hospital.ph.StockMangment.Category;

import jakarta.websocket.server.PathParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/v1/categories")
@CrossOrigin
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
public class CategoryController {
    private final CategoryServices categoryServices;

    public CategoryController(CategoryServices categoryServices) {
        this.categoryServices = categoryServices;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<Page<Category>> getAllCategories(Pageable pageable) {
        Page<Category> categoryPage = categoryServices.selectAllCategories(pageable);
        return ResponseEntity.ok(categoryPage);
    }
    @GetMapping("{categoryId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public CategoryDto getCategory(
            @PathVariable("categoryId") Integer categoryId
    ) {
        return categoryServices.getCategoryById(categoryId);
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<?> registerCategory(
            @RequestBody CategoryRegistrationRequest request){
        categoryServices.createCategory(request);
        boolean status = true;
        return ResponseEntity.ok().body("{\"status\": " + status + "}");
    }

    @DeleteMapping("{categoryId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<String> deleteCategoryById(
            @PathVariable("categoryId") Integer categoryId) {
        categoryServices.deleteCategory(categoryId);
        boolean status = true;
        return ResponseEntity.ok().body("{\"status\": " + status + "}");
    }


    @PutMapping ("{categoryId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<String> updateCategoryById(
            @PathVariable("categoryId") Integer categoryId,
            @RequestBody CategoryUpdateRequest request){
        categoryServices.updateCategory(categoryId,request);
        boolean status = true;
        return ResponseEntity.ok().body("{\"status\": " + status + "}");
    }
}
