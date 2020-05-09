package com.springboot.ecommerceApplication.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryMetaDataFieldValueDto {

    private Integer id;

    @NotEmpty
   @Size(min=1)
    private String value;

    private Integer categoryId;


    private Integer metadataId;


    public CategoryMetaDataFieldValueDto(){

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
