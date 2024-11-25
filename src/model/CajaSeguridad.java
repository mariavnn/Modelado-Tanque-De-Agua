/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Victoria
 */
public class CajaSeguridad {
    private JTextField alerta; // Cambiado a JTextField

    public CajaSeguridad(JTextField alerta) {
        this.alerta = alerta;
    }
    
    public void mostrarAlerta(String mensaje) {
        SwingUtilities.invokeLater(() -> {
            // Cambiar el texto del JTextField de alerta
            alerta.setText(mensaje);
            alerta.setForeground(Color.RED); 
        });
    }
    /*
    private TransmisorNivel transmisorNivel;
    private ControlNivel controlNivel;
    private JProgressBar tanque;
   
    private boolean enEmergencia = false;
   

    public CajaSeguridad(TransmisorNivel transmisorNivel, ControlNivel controlNivel, JProgressBar tanque, JTextField alerta) {
        this.transmisorNivel = transmisorNivel;
        this.controlNivel = controlNivel;
        this.tanque = tanque;
        this.alerta = alerta; 
       
    }

    // Método principal de monitoreo
    public void monitorear() {
        new Thread(() -> {
            try {
                while (true) {
                    if(isEnEmergencia()) break;
                    
                    
                    double nivelTransmisor = transmisorNivel.medirNivel();
                    System.out.println("NIVEL TRANSMISOR " + nivelTransmisor);
                    double nivelControl = controlNivel.obtenerNivelActual();
                    System.out.println("CONTROL NIVEL " + nivelControl);
                    

                    // Verificar sincronización de niveles
                    if (Math.abs(nivelTransmisor - nivelControl) > 0.05) { // Tolerancia del 5%
                        mostrarAlerta("Desincronización detectada entre el transmisor y el control de nivel.");
                        activarEmergencia();
                    }

                    // Verificar niveles del tanque y  sus alertas
                    if (nivelTransmisor >= 90.0) {
                        mostrarAlerta("¡Alerta! El tanque está desbordándose.");
                        activarEmergencia();
                        
                       
                    }else if (nivelTransmisor <= 30.0) {
                        mostrarAlerta("¡Alerta! Nivel bajo de agua detectado.");
                        //activarEmergencia();
                    }else{
                        mostrarAlerta("El tanque esta funcionando correctamente");
                        desactivarEmergencia();
                    }
                    // Pausa antes de la siguiente verificación
                    Thread.sleep(500); // 500 ms
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

   
    
    private void activarEmergencia() {
        enEmergencia = true;
        System.out.println("Emergencia activada: ");
    }

    private void desactivarEmergencia() {
        enEmergencia = false;
        System.out.println("Emergencia desactivada: ");
    }
    
    public boolean isEnEmergencia() {
        return enEmergencia;
    }
    */
}
