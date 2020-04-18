package com.springboot.ecommerceApplication.co;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UserCO {
    @NotEmpty
    private String email;
    @NotEmpty
    private String firstName;
    private String middleName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    @Pattern(regexp="(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*])(?=.{8,15})", message = "Invalid Password")
    private String password;
    @NotEmpty
    private String confirmPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
