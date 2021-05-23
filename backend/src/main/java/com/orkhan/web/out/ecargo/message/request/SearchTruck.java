package com.orkhan.web.out.ecargo.message.request;

import org.apache.tomcat.jni.Local;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;

public class SearchTruck {

    @NotBlank
    private String pickUpLocation;

    @NotBlank
    private String dropOffLocation;

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }


    @NotBlank
    private LocalDateTime pickUpTime;


    public LocalDateTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalDateTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public LocalDateTime getDropOffTime() {
        return dropOffTime;
    }

    public void setDropOffTime(LocalDateTime dropOffTime) {
        this.dropOffTime = dropOffTime;
    }

    @NotBlank
    private LocalDateTime dropOffTime;
}
