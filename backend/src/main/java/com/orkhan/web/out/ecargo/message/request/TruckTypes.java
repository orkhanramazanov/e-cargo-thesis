package com.orkhan.web.out.ecargo.message.request;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class TruckTypes {
    @NotBlank
    private List<String> types;

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
