/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inmobiliariaapp.controladores;

import com.mycompany.inmobiliariaapp.entidades.Notas;
import com.mycompany.inmboliariaapp.repositorios.RepositorioNotas;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Named
@SessionScoped
public class ControladorNotas implements Serializable {

    @Inject
    private RepositorioNotas repositorioNotas;

    private Notas nuevaNota = new Notas();
    private String categoriaSeleccionada;

    private List<Notas> notasFiltradas; // Resultado de filtrarPorCategoria

    public ControladorNotas() {
        nuevaNota.setColor("#ff0000"); // Color por defecto
    }

    // Agregar o editar una nota
    public void agregarNota() {
        // Validar categoría
        if (nuevaNota.getCategoria() == null || nuevaNota.getCategoria().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar una categoría", null));
            return;
        }

        // Validar hora (que no pase de 23)
        if (nuevaNota.getFecha() != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(nuevaNota.getFecha());
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            if (hour > 23) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error: La hora no puede ser mayor a 23.", null));
                return;
            }
        }

        
        if (nuevaNota.getId() != null) {
            editarNota(nuevaNota);
        } else {
            if (nuevaNota.getFecha() == null) {
                nuevaNota.setFecha(new Date()); // Usar fecha actual si no se ingresó
            }
            repositorioNotas.crear(nuevaNota);
        }

        // Limpiar
        nuevaNota = new Notas();
        nuevaNota.setColor("#ff0000");
        
        // Refrescar lista filtrada si había filtro aplicado
        if (categoriaSeleccionada != null && !categoriaSeleccionada.isEmpty()) {
            filtrarPorCategoria();
        }
    }

    // Eliminar
    public void eliminarNota(Notas nota) {
        repositorioNotas.eliminar(nota);
        filtrarPorCategoria(); // refrescar después de eliminar
    }

    // Edición (se usa en botón editar)
    public void cargarNotaParaEditar() {
        // Este método no hace nada por ahora porque el botón JSF ya pasa la nota a editar
    }

    public void editarNota(Notas nota) {
        Notas notaActual = repositorioNotas.buscarPorId(nota.getId());

        boolean huboCambios = !Objects.equals(nota.getTitulo(), notaActual.getTitulo()) ||
                              !Objects.equals(nota.getContenido(), notaActual.getContenido()) ||
                              !Objects.equals(nota.getColor(), notaActual.getColor()) ||
                              !Objects.equals(nota.getFecha(), notaActual.getFecha()) ||
                              !Objects.equals(nota.getCategoria(), notaActual.getCategoria());

        if (huboCambios) {
            repositorioNotas.editar(nota);
        }
    }

    // Filtro por categoría
    public void filtrarPorCategoria() {
        if (categoriaSeleccionada == null || categoriaSeleccionada.isEmpty()) {
            notasFiltradas = repositorioNotas.obtenerTodasOrdenadasDesc();
        } else {
            notasFiltradas = repositorioNotas.obtenerPorCategoria(categoriaSeleccionada);
        }
    }

    // Getters y Setters

    public Notas getNuevaNota() {
        return nuevaNota;
    }

    public void setNuevaNota(Notas nuevaNota) {
        this.nuevaNota = nuevaNota;
    }

    public String getCategoriaSeleccionada() {
        return categoriaSeleccionada;
    }

    public void setCategoriaSeleccionada(String categoriaSeleccionada) {
        this.categoriaSeleccionada = categoriaSeleccionada;
    }

    // Este método es el que se usa en el XHTML para mostrar las notas
    public List<Notas> getNotas() {
        // Si se filtró, devuelve el resultado filtrado
        if (notasFiltradas != null) {
            return notasFiltradas;
        }
        // Si no hay filtro, devuelve todas ordenadas
        return repositorioNotas.obtenerTodasOrdenadasDesc();
    }
}
