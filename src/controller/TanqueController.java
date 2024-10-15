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
    private JProgressBar TuberiaValvula;
    private JLabel valvula;
    private JTextField txtPorcentaje;
    private JButton btnIniciar;
    private JPanel ColorValvula;
    private boolean isRunning = false;

    // Variables para almacenar el estado de progreso
    private int progresoTanque = 0;
    private int progresoTuberia = 0;

    public TanqueController(JProgressBar Tanque, JProgressBar tuberiaCasa, JProgressBar TuberiaControlNivel,
            JProgressBar TuberiaTransmisorDeNivel, JProgressBar TuberiaValvula, JLabel valvula,
            JTextField txtPorcentaje, JButton btnIniciar, JPanel ColorValvula) {
        this.tanque = Tanque;
        this.tuberiaCasa = tuberiaCasa;
        this.valvula = valvula;
        this.txtPorcentaje = txtPorcentaje;
        this.btnIniciar = btnIniciar;
        this.ColorValvula = ColorValvula;
        this.TuberiaValvula = TuberiaValvula;

        // Deshabilitar el botón al iniciar la simulación
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
                //Tuberia Valvula
                while (isRunning) {
                    //Llenar
                    for (progresoTuberia = progresoTuberia; progresoTuberia < 100; progresoTuberia++) {
                        if (!isRunning) {
                            return;
                        }
                        TuberiaValvula.setValue(progresoTuberia);
                        Thread.sleep(1);
                    }

                    // Llenar el tanque hasta 100%
                    for (progresoTanque = progresoTanque; progresoTanque <= 100; progresoTanque++) {
                        if (!isRunning) {
                            return;
                        }
                        actualizarTanque(progresoTanque);
                        Thread.sleep(100);
                    }

                    // SEÑAL DEL TRANSMISOR DE CONTROL A LA VALVULA PARA CERRARSE 
                    TuberiaValvula.setValue(0);

                    // Cambiar color de la válvula a rojo
                    SwingUtilities.invokeLater(() -> ColorValvula.setBackground(java.awt.Color.RED));

                    // Vaciar el tanque de 100% a 0%
                    for (int i = 100; i >= 0; i--) {
                        if (!isRunning) {
                            return;
                        }
                        actualizarTanque(i);
                        tuberiaCasa.setValue(100); 
                        Thread.sleep(100);

                        if (i <= 60) {
                            // Detener el vaciado
                            tuberiaCasa.setValue(0);
                            // Iniciar el llenado nuevamente desde el 60%
                            llenarTanqueDesde60(60);
                            break; // Salir del bucle de vaciado
                        }

                        Thread.sleep(100);
                    }
                }
                tuberiaCasa.setValue(0);
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
                    if (!isRunning) {
                        return;
                    }
                    Thread.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void detenerSimulacion() {
        isRunning = false;
    }

    private void estadoInicio() {
        

    }

    private void llenarTanqueDesde60(int inicioDesde) {
        new Thread(() -> {
        try {
            for (progresoTanque = inicioDesde; progresoTanque <= 100; progresoTanque++) {
                if (!isRunning) {
                    return; // Verificar si se debe detener
                }
                // Actualizar el tanque y el porcentaje en el hilo de eventos de Swing
                SwingUtilities.invokeLater(() -> actualizarTanque(progresoTanque));
                Thread.sleep(100); // Tiempo de llenado
            }
            // Mensaje de llenado exitoso
            SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null, "Tanque Llenado Exitosamente"));
        } catch (InterruptedException e) {
            e.printStackTrace(); // Manejar la excepción de interrupción
        }
    }).start();
    }

    private void actualizarTanque(int nivel) {
        tanque.setValue(nivel);
        txtPorcentaje.setText(nivel + "%");
    }
}
