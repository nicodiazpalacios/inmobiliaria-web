package com.inmobiliaria.inmobiliaria.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UpcomingDue {
    private String address;
    private String type;
    private LocalDate dueDate;
    private BigDecimal amount;

    // --- Getters y Setters ---
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}