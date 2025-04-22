/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

 package com.mycompany.proyecto_progra;

 /**
  *
  * @author isaac
  */
 import javax.swing.JOptionPane;
 
 public class Proyecto_Progra {
     public static void main(String[] args) {
         Sistema sistema = new Sistema();
         int seleccion;
         String[] opciones = {
             "Iniciar sesion",
             "Registrar usuario",
             "Salir"
         };
 
         do {
             seleccion = JOptionPane.showOptionDialog(
                 null,
                 "Menú Principal",
                 "Seleccione una opción",
                 JOptionPane.DEFAULT_OPTION,
                 JOptionPane.QUESTION_MESSAGE,
                 null,
                 opciones,
                 new Object()  // Sin botón por defecto
             );
 
             switch (seleccion) {
                 case 0:
                     login(sistema);
                     break;
                 case 1:
                     registrarUsuario(sistema);
                     break;
                 case 2:
                     JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                     break;
                 default:
                     // Si cierra el diálogo, tratamos como salir
                     seleccion = 2;
                     break;
             }
         } while (seleccion != 2);
 
         System.exit(0);
     }
 
 
 
     // ----------------------------------------------
     // Menú para el Admin
     // ----------------------------------------------
     private static void menuAdmin(Sistema sistema, String idUsuario) {
         boolean continuar = true;
         while (continuar) {
             Object[] opciones = {"Registrar Evento", "Volver al Menú Principal"};
 int opcion = JOptionPane.showOptionDialog(
     null,
     "Menú de Administrador",
     "Input",
     JOptionPane.DEFAULT_OPTION,
     JOptionPane.INFORMATION_MESSAGE,
     null,
     opciones,
     opciones[0]
 );
 
 // El resultado estará en la variable 'opcion'
 // 0 = Registrar Evento, 1 = Volver al Menú Principal, -1 = Cancelar o cerrar
     switch (opcion) {
         case 0: {
             String nombre = JOptionPane.showInputDialog("Nombre del evento:");
             String ubicacion = JOptionPane.showInputDialog("Ubicación:");
             String fecha = JOptionPane.showInputDialog("Fecha y hora (ej: 10/06/2025 20:00):");
 
             String capStr = JOptionPane.showInputDialog("Capacidad máxima:");
             int capacidadMaxima = Integer.parseInt(capStr);
             
             String[] opcionesTipoEvento = {"Concierto", "Fiesta", "Partido", "Conferencia"};
 
             // Mostrar el diálogo de entrada con el menú desplegable
             String tipoEvento = (String) JOptionPane.showInputDialog(
                 null,                             // Componente padre (null significa sin padre)
                 "Seleccione una opción:",         // Mensaje
                 "Menú Desplegable",               // Título del diálogo
                 JOptionPane.PLAIN_MESSAGE,        // Tipo de mensaje
                 null,                             // Icono personalizado (null usa el predeterminado)
                 opcionesTipoEvento,                         // Array de objetos para el menú desplegable
                 opcionesTipoEvento[0]                       // Valor seleccionado por defecto
             );
 
             String resultado = sistema.registrarEvento(nombre, ubicacion, fecha, capacidadMaxima, tipoEvento);
             JOptionPane.showMessageDialog(null, resultado);
             break;
         }
        
     case 1:
     continuar = false;
     break;
 default:
     JOptionPane.showMessageDialog(null, "Opción inválida.");
     break;
     case JOptionPane.CLOSED_OPTION:
         // El usuario cerró la ventana
         break;
 }}}            
             
     private static void login(Sistema sistema){
         // Extraer el ID del usuario (opcionalmente, el usuario podría ya leerlo del mensaje).
         String idUsuario = JOptionPane.showInputDialog("Ingrese su ID de usuario (USR-####) para continuar:");
         Usuario usuario = sistema.buscarUsuarioPorId(idUsuario);
         if(usuario != null){
             if(usuario.getTipo() == "Administrador"){
                 menuAdmin(sistema, idUsuario);
             }
             else{
                 flujoUsuarioEstandar(sistema, usuario ,idUsuario);
             }
         }
         else {
             JOptionPane.showMessageDialog(null, "Usuario inválido o no existente.");
 
         }
     }
 
     private static void registrarUsuario(Sistema sistema){
         // 1. Registrar Usuario
         String nombre = JOptionPane.showInputDialog("Ingrese su nombre completo:");
         String correo = JOptionPane.showInputDialog("Ingrese su correo:");
         String[] opcionesTipoUsuario = { "Consumidor", "Administrador" };
 
         // Mostrar el diálogo de entrada con el menú desplegable
         String seleccionTipoUsuario = (String) JOptionPane.showInputDialog(
             null,                             // Componente padre (null significa sin padre)
             "Seleccione una opción:",         // Mensaje
             "Menú Desplegable",               // Título del diálogo
             JOptionPane.PLAIN_MESSAGE,        // Tipo de mensaje
             null,                             // Icono personalizado (null usa el predeterminado)
             opcionesTipoUsuario,                         // Array de objetos para el menú desplegable
             opcionesTipoUsuario[0]                       // Valor seleccionado por defecto
         );
 
         String resultadoUsuario = sistema.registrarUsuario(nombre, correo, seleccionTipoUsuario);
         JOptionPane.showMessageDialog(null, resultadoUsuario);
     }
 
    
     private static void flujoUsuarioEstandar(Sistema sistema, Usuario usuario, String idUsuario) {
 
         boolean continuar = true;
         while (continuar) {
             String opcion = JOptionPane.showInputDialog(
                 "Menú de Usuario/Consumidor\n"
               + "1. Listar eventos\n"
               + "2. Comprar entradas\n"
               + "3. Ver entradas\n"
               + "4. Volver al Menú Principal\n"
               + "Seleccione una opción:"
             );
             
             if (opcion == null) {
                 // Cerrar diálogo
                 return;
             }
 
             switch (opcion) {
                 case "1": {
                     // 2. Mostrar lista de eventos
                     String lista = sistema.listarEventos();
                     JOptionPane.showMessageDialog(null, lista);
 
                     if (lista.contains("No hay eventos registrados")) {
                         return;
                     }
                     break;
                 }
                 case "2": {
                                 // 3. Pedir ID del evento
                     String idEvento = JOptionPane.showInputDialog("Ingrese el ID del evento (EVT-####) al que desea comprar entradas:");
 
                     // 4. Pedir cuántas entradas comprar
                     String numStr = JOptionPane.showInputDialog("¿Cuántas entradas desea comprar? (Máx 5 por evento):");
                     int numEntradas = Integer.parseInt(numStr); 
 
                     // 5. Realizar la compra
                     for (int i = 0; i < numEntradas; i++) {
                         String resultadoCompra = sistema.comprarEntrada(idUsuario, idEvento);
                         
                         if (!resultadoCompra.startsWith("Compra exitosa")) {
                             JOptionPane.showMessageDialog(null, resultadoCompra);
                             break;
                         } else {
                             JOptionPane.showMessageDialog(null, resultadoCompra);
                         }
                     }
                     break;
                 }
                 case "3":
                     String idEvento = JOptionPane.showInputDialog("Ingrese el ID del evento (EVT-####) del que desea ver sus entradas:");
                     Entrada[] entradasEncontradas = sistema.buscarEntradasPorUsuario(idUsuario, usuario, idEvento);
                     if(entradasEncontradas == null){
                         String mensaje = "No se han encontrado entradas para este evento";
                         JOptionPane.showMessageDialog(null, mensaje);
                         break;
                     }
                     if(entradasEncontradas.length == 0){
                         String mensaje = "No se han encontrado entradas para este evento";
                         JOptionPane.showMessageDialog(null, mensaje);
                         break;
                     }
                     for(int i = 0 ; i < entradasEncontradas.length; i++){
                        if (entradasEncontradas[i] != null){
                         String mensaje = "Código de entrada: "+entradasEncontradas[i].getCodigoEntrada()+" | ID del Evento: "+entradasEncontradas[i].getIdEvento();
                         JOptionPane.showMessageDialog(null, mensaje);
                        }
                     }
                     break;
                 case "4":
                     continuar = false;
                     break;
                 default:
                     JOptionPane.showMessageDialog(null, "Opción inválida.");
                     break;
             }
         }
         
 
       
         
 
         
 
         
     }
 }
 
 