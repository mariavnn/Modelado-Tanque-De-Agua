/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.JTextField;
import model.CajaSeguridad;
import model.TransmisorNivel;

/**
 *
 * @author Victoria
 */
public class SecurityLoop {
    private JTextField porcentajeValvula;
    
    private TanqueController tanqueController;
    private TransmisorNivel transmisorNivel;
    private CajaSeguridad cajaSeguridad;
    private ControlLoop controlLoop;

    private static final double NIVEL_MINIMO = 30; // Nivel mínimo crítico 
    private static final int NIVEL_MAXIMO = 90; // Nivel máximo crítico 
    
    private boolean isRunning;
    private boolean valvulaSalida;
    private boolean valvulaEntrada;
    private boolean isControlPaused = false;
    private int progresoTanque;
    
    public SecurityLoop(TransmisorNivel transmisorNivel, CajaSeguridad cajaSeguridad, ControlLoop controlLoop,
                            JTextField porcentajeValvula
    ) {
        this.transmisorNivel = transmisorNivel;
        this.cajaSeguridad = cajaSeguridad;
        this.controlLoop = controlLoop;
        this.isRunning = false;
        
        
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
    
    

    public void iniciar() {
        isRunning = true;
        new Thread(this::monitorizarNivel).start();
        System.out.println("SecurityLoop iniciado.");
    }

    public void detener() {
        if(isRunning){
           isRunning = false;
        }
        System.out.println("SecurityLoop detenido.");
    }

    private void monitorizarNivel() {
        System.out.println("Monitoreo del nivel en el sistema de seguridad ");
        
        while (isRunning) {
            double nivelActual = transmisorNivel.obtenerNivelActual();
            System.out.println("Nivel actual " + nivelActual);

            if (nivelActual <= NIVEL_MINIMO) {
                cajaSeguridad.mostrarAlerta("Nivel critico bajo detectado");
            } else if (nivelActual >= NIVEL_MAXIMO) {
                cajaSeguridad.mostrarAlerta("Nivel crítico alto detectado: ");             
                manejarEmergencia();                                      
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
    
    private void manejarEmergencia() {
        System.out.println("Manejando emergencia...");

        // Detener el lazo de control
        controlLoop.detenerSimulacion();

        // Iniciar el manejo de emergencia
        new Thread(() -> {
            try {
                while (isRunning) {
                    double nivelActual = transmisorNivel.obtenerNivelActual();

                    if (nivelActual <= NIVEL_MINIMO) {
                        System.out.println("Nivel crítico bajo. Llenando tanque...");
                        
                        if(controlLoop.isIsAutomaticSelected()) {
                            abrirValvulaEntrada();
                            llenarTanque();
                        } else if(controlLoop.isIsManualSelected()) {
                            abrirValvulaEntrada();
                            llenarTanqueManual(controlLoop.getProgresoTanque());
                            
                        } else {
                            break;
                        }
                        
                    } else if (nivelActual >= NIVEL_MAXIMO) {
                        System.out.println("Nivel crítico alto. Vaciando tanque...");
                           if(controlLoop.isIsAutomaticSelected()){
                               cerrarValvulaEntrada();
                               vaciarTanque();
                            }else if(controlLoop.isIsManualSelected()){
                                cerrarValvulaEntrada();
                                vaciarTanqueManual(controlLoop.getProgresoTanque());
                            }else{
                               break;
                            }
                         
                        
                    } else {
                        System.out.println("Nivel estabilizado. Deteniendo manejo de emergencia...");
                        System.out.println("IS RUNNING PROCESO SECURITY LOOP " + isRunning);
                        
                        System.out.println("IS RUNNING PROCESO SECURITY LOOP 2 " + isRunning);
                        controlLoop.reanudarSimulacion();
                        controlLoop.setProgresoTanque(progresoTanque);
                        
                        System.out.println("IS AUTOMATICO SELECTED " + controlLoop.isIsAutomaticSelected());
                        System.out.println("IS MANUAL SELECTED " + controlLoop.isIsManualSelected());
                        if(controlLoop.isIsAutomaticSelected()){
                           controlLoop.modoAutomatico();
                        }else if(controlLoop.isIsManualSelected()){
                           
                        }else{
                            break;
                        }
                        
                    }
                    Thread.sleep(500); // Simulación de espera
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Manejo de emergencia interrumpido.");
            }
        }).start();
    }

     private void abrirValvulaEntrada() {
        System.out.println("Abriendo válvula de entrada...");
        controlLoop.abrirValvula();
        valvulaEntrada = controlLoop.isValvulaAbierta();
    }
    
    private void cerrarValvulaEntrada() {
        // Suponiendo que tienes acceso a la lógica de control de la válvula de entrada
        System.out.println("Cerrando válvula de entrada...");
        controlLoop.cerrarValvula();
        valvulaEntrada = controlLoop.isValvulaAbierta();
        System.out.println("VALVULA ENTRADA CERRADA " + valvulaEntrada);
        // Implementa la lógica específica para cerrar la válvula de entrada aquí
    }
    
    private void vaciarTanque() throws InterruptedException {
        System.out.println("INICIO DEL VACIADO DEL TANQUE SECURITY LOOP ");
        progresoTanque = controlLoop.getProgresoTanque();
        System.out.println("PROGRESO TANQUE VACIADO " + progresoTanque);
        while (isRunning && progresoTanque > 60) {
            if (!isRunning ) break;
            
            controlLoop.actualizarValvula();
            controlLoop.actualizarCasa();
            System.out.println("PROGRESO TANQUE VACIADO " + progresoTanque);
            //porcentajeValvula.setText("40%"); //La valvula esta abierta a un 50%
            valvulaEntrada = false;
            controlLoop.actualizarValvula();
            progresoTanque--; // Reducir el nivel del tanque
            
            //System.out.println("PRIMER VACIADO DEL TANQUE");
            controlLoop.actualizarTanque(progresoTanque); // Actualizar la interfaz gráfica
            Thread.sleep(100); // Esperar un momento para simular el flujo
        }
    }

    private void vaciarTanqueManual(int nivelTanqueReal) throws InterruptedException {
        System.out.println("INICIO DEL VACIADO DEL TANQUE SECURITY LOOP MANUAL ");
        //progresoTanque = nivelTanqueReal;
        while (isRunning && nivelTanqueReal > 0) {
            if (!isRunning ) break;
            
            controlLoop.actualizarValvula();
            controlLoop.actualizarCasa();
            System.out.println("PROGRESO TANQUE VACIADO " + nivelTanqueReal);
            //porcentajeValvula.setText("40%"); //La valvula esta abierta a un 50%
            valvulaEntrada = false;
            controlLoop.actualizarValvula();
            nivelTanqueReal--; // Reducir el nivel del tanque
            
            //System.out.println("PRIMER VACIADO DEL TANQUE");
            controlLoop.actualizarTanque(nivelTanqueReal); // Actualizar la interfaz gráfica
            transmisorNivel.setNivelAgua(nivelTanqueReal);
            Thread.sleep(100); // Esperar un momento para simular el flujo
        }
    }
     
    private void llenarTanque() throws InterruptedException {
        System.out.println("INICIO DEL LLENADO DEL TANQUE SECURITY LOOP ");
        progresoTanque = controlLoop.getProgresoTanque();
        while (isRunning && progresoTanque < 90) {
            if (!isRunning ) break;
            
            controlLoop.actualizarCasa();
            System.out.println("PROGRESO TANQUE LLENADO " + progresoTanque);

            valvulaEntrada = true;
            controlLoop.actualizarValvula();
            progresoTanque++; // Aumentar el nivel del tanque
            
            controlLoop.actualizarTanque(progresoTanque); // Actualizar la interfaz gráfica
            Thread.sleep(100); // Esperar un momento para simular el flujo
        }
    }

    private void llenarTanqueManual(int nivelTanqueReal) throws InterruptedException {
        System.out.println("INICIO DEL LLENADO DEL TANQUE SECURITY LOOP MANUAL ");
        //progresoTanque = controlLoop.getProgresoTanque();
        nivelTanqueReal = 0;
        while (isRunning && nivelTanqueReal < 40) {
            if (!isRunning ) break;
            
            controlLoop.actualizarCasa();
            System.out.println("PROGRESO TANQUE LLENADO " + nivelTanqueReal);

            valvulaEntrada = true;
            controlLoop.actualizarValvula();
            nivelTanqueReal++; // Aumentar el nivel del tanque
            
            controlLoop.actualizarTanque(nivelTanqueReal); // Actualizar la interfaz gráfica
            transmisorNivel.setNivelAgua(nivelTanqueReal);
            Thread.sleep(100); // Esperar un momento para simular el flujo
        }
        cerrarValvulaEntrada();
        valvulaEntrada = false;
        controlLoop.actualizarValvula();
    }
    private void abrirValvulasSalida() {
        // Suponiendo que tienes acceso a la lógica de control de las válvulas de salida
        System.out.println("Abriendo válvulas de salida...");
        // Implementa la lógica específica para abrir las válvulas de salida aquí
    }
    
    
}
