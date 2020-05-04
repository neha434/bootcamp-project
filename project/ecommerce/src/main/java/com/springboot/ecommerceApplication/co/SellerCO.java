package com.springboot.ecommerceApplication.co;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SellerCO extends UserCO {

    @NotEmpty

    private String gst;
    @NotEmpty
    private String companyName;
    @NotEmpty

    private String companyContact;
    @NotEmpty
    private String addressLine;
    @NotEmpty
    private String city;
    @NotEmpty
    private String country;
    @NotEmpty
    private String label;
    @NotEmpty
    private String state;
    @NotEmpty
    private String zipCode;

    public SellerCO(@NotNull @NotEmpty String email, @NotNull @NotEmpty String firstName,
                    String middleName, @NotNull @NotEmpty String lastName, @NotNull @NotEmpty String password,
                    @NotNull @NotEmpty String confirmPassword, @NotNull @NotEmpty String gst, @NotNull @NotEmpty String
                            companyName, @NotNull @NotEmpty String companyContact) {
        super(email, firstName, middleName, lastName, password, confirmPassword);
        this.gst = gst;
        this.companyName = companyName;
        this.companyContact = companyContact;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}