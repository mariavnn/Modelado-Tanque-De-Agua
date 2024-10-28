/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private boolean valvulaAbierta;
    private boolean vaciandoTanque = false;
    private boolean llenandoTanque = false;
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
        progresoTanque = 0;
        actualizarTanque(progresoTanque);
        
        isRunning = false;
        llenandoTanque = false;  // Detener cualquier proceso de llenado en curso
        vaciandoTanque = false;  // Detener cualquier proceso de vaciado en curso
        valvulaAbierta = false;  // Cerrar la válvula para evitar llenado
        AbrirValvula.setEnabled(false);
        CerrarValvula.setEnabled(false);
        System.out.println("Simulación detenida.");
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
                        valvulaAbierta = true;
                        llenarTanque();
                        

                        // Comenzar el ciclo de llenado y vaciado
                        while (isRunning && modoAutomatico.isSelected()) {
                            // Si el tanque está lleno, empezar a pasar agua a la casa
                            if (progresoTanque >= 100 || progresoTanque >= 80) {
                                // Pasar agua a la casa hasta que el tanque baje a 60
                                vaciarTanque();
                            }

                            // Si el tanque llega a 60, comenzar a llenar desde 60 hasta 80
                            if (progresoTanque <= 60) {
                                System.out.println("DENTRO DEL BUCLE DE 60 A 80");
                                while (isRunning && progresoTanque < 80) {
                                    valvulaAbierta = false;
                                    llenarTanqueDesde60(); // Llenar de 60 a 80
                                }
                            }
                        }
                    } else if (modoManual.isSelected()) {
                        System.out.println("VALVULA ABIERTA " + valvulaAbierta);
                        // Lógica para el modo manual
                        System.out.println("LOGICA PARA EL MODO MANUAL");
                        if (valvulaAbierta) {
                            System.out.println("LLENAR TANQUE MANUALMENTE if");
                            llenarTanqueManualmente();
                            
                        }else{
                            System.out.println("VACIAR TANQUE MANUALMENTE if");
                            vaciarTanqueManual();
                        }
                    }else{
                        System.out.println("SELECCIONA UN MODO ");
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
        detenerSimulacion(); // Detener la simulación si está corriendo
    }

    private void activarModoManual() {
        modoAutomatico.setSelected(false);
        AbrirValvula.setEnabled(true);
        CerrarValvula.setEnabled(true);
    }

    private synchronized void abrirValvula() {
    System.out.println("ABRIR VALVULA");
    valvulaAbierta = true;
    valvulaModel.abrir(100); // Abrir la válvula completamente
    ColorValvula.setBackground(Color.GREEN);
    
    if (modoManual.isSelected()) {
        // Detener cualquier proceso de vaciado antes de empezar a llenar
        vaciandoTanque = false;
        llenarTanqueManualmente(); // Iniciar el llenado manual
    }
}

    private synchronized void cerrarValvula() {
        System.out.println("CERRAR VALVULA");
        valvulaAbierta = false;
        valvulaModel.abrir(0); // Cerrar la válvula
        ColorValvula.setBackground(Color.RED);

        if (modoManual.isSelected()) {
            // Detener cualquier proceso de llenado antes de empezar a vaciar
            llenandoTanque = false;
            vaciarTanqueManual(); // Iniciar el vaciado manual
        }
    }

    private void estadoInicio() {
        // Iniciar la válvula cerrada y tanque vacío
        valvulaModel.abrir(0);
        tanque.setValue(0);
        tuberiaCasa.setValue(0);
    }

    private void llenarTanque() throws InterruptedException {
        System.out.println("LLENAR TANQUE " + progresoTanque);
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
    // Llenar el tanque desde 60 hasta 80
    System.out.println("LLENAR TANQUE DESDE 60"+ progresoTanque);
    for (progresoTanque = 60; progresoTanque <= 80; progresoTanque++) {
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
        while (isRunning && progresoTanque > 60) {
            valvulaAbierta = false;
            tuberiaCasa.setValue(100); // Simular el flujo de agua hacia la casa
            progresoTanque--; // Reducir el nivel del tanque
            
            System.out.println("PRIMER VACIADO DEL TANQUE");
            actualizarTanque(progresoTanque); // Actualizar la interfaz gráfica
            Thread.sleep(100); // Esperar un momento para simular el flujo
        }
        
        tuberiaCasa.setValue(0); // Detener el flujo hacia la casa cuando el tanque esté en 60
        System.out.println("VACIADO DEL TANQUE A 60");
    }


    
    //ARREGLOS LOS HILOS PARA QUE NO HAGAN INTERFERENCIA
    private synchronized void llenarTanqueManualmente() {
        // Verificar si ya se está llenando el tanque o si se está vaciando
        if (llenandoTanque || vaciandoTanque) {
            System.out.println("Otro proceso ya está en curso.");
            return; // Salir si ya hay un proceso activo
        }

        // Marcar que el llenado está en curso
        llenandoTanque = true;
        vaciandoTanque = false; // Asegurarse de que no se está vaciando

        System.out.println("Iniciando llenado del tanque...");

        // Crear un hilo para realizar el llenado continuo
        new Thread(() -> {
            while (valvulaAbierta && progresoTanque < 100 && llenandoTanque) {
                // Aumentar el progreso del tanque
                progresoTanque++;
                System.out.println("LLENAR TANQUE MANUALMENTE: Progreso = " + progresoTanque);

                // Simular llenado
                transmisorNivel.setNivelAgua(progresoTanque / 100.0);
                controlNivel.verificarNivel(transmisorNivel.medirNivel());
                actualizarTanque(progresoTanque);

                // Verificar si el tanque está lleno
                if (progresoTanque >= 100) {
                    System.out.println("Tanque lleno.");
                    valvulaAbierta = false; // Detener el llenado
                }

                // Pausar brevemente para simular el llenado gradual
                try {
                    Thread.sleep(100); // Pausa de 100ms para simular el tiempo de llenado
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Finalizar el proceso de llenado si se completa o si se cierra la válvula
            llenandoTanque = false; // Marcar que el llenado ha finalizado

            // Mensaje final si la válvula se cierra
            if (!valvulaAbierta) {
                System.out.println("La válvula se cerró. Deteniendo el llenado.");
            }
        }).start();
    }

    private synchronized void vaciarTanqueManual() {
        // Verificar si ya se está vaciando el tanque o si se está llenando
        if (vaciandoTanque || llenandoTanque) {
            System.out.println("Otro proceso ya está en curso.");
            return; // Salir si ya hay un proceso activo
        }

        // Marcar que el vaciado está en curso
        vaciandoTanque = true;
        llenandoTanque = false; // Asegurarse de que no se está llenando

        System.out.println("Iniciando vaciado del tanque...");

        // Crear un hilo para realizar el vaciado continuo
        new Thread(() -> {
            while (progresoTanque > 0 && vaciandoTanque) {
                // Reducir el progreso del tanque
                progresoTanque--;
                System.out.println("VACIAR TANQUE MANUALMENTE: Progreso = " + progresoTanque);

                // Simular el vaciado hacia la casa
                tuberiaCasa.setValue(100);  // Simular el flujo de agua hacia la casa
                actualizarTanque(progresoTanque); // Actualizar la interfaz gráfica

                // Verificar si el tanque está completamente vacío
                if (progresoTanque <= 0) {
                    System.out.println("El tanque está vacío.");
                    tuberiaCasa.setValue(0); // Detener el flujo de agua hacia la casa
                    vaciandoTanque = false;  // Marcar que el vaciado ha terminado
                }

                // Pausar brevemente para simular el vaciado gradual
                try {
                    Thread.sleep(100); // Pausa de 100ms para simular el tiempo de vaciado
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Mensaje final cuando el tanque se vacía completamente
            if (progresoTanque <= 0) {
                System.out.println("El tanque ya está completamente vacío.");
                tuberiaCasa.setValue(0); // Asegurarse de detener el flujo de agua hacia la casa
            }
        }).start();
    }


    private void actualizarTanque(int nivel) {
        tanque.setValue(nivel);
        txtPorcentaje.setText(nivel + "%");

        // Actualizar color de la válvula dependiendo de su estado
        SwingUtilities.invokeLater(() -> {
            if (valvulaModel.getApertura() > 0) {
                ColorValvula.setBackground(Color.GREEN);
            } else{
                ColorValvula.setBackground(Color.RED);
            }
        });
    }
}
