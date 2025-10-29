package com.inmobiliaria.inmobiliaria.model;

import java.math.BigDecimal;

public class PropertyDetail {

    private Long id;
    private String title;
    private String description;
    private String type; // Ej: "Apartamentos" o "Casas"
    private int squareFootage;
    private int bedrooms;
    private int bathrooms;
    private int parkingSpaces;
    private BigDecimal price;
    private String mainImageUrl;
    private String agentName;
    private String agentTitle;
    private String agentImageUrl;

    public PropertyDetail() {
    }

    // Constructor completo para simulación (Opcional)
    public PropertyDetail(Long id, String title, String description, String type, int squareFootage, int bedrooms,
            int bathrooms, int parkingSpaces, BigDecimal price, String mainImageUrl, String agentName,
            String agentTitle, String agentImageUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.squareFootage = squareFootage;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.parkingSpaces = parkingSpaces;
        this.price = price;
        this.mainImageUrl = mainImageUrl;
        this.agentName = agentName;
        this.agentTitle = agentTitle;
        this.agentImageUrl = agentImageUrl;
    }

    // --- Getters y Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSquareFootage() {
        return squareFootage;
    }

    public void setSquareFootage(int squareFootage) {
        this.squareFootage = squareFootage;
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

    public int getParkingSpaces() {
        return parkingSpaces;
    }

    public void setParkingSpaces(int parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentTitle() {
        return agentTitle;
    }

    public void setAgentTitle(String agentTitle) {
        this.agentTitle = agentTitle;
    }

    public String getAgentImageUrl() {
        return agentImageUrl;
    }

    public void setAgentImageUrl(String agentImageUrl) {
        this.agentImageUrl = agentImageUrl;
    }
}