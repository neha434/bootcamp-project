package com.springboot.ecommerceApplication.co;

import javax.validation.constraints.NotEmpty;

public class CustomerCO extends UserCO{

    @NotEmpty
    private String contact;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}