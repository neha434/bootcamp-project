package com.springboot.ecommerceApplication.dto;

import javax.validation.constraints.NotEmpty;

public class CustomerDto extends UserDto {
    @NotEmpty
    private String contact;
    public CustomerDto(){

    }
    public CustomerDto(Integer id, String email, String firstName, String middleName, String lastName, String contact){
        this.setId(id);
        this.setEmail(email);
        this.setFirstName(firstName);
        this.setMiddleName(middleName);
        this.setLastName(lastName);
        this.contact=contact;

    }

    public CustomerDto(@NotEmpty String contact) {
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "contact='" + contact + '\'' +
                '}';
    }
}