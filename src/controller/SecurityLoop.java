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
    private boolean isRunning;
    private ControlLoop controlLoop;

    private static final double NIVEL_MINIMO = 30; // Nivel mínimo crítico 
    private static final int NIVEL_MAXIMO = 90; // Nivel máximo crítico 

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
    
}
