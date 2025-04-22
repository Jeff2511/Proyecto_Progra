/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_progra;

/**
 *
 * @author isaac
 */
public class Evento {
    private String idEvento;       // Formato: EVT-####
    private String nombre;
    private String ubicacion;
    private String fecha;
    private int capacidadMaxima;
    private String tipoEvento;
    private int entradasVendidas;  // Conteo de entradas vendidas

    public Evento(String idEvento, String nombre, String ubicacion, String fecha,
                  int capacidadMaxima, String tipoEvento) {
        this.idEvento = idEvento;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.capacidadMaxima = capacidadMaxima;
        this.tipoEvento = tipoEvento;
        this.entradasVendidas = 0;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getFecha() {
        return fecha;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public int getEntradasVendidas() {
        return entradasVendidas;
    }

    public void incrementarEntradasVendidas() {
        this.entradasVendidas++;
    }

    // Indica si ya no hay cupo
    public boolean estaLleno() {
        return entradasVendidas >= capacidadMaxima;
    }
}

