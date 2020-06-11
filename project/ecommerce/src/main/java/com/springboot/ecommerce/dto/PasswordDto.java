package com.springboot.ecommerce.dto;

import javax.validation.constraints.NotEmpty;

public class PasswordDto {

    @NotEmpty
    private String password;
    @NotEmpty
    private String confirmNewPassword;


    public PasswordDto() {

    }

    public PasswordDto(String newPassword, String confirmNewPassword) {
        this.password = newPassword;
        this.confirmNewPassword = confirmNewPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public String getConfirmPassword() {
        return confirmNewPassword;
    }

    public void setConfirmPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }
}
