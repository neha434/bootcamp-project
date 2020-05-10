package com.springboot.ecommerceApplication.dto;

import javax.validation.constraints.NotEmpty;

public class CategoryDto {
    private Integer id;

    @NotEmpty
    private String name;

    private Integer parentId;
    private String parentName;

    public CategoryDto(Integer id, String name) {
        this.id=id;
        this.name=name;
    }

    public CategoryDto() {

    }

    public Integer getParentId() {
        return parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
