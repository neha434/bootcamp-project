package com.springboot.ecommerceApplication.domain.order;

import com.springboot.ecommerceApplication.domain.product.ProductVariation;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_PRODUCT")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer quantity;
    private Integer price;
    private String productVariationMetadata;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order orders;

    public Order getOrders() {
        return orders;
    }
    @ManyToOne
    @JoinColumn(name = "PRODUCT_VARIATION_ID")
    private ProductVariation productVariation;

    public ProductVariation getProductVariation() {
        return productVariation;
    }

    public void setProductVariation(ProductVariation productVariation) {
        this.productVariation = productVariation;
    }

    public void setOrders(Order orders) {
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getProductVariationMetadata() {
        return productVariationMetadata;
    }

    public void setProductVariationMetadata(String productVariationMetadata) {
        this.productVariationMetadata = productVariationMetadata;
    }
}
