/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.*;
import java.awt.*;

import model.ControlNivel;
import model.TransmisorNivel;
import model.Valvula;

public class TanqueController {

    private JProgressBar tanque;
    private JProgressBar tuberiaCasa;
    private JProgressBar TuberiaValvula;
    private JLabel valvula;
    private JTextField txtPorcentaje;
    private JButton btnIniciar;
    private JPanel ColorValvula;
    private boolean isRunning = false;

    // Variables de lógica del sistema
    private int progresoTanque = 0;
    private int progresoTuberia = 0;
    private ControlNivel controlNivel;
    private TransmisorNivel transmisorNivel;
    private Valvula valvulaModel;

    public TanqueController(JProgressBar tanque, JProgressBar tuberiaCasa, JProgressBar TuberiaValvula,
                            JLabel valvula, JTextField txtPorcentaje, JButton btnIniciar, JPanel ColorValvula,
                            ControlNivel controlNivel, TransmisorNivel transmisorNivel, Valvula valvulaModel) {
        this.tanque = tanque;
        this.tuberiaCasa = tuberiaCasa;
        this.TuberiaValvula = TuberiaValvula;
        this.valvula = valvula;
        this.txtPorcentaje = txtPorcentaje;
        this.btnIniciar = btnIniciar;
        this.ColorValvula = ColorValvula;
        this.controlNivel = controlNivel;
        this.transmisorNivel = transmisorNivel;
        this.valvulaModel = valvulaModel;

        btnIniciar.addActionListener(e -> iniciarSimulacion());
    }

    public void iniciarSimulacion() {
        if (isRunning) {
            return; // Evitar múltiples hilos
        }
        isRunning = true;
        estadoInicio();

        // Hilo para el llenado del tanque
        new Thread(() -> {
            try {
                while (isRunning) {
                    // Llenar el tanque
                    llenarTanque();
                    // Vaciar el tanque
                    vaciarTanque();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                isRunning = false;  // Permitir reiniciar la simulación
            }
        }).start();
    }

    public void detenerSimulacion() {
        isRunning = false;
    }

    private void estadoInicio() {
        // Iniciar la válvula cerrada y tanque vacío
        valvulaModel.abrir(0);
        tanque.setValue(0);
        tuberiaCasa.setValue(0);
    }

    private void llenarTanque() throws InterruptedException {
        for (progresoTanque = 0; progresoTanque <= 100; progresoTanque++) {
            if (!isRunning) return;

            // Simular medición del transmisor
            transmisorNivel.setNivelAgua(progresoTanque / 100.0);
            controlNivel.verificarNivel(transmisorNivel.medirNivel());

            // Actualizar interfaz gráfica
            actualizarTanque(progresoTanque);
            Thread.sleep(100);
        }
    }

    private void vaciarTanque() throws InterruptedException {
        for (int i = 100; i >= 0; i--) {
            if (!isRunning) return;

            // Simular vaciado del tanque y tubería a la casa
            actualizarTanque(i);
            tuberiaCasa.setValue(100);  // Simular la entrada de agua hacia la casa

            if (i <= 60) {
                tuberiaCasa.setValue(0);
                // Volver a llenar el tanque desde el 60%
                llenarTanqueDesde60(60);
                break;
            }

            Thread.sleep(100);
        }
    }

    private void llenarTanqueDesde60(int inicioDesde) throws InterruptedException {
        for (progresoTanque = inicioDesde; progresoTanque <= 100; progresoTanque++) {
            if (!isRunning) return;

            transmisorNivel.setNivelAgua(progresoTanque / 100.0);
            controlNivel.verificarNivel(transmisorNivel.medirNivel());
            actualizarTanque(progresoTanque);

            Thread.sleep(100);
        }
    }

    private void actualizarTanque(int nivel) {
        tanque.setValue(nivel);
        txtPorcentaje.setText(nivel + "%");

        // Actualizar color de la válvula dependiendo de su estado
        SwingUtilities.invokeLater(() -> {
            if (valvulaModel.getApertura() == 100) {
                ColorValvula.setBackground(Color.GREEN);
            } else if (valvulaModel.getApertura() > 0) {
                ColorValvula.setBackground(Color.YELLOW);
            } else {
                ColorValvula.setBackground(Color.RED);
            }
        });
    }
}
