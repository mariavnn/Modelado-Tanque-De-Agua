/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.CajaSeguridad;
import model.TransmisorNivel;

/**
 *
 * @author Victoria
 */
public class SecurityLoop {
    private TransmisorNivel transmisorNivel;
    private CajaSeguridad cajaSeguridad;
    private ControlLoop controlLoop;

    private static final double NIVEL_MINIMO = 30; // Nivel mínimo crítico 
    private static final int NIVEL_MAXIMO = 90; // Nivel máximo crítico 
    
    private boolean isRunning;
    private boolean valvulaSalida;
    private boolean valvulaEntrada;
    
    public SecurityLoop(TransmisorNivel transmisorNivel, CajaSeguridad cajaSeguridad, ControlLoop controlLoop) {
        this.transmisorNivel = transmisorNivel;
        this.cajaSeguridad = cajaSeguridad;
        this.controlLoop = controlLoop;
        this.isRunning = false;
    }

    public void iniciar() {
        isRunning = true;
        new Thread(this::monitorizarNivel).start();
        System.out.println("SecurityLoop iniciado.");
    }

    public void detener() {
        isRunning = false;
        System.out.println("SecurityLoop detenido.");
    }

    private void monitorizarNivel() {
        System.out.println("Monitoreo del nivel en el sistema de seguridad ");
        while (isRunning) {
            double nivelActual = transmisorNivel.obtenerNivelActual();
            System.out.println("Nivel actual " + nivelActual);

            if (nivelActual < NIVEL_MINIMO) {
                cajaSeguridad.mostrarAlerta("Nivel critico bajo detectado");
                
            } else if (nivelActual >= NIVEL_MAXIMO) {
                cajaSeguridad.mostrarAlerta("Nivel crítico alto detectado: ");
                controlLoop.detenerSimulacion();
                
                // Cerrar la válvula de entrada y abrir las de salida
                cerrarValvulaEntrada();
                abrirValvulasSalida();
             
            }else{
                cajaSeguridad.mostrarAlerta("El tanque esta funcionando bien!");
            }

            try {
                Thread.sleep(500); // Esperar antes de realizar la siguiente verificación
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("El SecurityLoop fue interrumpido.");
            }
        }
    }
    
    private void cerrarValvulaEntrada() {
        // Suponiendo que tienes acceso a la lógica de control de la válvula de entrada
        System.out.println("Cerrando válvula de entrada...");
        controlLoop.cerrarValvula();
        valvulaEntrada = controlLoop.isValvulaAbierta();
        System.out.println("VALVULA ENTRADA CERRADA " + valvulaEntrada);
        try{
           if (!valvulaEntrada) {
            // Iniciar el proceso de vaciar el tanque
            System.out.println("Iniciando vaciado...");
            controlLoop.vaciarTanque(); // Usar la función para vaciar el tanque
            
            // Continuar el vaciado hasta que llegue a un nivel de 60
            while (controlLoop.getProgresoTanque() > 60) {
                System.out.println("Vaciando tanque... Nivel actual: " + controlLoop.getProgresoTanque());
                Thread.sleep(100); // Ajusta el tiempo según el flujo de vaciado
            }
            
            // Una vez que el tanque llegue a 60, volver a llenar
            System.out.println("Nivel del tanque en 60%. Volviendo a llenar...");
            controlLoop.llenarTanqueAutomatico(60, 80, "60"); // Llenar de 60 a 80
        }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
       
        // Implementa la lógica específica para cerrar la válvula de entrada aquí
    }

    private void abrirValvulasSalida() {
        // Suponiendo que tienes acceso a la lógica de control de las válvulas de salida
        System.out.println("Abriendo válvulas de salida...");
        // Implementa la lógica específica para abrir las válvulas de salida aquí
    }
}
