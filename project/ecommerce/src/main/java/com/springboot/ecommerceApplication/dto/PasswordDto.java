package com.springboot.ecommerceApplication.dto;

import javax.validation.constraints.NotEmpty;

public class PasswordDto {
 //  @NotEmpty
    private String Password;
 //  @NotEmpty
    private String confirmNewPassword;

    public PasswordDto(){

    }

    public PasswordDto(String newPassword,String confirmNewPassword) {
        this.Password = newPassword;
        this.confirmNewPassword = confirmNewPassword;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String newPassword) {
        this.Password = newPassword;
    }

    public String getConfirmPassword() {
        return confirmNewPassword;
    }

    public void setConfirmPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }
}
