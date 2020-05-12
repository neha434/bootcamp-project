package com.springboot.ecommerceApplication.domain.product;

import org.hibernate.mapping.Set;

import javax.persistence.*;
import java.util.HashSet;


@Entity
@Table(name = "CATEGORY_METADATA_FIELD_VALUES")
public class CategoryMetaDataFieldValues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //private Set value = new Set();
  //  @Column(unique = true)
    private String value;
   //private HashSet value = new HashSet();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "category_metadata_field_id")
    private CategoryMetaDataField categoryMetaDataField;

    public CategoryMetaDataFieldValues() {

    }
//
//    public CategoryMetaDataFieldValues(Category category, CategoryMetaDataField categoryMetaDataField, HashSet value) {
//        this.category = category;
//        this.categoryMetaDataField = categoryMetaDataField;
//        this.value=value;
//
//    }
//
//    public HashSet getValue() {
//        return value;
//    }

//    public CategoryMetaDataFieldValues(Category category, CategoryMetaDataField categoryMetaDataField, Set<String> value) {
//        this.category = category;
//        this.categoryMetaDataField = categoryMetaDataField;
//        this.value = value;
//
//    }

//    public void setValue(HashSet value) {
//        this.value = value;
//    }
    public CategoryMetaDataFieldValues(Category categoryId, CategoryMetaDataField metadataId, String value) {
        this.category = categoryId;
        this.categoryMetaDataField = metadataId;
        this.value = value;

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

//    public Set<String> getValue() {
//        return value;
//    }
//
//    public void setValue(Set<String> value) {
//        this.value = value;
//    }
//
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

