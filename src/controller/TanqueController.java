/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TanqueController {
    private JProgressBar tanque;
    private JProgressBar tuberiaCasa;
    private JProgressBar TuberiaControlNivel;
    private JProgressBar TuberiaTransmisorDeNivel;
    private JProgressBar TuberiaValvula;
    private JLabel valvula;
    private JTextField  txtPorcentaje;
    private JButton btnIniciar;
    private JPanel ColorValvula;
    private boolean isRunning = false;

    public TanqueController(JProgressBar Tanque, JProgressBar tuberiaCasa, JProgressBar TuberiaControlNivel, 
                            JProgressBar TuberiaTransmisorDeNivel, JProgressBar TuberiaValvula, JLabel valvula, 
                            JTextField  txtPorcentaje, JButton btnIniciar, JPanel ColorValvula) {
        this.tanque = Tanque;
        this.tuberiaCasa = tuberiaCasa;
        this.valvula = valvula;
        this.txtPorcentaje = txtPorcentaje;
        this.btnIniciar = btnIniciar;
        this.ColorValvula = ColorValvula;
        this.TuberiaControlNivel = TuberiaControlNivel;
        this.TuberiaTransmisorDeNivel = TuberiaTransmisorDeNivel;
        this.TuberiaValvula = TuberiaValvula;

        // Deshabilitar el botón al iniciar la simulación
        btnIniciar.addActionListener(e -> iniciarSimulacion());
    }

    public void iniciarSimulacion() {
    if (isRunning) return; // Evitar múltiples hilos

    isRunning = true;
    estadoInicio();

    // Hilo para el llenado del tanque
    new Thread(() -> {
        try {
            // Llenar el tanque hasta 100%
            for (int seg = 0; seg <= 100; seg++) {
                actualizarTanque(seg);
                Thread.sleep(100);
            } 

            // SEÑAL DEL TRANSMISOR DE CONTROL A LA VALVULA PARA CERRARSE 
            TuberiaValvula.setValue(100);

            // Cambiar color de la válvula a rojo
            SwingUtilities.invokeLater(() -> ColorValvula.setBackground(java.awt.Color.RED));

            // Vaciar el tanque de 100% a 0%
            for (int i = 100; i >= 0; i--) {
                actualizarTanque(i);
                tuberiaCasa.setValue(100); // Asegúrate de que esto sea correcto
                Thread.sleep(200);
            }
            
            tuberiaCasa.setValue(0);
            JOptionPane.showMessageDialog(null, "Tanque Vaciado Exitosamente");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            isRunning = false; // Permitir reiniciar la simulación
        }
    }).start();

    // Hilo para simular el estado de la válvula
    new Thread(() -> {
        SwingUtilities.invokeLater(() -> ColorValvula.setBackground(java.awt.Color.GREEN)); // Cambiar a verde
        try {
            for (int i = 0; i < 100; i++) {
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }).start();
}


    private void estadoInicio() {
        tanque.setValue(0);
        tuberiaCasa.setValue(0);
        txtPorcentaje.setText("0%");
    }

    private void actualizarTanque(int nivel) {
        tanque.setValue(nivel);
        txtPorcentaje.setText(nivel + "%");
        TuberiaTransmisorDeNivel.setValue(100);
        TuberiaControlNivel.setValue(100);
    }
}
