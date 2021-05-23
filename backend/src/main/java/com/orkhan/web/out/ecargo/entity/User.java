package com.orkhan.web.out.ecargo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;


@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "username"
        }),
        @UniqueConstraint(columnNames = {
            "email"
        })
})
public class User{
	  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min=3, max = 50)
    private String firstname;
    @NotBlank
    @Size(min=3, max = 50)
    private String surname;
    @NotBlank
    @Size(min=3, max = 50)
    private String phone;
    @NotBlank
    @Size(min=3, max = 50)
    private String country;

    @NotBlank
    @Size(min=3, max = 50)
    private String username;

    @NotBlank
    @Size(min=3, max = 50)
    private String tokenVerify;

    public String getTokenVerify() {
        return tokenVerify;
    }

    public void setTokenVerify(String tokenVerify) {
        this.tokenVerify = tokenVerify;
    }

    public String getTokenStatus() {
        return tokenStatus;
    }

    public void setTokenStatus(String tokenStatus) {
        this.tokenStatus = tokenStatus;
    }

    @NotBlank
    @Size(min=3, max = 50)
    private String tokenStatus;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Size(min=3, max = 50)
    private String status;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min=6, max = 100)
    private String password;


    @NotBlank
    @Size(min=6, max = 100)
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User() {}

    public User(String firstname, String username, String email, String password,String country,String phone ,String surname)  {
        this.firstname = firstname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.country=country;
        this.phone=phone;
        this.surname=surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}