package com.orkhan.web.out.ecargo.message.request;

import javax.validation.constraints.NotBlank;

public class AddCountry {

    @NotBlank
    private String countryName;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
