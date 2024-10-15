package com.azu.hospital.ph.StockMangment.SubCategory;


import com.azu.hospital.ph.StockMangment.Category.Category;
import com.azu.hospital.ph.StockMangment.Consumbles.entity.Consumables;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "SubCategory"
)
public class SubCategory {
    @Id
    @SequenceGenerator(
            name = "SubCategory_id_seq",
            sequenceName = "SubCategory_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SubCategory_id_seq"
    )
    private Integer SubCategoryId;
    @Column(
            nullable = false
    )
    private String SubCategoryName;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;


    @OneToMany()
    private List<Consumables> consumables;
    public SubCategory() {

    }


    public SubCategory(Integer subCategoryId, String subCategoryName, Category category) {
        SubCategoryId = subCategoryId;
        SubCategoryName = subCategoryName;
        this.category = category;


    }

    public SubCategory(String subCategoryName, Category category) {
        SubCategoryName = subCategoryName;
        this.category = category;
    }

    public SubCategory(String subCategoryName, Integer categoryId) {

    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory(String subCategoryName) {
        SubCategoryName = subCategoryName;
    }

    public Integer getSubCategoryId() {
        return SubCategoryId;
    }

    public void setSubCategoryId(Integer subCategoryId) {
        SubCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return SubCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        SubCategoryName = subCategoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubCategory that)) return false;
        return Objects.equals(SubCategoryId, that.SubCategoryId) && Objects.equals(SubCategoryName, that.SubCategoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SubCategoryId, SubCategoryName);
    }

}
