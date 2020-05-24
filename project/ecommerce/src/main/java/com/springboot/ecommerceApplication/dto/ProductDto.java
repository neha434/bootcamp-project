package com.springboot.ecommerceApplication.dto;

import com.springboot.ecommerceApplication.domain.product.Category;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

public class ProductDto implements Serializable {
    private Integer id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private String brand;
    private boolean isCancellable;
    private boolean isReturnable;
    private boolean isActive;
    private boolean isDeleted;
    private Integer sellerId;
    private Integer categoryId;
    private String categoryName;
    private Category category;
    private List<ProductVariationDto> productVariationDtoList;



    public ProductDto(Integer id, String name, String description, String brand) {
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
        this.setBrand(brand);

    }

    public ProductDto() {

    }

    public ProductDto(Integer id, String name, String description, boolean cancellable, boolean returnable, String brand, boolean active, Category productCategory) {
        this.id=id;
        this.name=name;
        this.description=description;
        this.isCancellable=cancellable;
        this.isReturnable=returnable;
        this.brand=brand;
        this.isActive=active;
        this.category=productCategory;


    }

    public ProductDto(Integer id, String name, String description, String brand, boolean cancellable, boolean returnable, List<ProductVariationDto> productVariationDtoList) {
        this.id = id;
        this.name = name;
        this.description=description;
        this.brand=brand;
        this.isCancellable=cancellable;
        this.isReturnable=returnable;
        this.productVariationDtoList=productVariationDtoList;

    }

    public ProductDto(Integer id, String name, Integer id1, String name1, String description, boolean cancellable, String brand, boolean returnable, List<ProductVariationDto> productVariationDtoList) {
        this.id = id;
        this.name=name;
        this.categoryId=id1;
        this.categoryName=name1;
        this.description=description;
        this.isCancellable=cancellable;
        this.brand=brand;
        this.isReturnable=returnable;
        this.productVariationDtoList=productVariationDtoList;

    }



    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isCancellable() {
        return isCancellable;
    }

    public void setCancellable(boolean cancellable) {
        isCancellable = cancellable;
    }

    public boolean isReturnable() {
        return isReturnable;
    }

    public void setReturnable(boolean returnable) {
        isReturnable = returnable;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", isCancellable=" + isCancellable +
                ", isReturnable=" + isReturnable +
                '}';
    }
}

