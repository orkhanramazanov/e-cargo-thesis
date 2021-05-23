package com.orkhan.web.out.ecargo.message.request;

import javax.validation.constraints.NotBlank;

public class ResetPassword {
    @NotBlank
    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
