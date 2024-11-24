/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.TanqueController;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Victoria
 */
public class CajaSeguridad {
    private TransmisorNivel transmisorNivel;
    private ControlNivel controlNivel;
    private JProgressBar tanque;
    private JTextField alerta; // Cambiado a JTextField
    private EstadoTanque estadoTanque;

    public CajaSeguridad(TransmisorNivel transmisorNivel, ControlNivel controlNivel, JProgressBar tanque, JTextField alerta) {
        this.transmisorNivel = transmisorNivel;
        this.controlNivel = controlNivel;
        this.tanque = tanque;
        this.alerta = alerta; 
        this.estadoTanque = EstadoTanque.NORMAL;
    }

   

    // Método principal de monitoreo
    public void monitorear() {
        new Thread(() -> {
            try {
                while (true) {
                    double nivelTransmisor = transmisorNivel.medirNivel();
                    System.out.println("NIVEL TRANSMISOR " + nivelTransmisor);
                    double nivelControl = controlNivel.obtenerNivelActual();
                    System.out.println("CONTROL NIVEL " + nivelControl);
                    
                    System.out.println(" ESTADO DEL TANQUE " + estadoTanque);

                    // Verificar sincronización de niveles
                    if (Math.abs(nivelTransmisor - nivelControl) > 0.05) { // Tolerancia del 5%
                        mostrarAlerta("Desincronización detectada entre el transmisor y el control de nivel.");
                    }

                    // Verificar niveles del tanque y  sus alertas
                    if (nivelTransmisor > 90.0) {
                        mostrarAlerta("¡Alerta! El tanque está desbordándose.");
                        setEstadoTanque(EstadoTanque.DESBORDANDO);
                       
                    }else if (nivelTransmisor < 30.0) {
                        mostrarAlerta("¡Alerta! Nivel bajo de agua detectado.");
                        setEstadoTanque(EstadoTanque.NIVEL_BAJO);
                    }else{
                        mostrarAlerta("El tanque esta funcionando correctamente");
                        setEstadoTanque(EstadoTanque.NORMAL);
                    }
                    // Pausa antes de la siguiente verificación
                    Thread.sleep(500); // 500 ms
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void mostrarAlerta(String mensaje) {
        SwingUtilities.invokeLater(() -> {
            // Cambiar el texto del JTextField de alerta
            alerta.setText(mensaje);
            alerta.setForeground(Color.RED); 
        });
    }
    
    public EstadoTanque getEstadoTanque() {
        return estadoTanque;
    }

    public void setEstadoTanque(EstadoTanque estadoTanque) {
        this.estadoTanque = estadoTanque;
       
    }

}
