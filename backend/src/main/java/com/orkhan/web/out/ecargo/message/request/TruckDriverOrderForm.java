package com.orkhan.web.out.ecargo.message.request;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class TruckDriverOrderForm {
    @NotBlank
    private String photo;

    @NotBlank
    private String truckType;

    @NotBlank
    private Double pricePerKg;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTruckType() {
        return truckType;
    }

    public void setTruckType(String truckType) {
        this.truckType = truckType;
    }

    public Double getPricePerKg() {
        return pricePerKg;
    }

    public void setPricePerKg(Double pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public Double getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(Double freeSpace) {
        this.freeSpace = freeSpace;
    }

    public String getTruckName() {
        return truckName;
    }

    public void setTruckName(String truckName) {
        this.truckName = truckName;
    }

    @NotBlank
    private String departureLocation;

    @NotBlank
    private String arrivalLocation;

    @NotBlank
    private LocalDateTime arrivalTime;

    @NotBlank
    private LocalDateTime departureTime;

    @NotBlank
    private Double freeSpace;


    @NotBlank
    private String truckName;
}
