/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parcialdomiciliario.validadores;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import java.util.Calendar;
import java.util.Date;

@FacesValidator("validadorHoraMaxima")
public class validadorHoraMaxima implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value != null && value instanceof Date) {
            Date fecha = (Date) value;
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            int hora = cal.get(Calendar.HOUR_OF_DAY);
            if (hora > 23) {
                throw new ValidatorException(new FacesMessage(
                        FacesMessage.SEVERITY_FATAL,
                        "Error: La hora no puede ser mayor a 23.",
                        null
                ));
            }
        }
    }
}
