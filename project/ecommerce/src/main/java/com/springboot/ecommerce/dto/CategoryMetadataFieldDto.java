package com.springboot.ecommerce.dto;

import javax.validation.constraints.NotEmpty;

public class CategoryMetadataFieldDto {

    private Integer id;

    @NotEmpty
    private String name;

    public CategoryMetadataFieldDto(Integer id, String name) {
        this.id= id;
        this.name=name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
