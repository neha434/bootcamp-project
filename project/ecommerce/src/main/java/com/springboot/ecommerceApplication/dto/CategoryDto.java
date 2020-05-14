package com.springboot.ecommerceApplication.dto;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;

public class CategoryDto {
    private Integer id;

    @NotEmpty
    private String name;

   private Integer parentId;
   // private String parentName;
    private CategoryDto parentCategory;
    //  private CategoryDto parentId;
//    private CategoryDto parentName;
    //private HashMap<Integer, String> catogaries;
   // private HashMap<Integer, String> subCategoryList;

    public CategoryDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDto() {

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

//    public HashMap<Integer, String> getSubCategoryList() {
//        return subCategoryList;
//    }
//
//    public void setSubCategoryList(HashMap<Integer, String> subCategoryList) {
//        this.subCategoryList = subCategoryList;
//    }
//
//    public HashMap<Integer, String> getCatogaries() {
//        return catogaries;
//    }
//
//    public void setCatogaries(HashMap<Integer, String> catogaries) {
//        this.catogaries = catogaries;
//    }
//    //    public CategoryDto(String name, Integer id) {
////
////    }


//    public CategoryDto getParentId() {
//        return parentId;
//    }

//    public void setParentId(CategoryDto parentId) {
//        this.parentId = parentId;
//    }
//
//    public CategoryDto getParentName() {
//        return parentName;
//    }
//
//    public void setParentName(CategoryDto parentName) {
//        this.parentName = parentName;
//    }

//    public Integer getParentId() {
//        return parentId;
//    }
//
//    public void setParentId(Integer parentId) {
//        this.parentId = parentId;
//    }
//
//    public String getParentName() {
//        return parentName;
//    }
//
//    public void setParentName(String parentName) {
//        this.parentName = parentName;
//    }

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
