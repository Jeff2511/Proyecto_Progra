/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_progra;

/**
 *
 * @author isaac
 */
public class Entrada {
    private String codigoEntrada;  // Formato: TKT-####
    private String nombreUsuario;  // Nombre del usuario que compró
    private String idEvento;       // ID del evento
    private boolean esValida;

    public Entrada(String codigoEntrada, String nombreUsuario, String idEvento) {
        this.codigoEntrada = codigoEntrada;
        this.nombreUsuario = nombreUsuario;
        this.idEvento = idEvento;
        this.esValida = true;
    }

    public String getCodigoEntrada() {
        return codigoEntrada;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public boolean getValidez(){
        return esValida;
    }

    /** Marca la entrada como usada (no válida). */
    public void invalidar() {
        this.esValida = false;
    }
}

