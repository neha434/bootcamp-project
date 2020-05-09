package com.springboot.ecommerceApplication.domain.product;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "CATEGORY_METADATA_FIELD_VALUES")
public class CategoryMetaDataFieldValues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String value;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "category_metadata_field_id")
    private CategoryMetaDataField categoryMetaDataField;

   public CategoryMetaDataFieldValues() {

   }

    public CategoryMetaDataFieldValues(Category categoryId, CategoryMetaDataField metadataId, String value){
    this.category=categoryId;
    this.categoryMetaDataField=metadataId;
    this.value=value;

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public CategoryMetaDataField getCategoryMetaDataField() {
        return categoryMetaDataField;
    }

    public void setCategoryMetaDataField(CategoryMetaDataField categoryMetaDataField) {
        this.categoryMetaDataField = categoryMetaDataField;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}