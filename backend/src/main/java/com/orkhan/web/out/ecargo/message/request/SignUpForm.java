package com.orkhan.web.out.ecargo.message.request;

import javax.validation.constraints.*;

public class SignUpForm {
    @NotBlank
    @Size(min = 3, max = 50)
    private String firstname;
    @NotBlank
    @Size(min = 3, max = 50)
    private String surname;

    @NotBlank
    @Size(min = 1, max = 50)
    private String phone;

    @NotBlank
    @Size(min = 3, max = 50)
    private String country;

    @NotBlank
    @Size(max = 60)
    @Email
    private String email;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @NotBlank
    @Size(max = 60)
    private String userType;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;


    @NotBlank
    @Size(min = 6, max = 40)
    private String password;


}