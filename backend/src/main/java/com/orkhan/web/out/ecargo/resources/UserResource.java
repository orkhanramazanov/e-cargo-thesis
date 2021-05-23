package com.orkhan.web.out.ecargo.resources;

import com.orkhan.web.out.ecargo.entity.User;

public class UserResource {
  private long id;



  private String username;

  private String email;

    private String firstname;
    private String surname;
    private String country;
    private String phone;
    private String password;
    private String role;
    private String status;

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserResource() {}

  public UserResource(User user) {
    this.id = user.getId();
    this.username = user.getUsername();
    this.email = user.getEmail();
    this.country=user.getCountry();
    this.firstname= user.getFirstname();;
    this.surname=user.getSurname();
    this.phone=user.getPhone();
    this.role=user.getRole();
    this.status=user.getStatus();
    this.password=user.getPassword();
  }

  public Long getId() {
      return id;
  }

  public void setId(long id) {
      this.id = id;
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
}
