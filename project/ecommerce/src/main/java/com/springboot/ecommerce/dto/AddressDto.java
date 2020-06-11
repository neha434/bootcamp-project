package com.springboot.ecommerce.dto;

import javax.validation.constraints.NotEmpty;

public class AddressDto {


    private Integer id;
     @NotEmpty
    private String city;
     @NotEmpty
    private String state;
     @NotEmpty
    private String country;
     @NotEmpty
    private String addressLine;
     @NotEmpty
    private String zipCode;
     @NotEmpty
    private String label;

    public AddressDto(@NotEmpty Integer id, @NotEmpty String city, @NotEmpty String state, @NotEmpty String country, @NotEmpty String addressLine, @NotEmpty String zipCode, @NotEmpty String label) {
        this.id = id;
        this.city = city;
        this.state = state;
        this.country = country;
        this.addressLine = addressLine;
        this.zipCode = zipCode;
        this.label = label;
    }

    public AddressDto() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
