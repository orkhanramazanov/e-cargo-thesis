package com.orkhan.web.out.ecargo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Truck {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Lob
	private String photo;


	private String truckType;


	private Double pricePerKg;


	private String departureLocation;

	private String arrivalLocation;


	private LocalDateTime arrivalTime;


	private LocalDateTime departureTime;


	private Double freeSpace;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public long getTruckDriverId() {
		return truckDriverId;
	}

	public void setTruckDriverId(long truckDriverId) {
		this.truckDriverId = truckDriverId;
	}

	private String truckName;

	private Long truckDriverId;
	
	
}
