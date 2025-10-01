/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parcialdomiciliario.entidades;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class GestorBD {

    @PersistenceContext(unitName = "parcialdomiciliario_war_1.0") 
    private EntityManager em;

    @Produces
    @RequestScoped
    public EntityManager generarEM() {
        return em;
    }
}



