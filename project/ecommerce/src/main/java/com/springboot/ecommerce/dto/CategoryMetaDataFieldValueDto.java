package com.springboot.ecommerce.dto;

import com.springboot.ecommerce.domain.StringSetConverter;

import javax.persistence.Convert;
import java.util.List;

public class CategoryMetaDataFieldValueDto {

    private Integer id;

    @Convert(converter = StringSetConverter.class)
    private List<String> value;

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

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }
}
