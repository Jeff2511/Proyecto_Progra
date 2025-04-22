/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_progra;

/**
 *
 * @author isaac
 */
public class Usuario {
    private String idUsuario;     // Formato: USR-####
    private String nombre;        // Nombre completo
    private String correo;        // Correo electrónico
    private String tipoUsuario;

    // Arreglo de Entradas (máx. 50)
    private Entrada[] entradasCompradas;
    private int contadorEntradasCompradas;

    public Usuario(String idUsuario, String nombre, String correo, String tipoUsuario) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.entradasCompradas = new Entrada[0];
        this.contadorEntradasCompradas = 0;
        this.tipoUsuario = tipoUsuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTipo(){
        return tipoUsuario;
    }

    public void agregarEntrada(Entrada e) {
        // Crear nuevo arreglo con espacio para una entrada adicional
        Entrada[] nuevoArray = new Entrada[contadorEntradasCompradas + 1];
    
        // Copiar elementos anteriores
        for (int i = 0; i < contadorEntradasCompradas; i++) {
            nuevoArray[i] = entradasCompradas[i];
        }
    
        // Agregar la nueva entrada
        nuevoArray[contadorEntradasCompradas] = e;
    
        // Reemplazar el arreglo viejo con el nuevo
        entradasCompradas = nuevoArray;
        contadorEntradasCompradas++;
    }
    

    // Cuenta cuántas entradas tiene este usuario para un mismo evento
    public int contarEntradasPorEvento(String idEvento) {
        int contador = 0;
        // Normalizo el idEvento para la comparación
        String idEventoNormalizado = idEvento.trim().toUpperCase();
        for (int i = 0; i < contadorEntradasCompradas; i++) {
            if (entradasCompradas[i] != null) {
                String idEntrada = entradasCompradas[i].getIdEvento().trim().toUpperCase();
                if (idEntrada.equals(idEventoNormalizado)) {
                    contador++;
                }
            }
        }
        return contador;
    }
    

    public Entrada[] getEntradasPorEvento(String idEvento) {
        // Normalizamos el idEvento para evitar problemas de espacios o mayúsculas/minúsculas.
        String idEventoNormalizado = idEvento.trim().toUpperCase();
        
        // Primer pase: contar coincidencias
        int count = 0;
        for (int i = 0; i < contadorEntradasCompradas; i++) {
            if (entradasCompradas[i] != null) {
                // Normalizamos el id de la entrada
                String idEntrada = entradasCompradas[i].getIdEvento().trim().toUpperCase();
                if (idEntrada.equals(idEventoNormalizado)) {
                    count++;
                }
            }
        }
        
        // Crear el arreglo filtrado con el tamaño exacto de coincidencias
        Entrada[] filtradas = new Entrada[count];
        
        // Segundo pase: asignar las entradas que coinciden
        int index = 0;
        for (int i = 0; i < contadorEntradasCompradas; i++) {
            if (entradasCompradas[i] != null) {
                String idEntrada = entradasCompradas[i].getIdEvento().trim().toUpperCase();
                if (idEntrada.equals(idEventoNormalizado)) {
                    filtradas[index] = entradasCompradas[i];
                    index++;
                }
            }
        }
        return filtradas;
    }
    
}

