package com.inmobiliaria.inmobiliaria.model;

public class DashboardMetrics {
    private int activeListings;
    private int activeContracts;
    private int pendingPayments;

    // --- Getters y Setters ---
    public int getActiveListings() {
        return activeListings;
    }

    public void setActiveListings(int activeListings) {
        this.activeListings = activeListings;
    }

    public int getActiveContracts() {
        return activeContracts;
    }

    public void setActiveContracts(int activeContracts) {
        this.activeContracts = activeContracts;
    }

    public int getPendingPayments() {
        return pendingPayments;
    }

    public void setPendingPayments(int pendingPayments) {
        this.pendingPayments = pendingPayments;
    }
}