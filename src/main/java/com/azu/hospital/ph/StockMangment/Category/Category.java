package com.azu.hospital.ph.StockMangment.Category;

import com.azu.hospital.ph.StockMangment.SubCategory.SubCategory;
import jakarta.persistence.*;


import java.util.Objects;



@Entity
@Table(name = "category",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "category_categoryName_unique",
                        columnNames = "categoryName"
                )
}
)
public class Category {
    @Id
    @SequenceGenerator(
            name = "category_id_seq",
            sequenceName = "category_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_id_seq"
    )
    private Integer categoryId;
    private String categoryName;




    public Category() {
    }

    public Category(Integer categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }



    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(Integer categoryId) {

    }


    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return Objects.equals(categoryId, category.categoryId) && Objects.equals(categoryName, category.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, categoryName);
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
