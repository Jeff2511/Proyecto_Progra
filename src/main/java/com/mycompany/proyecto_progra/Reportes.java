package com.mycompany.proyecto_progra;

public class Reportes {
    private Sistema sistema;

    public Reportes(Sistema sistema) {
        this.sistema = sistema;
    }

    /**
     * 1) Eventos Disponibles: Lista de todos los eventos próximos con detalles.
     */
    public String eventosDisponibles() {
        if (sistema.getContadorEventos() == 0) {
            return "No hay eventos registrados.";
        }
        String reporte = "=== Eventos Disponibles ===\n";
        for (int i = 0; i < sistema.getContadorEventos(); i++) {
            Evento ev = sistema.getListaEventos()[i];
            if (ev != null && ev.getIdEvento() != null && !ev.getIdEvento().isEmpty()) {
                reporte = reporte
                    + "- " + ev.getIdEvento() + ": " + ev.getNombre() + "\n"
                    + "    Ubicación: " + ev.getUbicacion() + "\n"
                    + "    Fecha: "     + ev.getFecha()     + "\n"
                    + "    Tipo: "      + ev.getTipoEvento() + "\n";
            }
        }
        return reporte;
    }

    /**
     * 2) Asistentes por Evento: Quiénes han comprado entradas y cuántos boletos adquirieron.
     */
    public String asistentesPorEvento() {
        if (sistema.getContadorEventos() == 0) {
            return "No hay eventos registrados.";
        }
        String reporte = "=== Asistentes por Evento ===\n";
        for (int i = 0; i < sistema.getContadorEventos(); i++) {
            Evento ev = sistema.getListaEventos()[i];
            if (ev != null && ev.getIdEvento() != null) {
                reporte = reporte + "Evento " 
                    + ev.getIdEvento() + " - " + ev.getNombre() + "\n";
                boolean hayAsistentes = false;
                for (int j = 0; j < sistema.getContadorUsuarios(); j++) {
                    Usuario u = sistema.getListaUsuarios()[j];
                    if (u != null) {
                        int qty = u.contarEntradasPorEvento(ev.getIdEvento());
                        if (qty > 0) {
                            reporte = reporte
                                + "    * " + u.getNombre()
                                + " - " + qty + " entradas\n";
                            hayAsistentes = true;
                        }
                    }
                }
                if (!hayAsistentes) {
                    reporte = reporte + "    (No tiene asistentes registrados)\n";
                }
            }
        }
        return reporte;
    }

    /**
     * 3) Reporte de Ventas: Cantidad de entradas vendidas por evento.
     */
    public String reporteVentas() {
        if (sistema.getContadorEventos() == 0) {
            return "No hay eventos registrados.";
        }
        String reporte = "=== Reporte de Ventas ===\n";
        for (int i = 0; i < sistema.getContadorEventos(); i++) {
            Evento ev = sistema.getListaEventos()[i];
            if (ev != null && ev.getIdEvento() != null) {
                reporte = reporte
                    + "- " + ev.getIdEvento()
                    + ": " + ev.getEntradasVendidas()
                    + " entradas vendidas\n";
            }
        }
        return reporte;
    }
}
