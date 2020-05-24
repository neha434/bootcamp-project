package com.springboot.ecommerceApplication.dto;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.List;

public class CategoryDto {
    private Integer id;

    @NotEmpty
    private String name;

   private Integer parentId;
    private CategoryDto parentCategory;
    List<CategoryDto> childCategory;

    public CategoryDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDto() {

    }

    public List<CategoryDto> getChildCategory() {
        return childCategory;
    }

    public void setChildCategory(List<CategoryDto> childCategory) {
        this.childCategory = childCategory;
    }

    public CategoryDto getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(CategoryDto parentCategory) {
        this.parentCategory = parentCategory;
    }

    public CategoryDto(String name, String name1, Integer id) {
        //  this.parentName = name;
        this.name = name1;
        this.id = id;

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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
