/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parcialdomiciliario.repositorios;

import com.mycompany.parcialdomiciliario.entidades.Notas;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class RepositorioNotas {

    @PersistenceContext(unitName = "parcialdomiciliario_war_1.0")
    private EntityManager em;

    public void crear(Notas nota) {
        em.persist(nota);
    }

    public void editar(Notas nota) {
        em.merge(nota);
    }

    public void eliminar(Notas nota) {
        Notas aEliminar = em.find(Notas.class, nota.getId());
        if (aEliminar != null) {
            em.remove(aEliminar);
        }
    }

    public Notas buscarPorId(Integer id) {
        return em.find(Notas.class, id);
    }

    public List<Notas> obtenerTodasOrdenadasDesc() {
    return em.createQuery("SELECT n FROM Notas n ORDER BY n.fecha DESC", Notas.class)
             .getResultList();
    }
    
    public List<Notas> obtenerPorCategoria(String categoria) {
    return em.createQuery("SELECT n FROM Notas n WHERE n.categoria = :categoria ORDER BY n.fecha DESC", Notas.class)
             .setParameter("categoria", categoria)
             .getResultList();
}


}

