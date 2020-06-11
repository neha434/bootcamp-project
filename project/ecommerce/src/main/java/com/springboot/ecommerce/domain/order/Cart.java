package com.springboot.ecommerce.domain.order;

import com.springboot.ecommerce.domain.product.ProductVariation;
import com.springboot.ecommerce.domain.user.Customer;
import javax.persistence.*;

@Entity
@Table(name = "CART")
//@PrimaryKeyJoinColumn(name = "CUSTOMER_USER_ID")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer quantity;
    private boolean WishList;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_VARIATION_ID")
    private ProductVariation productVariation;

    public ProductVariation getProductVariation() {
        return productVariation;
    }

    public void setProductVariation(ProductVariation productVariation) {
        this.productVariation = productVariation;
    }

    @OneToOne
    @JoinColumn(name = "CUSTOMER_USER_ID")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public boolean WishList() {
        return WishList;
    }

    public void setWishList(boolean wishList) {
        WishList = wishList;
    }

    }
