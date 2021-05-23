package com.orkhan.web.out.ecargo.message.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PasswordForm {
    @NotBlank
    private String currentPassword;

    @NotBlank
    private String newPassword;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
