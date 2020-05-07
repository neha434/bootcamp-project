package com.springboot.ecommerceApplication.dto;

import javax.validation.constraints.NotEmpty;

public class ProductVariationDto {
    @NotEmpty
    private Integer id;
   @NotEmpty
    private Integer  quantityAvailable;
   @NotEmpty
   private Integer price;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public ProductVariationDto(@NotEmpty Integer id, @NotEmpty Integer quantityAvailable) {
        this.id = id;
        this.quantityAvailable = quantityAvailable;
    }

    public ProductVariationDto(@NotEmpty Integer id, @NotEmpty Integer quantityAvailable, @NotEmpty Integer price) {
        this.id = id;
        this.quantityAvailable = quantityAvailable;
        this.price = price;
    }
}
