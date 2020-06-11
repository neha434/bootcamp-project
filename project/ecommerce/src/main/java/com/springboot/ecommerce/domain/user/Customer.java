package com.springboot.ecommerce.domain.user;

import com.springboot.ecommerce.domain.order.Cart;
import com.springboot.ecommerce.domain.order.Order;
import com.springboot.ecommerce.domain.product.ProductReview;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
@PrimaryKeyJoinColumn(name = "USER_ID")
//@Inheritance(strategy = InheritanceType.JOINED)    //cart table inherits customer table
public class Customer extends User implements Serializable {
    private  String contact;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<ProductReview> productReviewsList;

    public Customer(String email, String firstName, String middleName, String lastName, String password, String contact) {
        super( email, firstName,middleName,lastName,password);
        this.contact= contact;
    }

    public Customer() {

        super(1, "Jack Lee", "jack@example.com");
    }

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

    @Override
    public String toString() {
        return "Customer{" +
                "contact='" + contact + '\'' +
                ", productReviewsList=" + productReviewsList +
                ", ordersList=" + ordersList +
                ", cart=" + cart +
                '}';
    }
}