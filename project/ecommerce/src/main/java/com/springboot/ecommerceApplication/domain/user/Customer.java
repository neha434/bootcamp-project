package com.springboot.ecommerceApplication.domain.user;

import com.springboot.ecommerceApplication.domain.order.Cart;
import com.springboot.ecommerceApplication.domain.order.Order;
import com.springboot.ecommerceApplication.domain.product.ProductReview;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
@PrimaryKeyJoinColumn(name = "USER_ID")
//@Inheritance(strategy = InheritanceType.JOINED)    //cart table inherits customer table
public class Customer extends User{
    private  String contact;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<ProductReview> productReviewsList;

    public List<ProductReview> getProductReviewsList() {
        return productReviewsList;
    }

    public void setProductReviewsList(List<ProductReview> productReviewsList) {
        this.productReviewsList = productReviewsList;
    }

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Order> ordersList;

    public List<Order> getOrdersList() {
        return ordersList;
    }
    public void setOrdersList(List<Order> ordersList) {
        this.ordersList = ordersList;
    }


    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }


    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}