package com.springboot.ecommerceApplication.co;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class SellerCO extends UserCO {

    @NotEmpty
    //@Pattern(regexp = "/^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$/",message= "Wrong GST")
    private String gst;
    @NotEmpty
    private String companyName;
    @NotEmpty
    private String companyContact;

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