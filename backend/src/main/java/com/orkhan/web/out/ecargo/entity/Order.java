package com.orkhan.web.out.ecargo.entity;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Table(name = "orders")
@Entity
public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    @NotBlank
    private String lastName;

    @NotBlank
    private String truckDriverId;

    public String getTruckDriverId() {
        return truckDriverId;
    }

    public void setTruckDriverId(String truckDriverId) {
        this.truckDriverId = truckDriverId;
    }

    private LocalDateTime departureTime;

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @NotBlank
    private String paymentType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public Double getItemWidth() {
        return itemWidth;
    }

    public void setItemWidth(Double itemWidth) {
        this.itemWidth = itemWidth;
    }

    @NotBlank
    private String status;

    @NotBlank
    private Double itemWidth;
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @NotBlank
    private String clientId;

    @NotBlank
    private String itemHeight;

    public String getItemHeight() {
        return itemHeight;
    }

    public void setItemHeight(String itemHeight) {
        this.itemHeight = itemHeight;
    }

    @NotBlank
    private LocalDateTime updated;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getTruckId() {
        return truckId;
    }

    public void setTruckId(String truckId) {
        this.truckId = truckId;
    }

    public String getMotherMaidenName() {
        return motherMaidenName;
    }

    public void setMotherMaidenName(String motherMaidenName) {
        this.motherMaidenName = motherMaidenName;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationalityName() {
        return nationalityName;
    }

    public void setNationalityName(String nationalityName) {
        this.nationalityName = nationalityName;
    }

    public String getDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(String dateofBirth) {
        this.dateofBirth = dateofBirth;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(Double itemWeight) {
        this.itemWeight = itemWeight;
    }

    public Double getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Double itemQuantity) {
        this.itemQuantity = itemQuantity;
    }




    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @NotBlank
    private String firstName;
    @NotBlank
    private String nationalityName;
    @NotBlank
    private String truckId ;
    @NotBlank
    private String motherMaidenName;
    @NotBlank
    private String birthPlace;
    @NotBlank
    private String dateofBirth;

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    @NotBlank
    private String email;
    @NotBlank
    private String idType;
    @NotBlank
    private String idNumber;
    @NotBlank
    private String residence;
    @NotBlank
    private String zip;
    @NotBlank
    private String street;
    @NotBlank
    private String city;
    @NotBlank
    private String phone;
    @NotBlank
    private String packageType;
    @NotBlank
    private String itemCategory;

    @Lob
    private String additionalInformation ;
    @NotBlank
    private String itemName;
    @NotBlank
    private Double itemWeight;
    @NotBlank
    private Double itemQuantity;
    @NotBlank
    private Double itemLength;

    public Double getItemLength() {
        return itemLength;
    }

    public void setItemLength(Double itemLength) {
        this.itemLength = itemLength;
    }

    public Double getItemDiameter() {
        return itemDiameter;
    }

    public void setItemDiameter(Double itemDiameter) {
        this.itemDiameter = itemDiameter;
    }

    @NotBlank
    private Double itemDiameter ;
    @NotBlank
    private Double totalPrice;
}
