package com.springboot.ecommerceApplication.dto;

public class CartDto {
    private Integer cartProductId;
    private Integer customerId;
    private Integer quantity;
    private boolean isWishList;
    private Integer productVariationId;

    public CartDto(){

    }
    public CartDto(Integer cartProductId, Integer quantity, boolean wishList, Integer customerId, Integer productVariationId) {
        this.cartProductId=cartProductId;
        this.quantity=quantity;
        this.isWishList=wishList;
        this.customerId=customerId;
        this.productVariationId=productVariationId;
    }

    public Integer getCartProductId() {
        return cartProductId;
    }

    public void setCartProductId(Integer cartProductId) {
        this.cartProductId = cartProductId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean isWishList() {
        return isWishList;
    }

    public void setWishList(boolean wishList) {
        isWishList = wishList;
    }

    public Integer getProductVariationId() {
        return productVariationId;
    }

    public void setProductVariationId(Integer productVariationId) {
        this.productVariationId = productVariationId;
    }
}