package com.springboot.ecommerce.domain.product;

import com.springboot.ecommerce.domain.user.Customer;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_REVIEW")
public class ProductReview  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//Updated

    private String review;

    private Double rating;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName ="id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_USER_ID")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Double getRating() {
        return rating;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }



  }
