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

    public ProductDto(Integer id, String name, String description, String brand) {
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
        this.setBrand(brand);

    }

    public ProductDto() {

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
}

