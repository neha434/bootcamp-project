package com.springboot.ecommerceApplication.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

public class ProductVariationDto implements Serializable {

    private Integer id;
   @NotNull
   @Min(0)
    private Integer  quantityAvailable;
   @NotNull
   @Min(1)
   private Integer price;
   private Integer productId;
   private String productName;
    Map<String, String> metaData;

    public ProductVariationDto(Integer id, Integer price, Integer quantityAvailable, Integer id1, String name) {
        this.id=id;
        this.price=price;
        this.quantityAvailable=quantityAvailable;
        this.productId=id1;
        this.productName=name;
    }

    public Map<String, String> getMetaData() {
        return metaData;
    }

    public void setMetaData(Map<String, String> metaData) {
        this.metaData = metaData;
    }

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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public ProductVariationDto(){

    }
    @Override
    public String toString() {
        return "ProductVariationDto{" +
                "id=" + id +
                ", quantityAvailable=" + quantityAvailable +
                ", price=" + price +
                '}';
    }
}
