package com.springboot.ecommerceApplication.dto;

import javax.validation.constraints.NotEmpty;

public class ProductVariationDto {
    @NotEmpty
    private Integer id;
   @NotEmpty
    private Integer  quantityAvailable;

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
}
