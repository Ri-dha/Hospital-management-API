package com.azu.hospital.ph.StockMangment.SubCategory;

import com.azu.hospital.ph.StockMangment.Category.Category;
import jakarta.websocket.server.PathParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.condition.ParamsRequestCondition;

@RestController
@RequestMapping("api/v1/subcategories")
@CrossOrigin
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
public class SubCategoryController {
    private final SubCategoryServices subCategoryServices;

    public SubCategoryController( SubCategoryServices subCategoryServices) {
        this.subCategoryServices = subCategoryServices;

    }


    @GetMapping
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<Page<SubCategory>> getAllSubCategories(Pageable pageable) {
        Page<SubCategory> SubcategoryPage = subCategoryServices.selectAllSubCategories(pageable);
        return ResponseEntity.ok(SubcategoryPage);
    }
    @GetMapping("{subcategoryId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public SubCategoryDto getSubCategory(
            @PathVariable("subcategoryId") Integer SubCategoryId
    ) {
        return subCategoryServices.getSubCategoryById(SubCategoryId);
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<?> registerSubCategory(
            @RequestBody SubCategoryRegistrationRequest request,
            @RequestParam Integer categoryId
    ){
        subCategoryServices.createSubCategory(request, categoryId);
        boolean status = true;
        return ResponseEntity.ok().body("{\"status\": " + status + "}");
    }
    @DeleteMapping({"{subcategoryId}"})
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<String> deleteSubCategoryById(@PathVariable("subcategoryId") Integer SubCategoryId){
        subCategoryServices.deleteSubCategory(SubCategoryId);
        boolean status = true;
        return ResponseEntity.ok().body("{\"status\": " + status + "}");
    }

    @PutMapping ({"{subcategoryId}"})
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<String> updateSubCategoryById(
            @PathVariable("subcategoryId") Integer SubCategoryId,
            @RequestBody SubCategoryUpdateRequest request,
            @RequestParam Integer categoryId){
        subCategoryServices.updateSubCategory(SubCategoryId, request, categoryId);
        boolean status = true;
        return ResponseEntity.ok().body("{\"status\": " + status + "}");
    }
}
