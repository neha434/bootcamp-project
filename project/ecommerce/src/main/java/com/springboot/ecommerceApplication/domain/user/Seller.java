package com.springboot.ecommerceApplication.domain.user;

import com.springboot.ecommerceApplication.domain.product.Product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "SELLERS")
@PrimaryKeyJoinColumn(name = "USER_ID")
public class Seller extends User implements Serializable {

 //@Pattern(regexp = "/^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$/",message= "Wrong GST")
    private String gst;
    private String companyContact;
    private String companyName;

    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL)
    private List<Product> productList;


    public Seller(String email, String firstName, String middleName, String lastName, String password,  String gst, String companyContact, String companyName) {
        super( email, firstName,middleName,lastName,password);
        this.gst = gst;
        this.companyContact = companyContact;
        this.companyName = companyName;
    }

    public Seller() {

        super(1, "Jack Lee", "jack@example.com");
    }


    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productsList) {
        this.productList = productsList;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "gst='" + gst + '\'' +
                ", companyContact='" + companyContact + '\'' +
                ", companyName='" + companyName + '\'' +
               // ", productList=" + productList +
                '}';
    }
}
