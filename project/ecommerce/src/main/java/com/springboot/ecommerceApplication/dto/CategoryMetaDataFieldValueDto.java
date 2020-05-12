package com.springboot.ecommerceApplication.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class CategoryMetaDataFieldValueDto {

    private Integer id;

    @NotEmpty
   @Size(min=1)
 //   @Column(unique = true)
    private String value;

//    //private Set<String> value = new HashSet<String>();
//    private HashSet value = new HashSet();
//
//
////    public Set<String> getValue() {
////        return value;
////    }
////
////    public void setValue(Set<String> value) {
////        this.value = value;
////    }


//    public HashSet getValue() {
//        return value;
//    }
//
//    public void setValue(HashSet value) {
//        this.value = value;
//    }

    private Integer categoryId;


    private Integer metadataId;

    public CategoryMetaDataFieldValueDto() {

    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getMetadataId() {
        return metadataId;
    }

    public void setMetadataId(Integer metadataId) {
        this.metadataId = metadataId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
