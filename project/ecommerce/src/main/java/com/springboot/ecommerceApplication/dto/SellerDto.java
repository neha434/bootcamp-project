package com.springboot.ecommerceApplication.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class SellerDto extends UserDto {
    @NotEmpty
    @Pattern(regexp = "/^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$/",message= "Wrong GST")
    private String gst;
    @NotEmpty
    private String companyName;
    @NotEmpty
    private String companyContact;

    public SellerDto(){

    }
    public SellerDto(Integer id, String email, String firstName,
                     String middleName, String lastName, String gst, String companyContact, String companyName) {
        this.setId(id);
        this.setEmail(email);
        this.setFirstName(firstName);
        this.setMiddleName(middleName);
        this.setLastName(lastName);
        this.gst=gst;
        this.companyContact=companyContact;
        this.companyName=companyName;
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

   }