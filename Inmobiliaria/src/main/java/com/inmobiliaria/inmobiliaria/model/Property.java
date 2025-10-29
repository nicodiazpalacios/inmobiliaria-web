package com.inmobiliaria.inmobiliaria.model;

import java.math.BigDecimal;

public class Property {

    private Long id;
    private String address;
    private String type;
    private int yearBuilt;
    private int bedrooms;
    private int bathrooms;
    private int squareFootage;
    private BigDecimal price;
    private String imageUrl;

    public Property() {
    }

    // Constructor completo (opcional, pero útil para datos mock)
    public Property(Long id, String address, String type, int yearBuilt, int bedrooms, int bathrooms, int squareFootage,
            BigDecimal price, String imageUrl) {
        this.id = id;
        this.address = address;
        this.type = type;
        this.yearBuilt = yearBuilt;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.squareFootage = squareFootage;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // --- Getters y Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(int yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getSquareFootage() {
        return squareFootage;
    }

    public void setSquareFootage(int squareFootage) {
        this.squareFootage = squareFootage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}