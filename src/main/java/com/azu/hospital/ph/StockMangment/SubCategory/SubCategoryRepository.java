package com.azu.hospital.ph.StockMangment.SubCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends
        JpaRepository<SubCategory, Integer> {

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM SubCategory  s WHERE s.SubCategoryName = :SubCategoryName")
    boolean existsSubCategoryBySubCategoryName(String SubCategoryName);

}
