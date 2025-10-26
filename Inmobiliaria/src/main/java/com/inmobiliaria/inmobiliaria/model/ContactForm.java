package com.inmobiliaria.inmobiliaria.model;

import java.time.LocalDate; // Importar si deseas usar LocalDate para la fecha

public class ContactForm {

    private String name;
    private String email;
    private String phone;
    private String message;
    private LocalDate desiredDate; // O String, si prefieres manejarlo como texto
    private boolean followUp;

    // --- Getters y Setters ---

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDesiredDate() {
        return desiredDate;
    }

    public void setDesiredDate(LocalDate desiredDate) {
        this.desiredDate = desiredDate;
    }

    // Para el checkbox, el getter debe ser isFollowUp
    public boolean isFollowUp() {
        return followUp;
    }

    public void setFollowUp(boolean followUp) {
        this.followUp = followUp;
    }
}