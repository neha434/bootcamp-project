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

    public CustomerCO(@NotEmpty String email, @NotEmpty String firstName, String middleName, @NotEmpty String lastName, @NotEmpty String password, @NotEmpty String confirmPassword, @NotEmpty String contact) {
        super(email, firstName, middleName, lastName, password, confirmPassword);
        this.contact = contact;
    }

}