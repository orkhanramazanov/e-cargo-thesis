package com.orkhan.web.out.ecargo.message.request;

import javax.validation.constraints.NotBlank;

public class TruckPhoto {
    @NotBlank
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @NotBlank
    private String photo;

}
