/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parcialdomiciliario.entidades;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Nicolas
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notas.findAll", query = "SELECT n FROM Notas n"),
    @NamedQuery(name = "Notas.findById", query = "SELECT n FROM Notas n WHERE n.id = :id"),
    @NamedQuery(name = "Notas.findByTitulo", query = "SELECT n FROM Notas n WHERE n.titulo = :titulo"),
    @NamedQuery(name = "Notas.findByContenido", query = "SELECT n FROM Notas n WHERE n.contenido = :contenido"),
    @NamedQuery(name = "Notas.findByFecha", query = "SELECT n FROM Notas n WHERE n.fecha = :fecha"),
    @NamedQuery(name = "Notas.findByColor", query = "SELECT n FROM Notas n WHERE n.color = :color"),
    @NamedQuery(name = "Notas.findByCategoria", query = "SELECT n FROM Notas n WHERE n.categoria = :categoria")})
public class Notas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Size(max = 40)
    private String titulo;
    @Size(max = 255)
    private String contenido;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 7)
    private String color;
    @Size(max = 50)
    private String categoria;

    public Notas() {
    }

    public Notas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notas)) {
            return false;
        }
        Notas other = (Notas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.parcialdomiciliario.entidades.Notas[ id=" + id + " ]";
    }
    
}
