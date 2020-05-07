package com.springboot.ecommerceApplication.dto;

import javax.validation.constraints.NotEmpty;

public class ProductDto {
    @NotEmpty
    private Integer id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private String brand;
    private boolean isCancellable;
    private boolean isReturnable;



    public ProductDto(Integer id, String name, String description, String brand) {
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
        this.setBrand(brand);

    }

    public ProductDto() {

    }

    public ProductDto(Integer id, String name, String description, boolean cancellable, boolean returnable, String brand, boolean active) {
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

