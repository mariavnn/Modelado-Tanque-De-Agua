/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JButton AbrirValvula;
    private JButton CerrarValvula;
    private JPanel ColorValvula;
    private JRadioButton modoAutomatico;
    private JRadioButton modoManual;
    private boolean isRunning = false;

    // Variables de lógica del sistema
    private int progresoTanque = 0;
    private ControlNivel controlNivel;
    private TransmisorNivel transmisorNivel;
    private Valvula valvulaModel;

    public TanqueController(JProgressBar tanque, JProgressBar tuberiaCasa, JProgressBar TuberiaValvula, JLabel valvula, JTextField txtPorcentaje, JButton btnIniciar, JButton AbrirValvula, JButton CerrarValvula, JPanel ColorValvula, JRadioButton modoAutomatico, JRadioButton modoManual, ControlNivel controlNivel, TransmisorNivel transmisorNivel, Valvula valvulaModel) {
        this.tanque = tanque;
        this.tuberiaCasa = tuberiaCasa;
        this.TuberiaValvula = TuberiaValvula;
        this.valvula = valvula;
        this.txtPorcentaje = txtPorcentaje;
        this.btnIniciar = btnIniciar;
        this.AbrirValvula = AbrirValvula;
        this.CerrarValvula = CerrarValvula;
        this.ColorValvula = ColorValvula;
        this.modoAutomatico = modoAutomatico;
        this.modoManual = modoManual;
        this.controlNivel = controlNivel;
        this.transmisorNivel = transmisorNivel;
        this.valvulaModel = valvulaModel;

        // Configurar el estado inicial
        modoAutomatico.setSelected(true);
        AbrirValvula.setEnabled(false);
        CerrarValvula.setEnabled(false);

        btnIniciar.addActionListener(e -> iniciarSimulacion());
        
        // Listeners para los JRadioButton
        modoAutomatico.addActionListener(e -> activarModoAutomatico());
        modoManual.addActionListener(e -> activarModoManual());
        
        // Listeners para los botones de válvula
        AbrirValvula.addActionListener(e -> abrirValvula());
        CerrarValvula.addActionListener(e -> cerrarValvula());
    }
    
    public void detenerSimulacion() {
        isRunning = false;
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
                if (modoAutomatico.isSelected()) {
                    // Llenar el tanque por primera vez
                    System.out.println("LLENADO DEL TANQUE DESDE 0");
                    llenarTanque();

                    // Comenzar el ciclo de llenado y vaciado
                    while (isRunning) {
                        // Si el tanque está lleno, empezar a pasar agua a la casa
                        if (progresoTanque >= 100) {
                            // Pasar agua a la casa hasta que el tanque baje a 60
                            while (isRunning && progresoTanque > 60) {
                                tuberiaCasa.setValue(100); // Simular el flujo de agua hacia la casa
                                progresoTanque--; // Reducir el nivel del tanque
                                System.out.println("PRIMER VACIADO DEL TANQUE");
                                actualizarTanque(progresoTanque); // Actualizar la interfaz gráfica
                                Thread.sleep(100); // Esperar un momento para simular el flujo
                            }
                            tuberiaCasa.setValue(0); // Detener el flujo hacia la casa cuando el tanque esté en 60
                            System.out.println("VACIADO DEL TANQUE A 60");
                        }

                        // Si el tanque llega a 60, comenzar a llenar desde 60 hasta 100
                        if (progresoTanque <= 60) {
                            System.out.println("DENTRO DEL BUCLE DE 60 A 100");
                            while (isRunning && progresoTanque < 100) {
                                llenarTanqueDesde60(); // Llenar de 60 a 100
                            }
                        }
                    }
                } else {
                    vaciarTanqueManual();
                }
            }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                isRunning = false;  // Permitir reiniciar la simulación
            }
        }).start();
    }

    private void activarModoAutomatico() {
        modoManual.setSelected(false);
        AbrirValvula.setEnabled(false);
        CerrarValvula.setEnabled(false);
    }

    private void activarModoManual() {
        modoAutomatico.setSelected(false);
        AbrirValvula.setEnabled(true);
        CerrarValvula.setEnabled(true);
    }

    private void abrirValvula() {
        valvulaModel.abrir(100); // Abrir la válvula completamente
        ColorValvula.setBackground(Color.GREEN);
        llenarTanqueManualmente();
    }

    private void cerrarValvula() {
        valvulaModel.abrir(0); // Cerrar la válvula
        ColorValvula.setBackground(Color.RED);
    }

    private void estadoInicio() {
        // Iniciar la válvula cerrada y tanque vacío
        valvulaModel.abrir(0);
        tanque.setValue(0);
        tuberiaCasa.setValue(0);
    }

    private void llenarTanque() throws InterruptedException {
        System.out.println(progresoTanque);
        for (progresoTanque = 0; progresoTanque <= 100; progresoTanque++) {
            if (!isRunning || !modoAutomatico.isSelected()) return;

            // Simular medición del transmisor
            transmisorNivel.setNivelAgua(progresoTanque / 100.0);
            controlNivel.verificarNivel(transmisorNivel.medirNivel());

            // Actualizar interfaz gráfica
            actualizarTanque(progresoTanque);
            Thread.sleep(100);
        }
    }
    
    private void llenarTanqueDesde60() throws InterruptedException {
    // Llenar el tanque desde 60 hasta 100
    System.out.println(progresoTanque);
    for (progresoTanque = 60; progresoTanque <= 100; progresoTanque++) {
        if (!isRunning || !modoAutomatico.isSelected()) return;

        // Simular medición del transmisor
        transmisorNivel.setNivelAgua(progresoTanque / 100.0);
        controlNivel.verificarNivel(transmisorNivel.medirNivel());

        // Actualizar interfaz gráfica
        actualizarTanque(progresoTanque);
        Thread.sleep(100); // Simular tiempo de llenado
    }
}

    private void vaciarTanque() throws InterruptedException {
        for (int i = 100; i >= 0; i--) {
            if (!isRunning || !modoAutomatico.isSelected()) return;

            // Simular vaciado del tanque y tubería a la casa
            actualizarTanque(i);
            tuberiaCasa.setValue(100);  // Simular la entrada de agua hacia la casa

            if (i <= 60) {
                tuberiaCasa.setValue(0);
                // Volver a llenar el tanque desde el 60%
                
                break;
            }

            Thread.sleep(100);
        }
    }

    private void llenarTanqueManualmente() {
        // Método que se llama al abrir la válvula en modo manual
        new Thread(() -> {
            try {
                while (isRunning && modoManual.isSelected() && progresoTanque < 100) {
                    progresoTanque++;
                    transmisorNivel.setNivelAgua(progresoTanque / 100.0);
                    controlNivel.verificarNivel(transmisorNivel.medirNivel());
                    actualizarTanque(progresoTanque);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void vaciarTanqueManual() throws InterruptedException {
        // Simular vaciado del tanque en modo manual
        if (progresoTanque > 0) {
            progresoTanque--;
            actualizarTanque(progresoTanque);
            tuberiaCasa.setValue(100);  // Simular la entrada de agua hacia la casa
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
