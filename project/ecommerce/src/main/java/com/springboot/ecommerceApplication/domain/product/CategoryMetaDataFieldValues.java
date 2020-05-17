package com.springboot.ecommerceApplication.domain.product;

import com.springboot.ecommerceApplication.domain.StringSetConverter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "CATEGORY_METADATA_FIELD_VALUES")
public class CategoryMetaDataFieldValues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //private Set value = new Set();
    //  @Column(unique = true)
    // private String value;
    @Convert(converter = StringSetConverter.class)
    private List<String> value;
    //@ElementCollection
    //private Set<String> value;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "category_metadata_field_id")
    private CategoryMetaDataField categoryMetaDataField;

    public CategoryMetaDataFieldValues() {

    }

    public CategoryMetaDataFieldValues(Category category, CategoryMetaDataField categoryMetaDataField, List<String> value) {
        this.category = category;
        this.categoryMetaDataField = categoryMetaDataField;
        this.value = value;


    }

//    public CategoryMetaDataFieldValues(Category category, CategoryMetaDataField categoryMetaDataField, Set<String> value) {
//        this.category = category;
//      this.categoryMetaDataField = categoryMetaDataField;
//        this.value=value;
//    }


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
//    public CategoryMetaDataFieldValues(Category categoryId, CategoryMetaDataField metadataId, String value) {
//        this.category = categoryId;
//        this.categoryMetaDataField = metadataId;
//        this.value = value;
//
//    }


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
//    public String getValue() {
//        return value;
//    }
//
//    public void setValue(String value) {
//        this.value = value;
//    }


//    public Set<String> getValue() {
//        return value;
//    }
//
//    public void setValue(Set<String> value) {
//        this.value = value;
//    }


    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }
}

