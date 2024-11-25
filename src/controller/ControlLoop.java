/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import model.CajaSeguridad;
import model.ControlNivel;
import model.TransmisorNivel;
import model.Valvula;

/**
 *
 * @author Victoria
 */
public class ControlLoop {
    
    //DECLARACION DE COMPONENTES EN LA INTERFAZ
    private JProgressBar tanque;
    private JPanel ColorValvulaCasa;
    private JTextField porcentajeValvulaCasa;
    private JTextField porcentajeValvula;
    private JProgressBar TuberiaSalida2;
    private JTextField txtPorcentaje;
    private JPanel ColorValvula;
    
    
    //DECLARACION DE VARIABLES BOOLEAN
    private boolean isRunning = false;
    private boolean isAutomaticSelected = false;
    private boolean isManualSelected = false;
    private boolean valvulaAbierta = false;
    private boolean vaciandoTanque = false;
    private boolean llenandoTanque = false;
    
    //DECLARACION DE VARIABLES NUMERICAS 
    private int progresoTanque = 0;
    private double ALTURA_MAXIMA_METROS = 1.0;
    
    
    //INSTANCIAS DE CLASES 
    private CajaSeguridad cajaSeguridad;
    private ControlNivel controlNivel;
    private TransmisorNivel transmisorNivel;
    private Valvula valvulaModel;

    public ControlLoop(JProgressBar tanque, JPanel ColorValvulaCasa, JTextField porcentajeValvulaCasa, 
            JTextField porcentajeValvula, JProgressBar TuberiaSalida2, JTextField txtPorcentaje, JPanel ColorValvula, 
            CajaSeguridad cajaSeguridad, ControlNivel controlNivel, TransmisorNivel transmisorNivel, Valvula valvulaModel) {
        this.tanque = tanque;
        this.ColorValvulaCasa = ColorValvulaCasa;
        this.porcentajeValvulaCasa = porcentajeValvulaCasa;
        this.porcentajeValvula = porcentajeValvula;
        this.TuberiaSalida2 = TuberiaSalida2;
        this.txtPorcentaje = txtPorcentaje;
        this.ColorValvula = ColorValvula;
        this.cajaSeguridad = cajaSeguridad;
        this.controlNivel = controlNivel;
        this.transmisorNivel = transmisorNivel;
        this.valvulaModel = valvulaModel;  
       
    }
    
    //CODIGO PARA MANEJO DE VARIABLES
    
    //SETTER

    public void setIsAutomaticSelected(boolean isAutomaticSelected) {
        this.isAutomaticSelected = isAutomaticSelected;
    }

    public void setIsManualSelected(boolean isManualSelected) {
        this.isManualSelected = isManualSelected;
    }
    
    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void setVaciandoTanque(boolean vaciandoTanque) {
        this.vaciandoTanque = vaciandoTanque;
    }

    public void setLlenandoTanque(boolean llenandoTanque) {
        this.llenandoTanque = llenandoTanque;
    }

    public void setValvulaAbierta(boolean valvulaAbierta) {
        this.valvulaAbierta = valvulaAbierta;
    }
    
    //GETTER
    public int getProgresoTanque() {
        return progresoTanque;
    }
    
    public boolean isIsRunning() {
        return isRunning;
    }

    public boolean isValvulaAbierta() {
        return valvulaAbierta;
    }

    public boolean isVaciandoTanque() {
        return vaciandoTanque;
    }

    public boolean isLlenandoTanque() {
        return llenandoTanque;
    }

    
    public void detenerSimulacion() {
        if (isRunning) {
            isRunning = false;
            System.out.println("ControlLoop detenido.");
        }
    }

    
     //INICIO DE LA SIMULACION DEL TANQUE DE AGUA
     public void iniciarSimulacion() {
        if (isRunning) {
            return; // Evitar múltiples hilos
        }
        isRunning = true;
        
        
        if(!isAutomaticSelected && !isManualSelected){
            //SI NINGUN MODO ESTA SELECCIONADO AL INICIAR LA SIMULACION MUESTRA UN DIALOG QUE PIDA ESCOGER UN MODO
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un modo (Automático o Manual) para iniciar la simulación.", "Modo no seleccionado", JOptionPane.WARNING_MESSAGE);
        }
        
        
        // Hilo para el llenado del tanque
        new Thread(() -> {
            try {
                while (isRunning) {
                    //LOGICA PARA EL MODO AUTOMATICO
                    
                    //cajaSeguridad.monitorear();
                    System.out.println("MODO ACTIVADO EN CONTROL LOOP: AUTOMATICO " + isAutomaticSelected);
                    System.out.println("MODO ACTIVADO EN CONTROL LOOP: MANUAL " + isManualSelected); 
                    
                    if (isAutomaticSelected) {
                        // Llenar el tanque por primera vez
                        
                        System.out.println("LLENADO DEL TANQUE DESDE 0");
                        valvulaAbierta = true;
                        actualizarValvula(); 
                        
                        llenarTanqueAutomatico(0, 100, "100"); //Llenar al 100%
                        

                        // Comenzar el ciclo de llenado y vaciado
                        while (isRunning && isAutomaticSelected) {
                            //Valvula de la casa se mantiene abierta para permitir el paso del agua siempre
                            actualizarCasa();
                            
                            // Si el tanque está lleno, empezar a pasar agua a la casa
                            if (progresoTanque >= 100 || progresoTanque >= 80) {
                                // Pasar agua a la casa hasta que el tanque baje a 60
                                vaciarTanque();
                            }

                            // Si el tanque llega a 60, comenzar a llenar desde 60 hasta 80
                            if (progresoTanque <= 60) {
                                System.out.println("DENTRO DEL BUCLE DE 60 A 80");
                                while (isRunning && progresoTanque < 80) {
                                    llenarTanqueAutomatico(60, 80, "60"); // Llenar de 60 a 80
                                }
                            }
                        }
                        //LOGICA PARA EL MODO MANUAL
                    } else if (isManualSelected) {
                        System.out.println("LOGICA PARA EL MODO MANUAL");
                        //Condicion para que el proceso se pare cuando el tanque esta vacio
                        if(progresoTanque == 0){
                            //Cerrar valvula
                            ColorValvulaCasa.setBackground(Color.RED);
                            porcentajeValvulaCasa.setText("0%"); 
                            //Dejar de pasar agua a la casa 
                            
                            //TODO REVISAR
                            //tuberiaCasa.setValue(0);
                        }
                        
                       
                        if (valvulaAbierta) {
                            System.out.println("LLENAR TANQUE MANUALMENTE");
                            vaciandoTanque = false;
                            llenarTanqueManualmente();
                            
                        }else{
                            System.out.println("VACIAR TANQUE MANUALMENTE");
                            llenandoTanque = false;
                            vaciarTanqueManual();
                        }
                        
                    }else{
                        System.out.println("SELECCIONA UN MODO ");
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                isRunning = false;  // Permitir reiniciar la simulación
            }
        }).start();
    }
     
    //METODOS PARA EL MODO AUTOMATICO
    
    public void llenarTanqueAutomatico(int inicio, int fin, String porcentaje) throws InterruptedException {
        System.out.println("LLENAR TANQUE DESDE " + inicio + " HASTA " + fin);
        for (progresoTanque = inicio; progresoTanque <= fin; progresoTanque++) {
            if (!isRunning || !isAutomaticSelected) break;

            // Actualizar porcentaje de la válvula
            valvulaAbierta = true;
            actualizarValvula();
            porcentajeValvula.setText( porcentaje + "%");

            // Actualizar interfaz gráfica
            actualizarTanque(progresoTanque);
            Thread.sleep(100); // Simular tiempo de llenado
            
        }
    }
    
     
    //FUNCION PARA VACIAR EL TANQUE EN MODO AUTOMATICO
    public void vaciarTanque() throws InterruptedException {
        while (isRunning && progresoTanque > 60) {
            if (!isRunning ) break;
            
            
            porcentajeValvula.setText("40%"); //La valvula esta abierta a un 50%
            valvulaAbierta = false;
            actualizarValvula();
            progresoTanque--; // Reducir el nivel del tanque
            
            System.out.println("PRIMER VACIADO DEL TANQUE");
            actualizarTanque(progresoTanque); // Actualizar la interfaz gráfica
            Thread.sleep(100); // Esperar un momento para simular el flujo
        }
        System.out.println("VACIADO DEL TANQUE A 60");
    }
    
    
    //FUNCIONES PARA MODO MANUAL
    
    //FUNCION PARA ABRIR LA VALVULA
    public void abrirValvula() {
        System.out.println("ABRIR VALVULA");
        valvulaAbierta = true;
        valvulaModel.abrir(100); // Abrir la válvula completamente
        porcentajeValvula.setText("100%");
        actualizarValvula();
    }
    
    //FUNCION PARA CERRAR LA VALVULA
    public void cerrarValvula() {
        System.out.println("CERRAR VALVULA");
        valvulaAbierta = false;
        valvulaModel.abrir(0); // Cerrar la válvula
        porcentajeValvula.setText("0%");
        actualizarValvula();
    }
    
    
    //FUNCION PARA LLENAR EL TANQUE DE FORMA MANUAL
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
                abrirValvula();
                System.out.println("LLENAR TANQUE MANUALMENTE: Progreso = " + progresoTanque);
                progresoTanque++;

                // Simular llenado
                actualizarTanque(progresoTanque);

                // Verificar si el tanque está lleno
                if (progresoTanque >= 100) {
                    System.out.println("Tanque lleno.");
                    cerrarValvula(); //Detener llenado
                }

                // Pausar brevemente para simular el llenado gradual
                try {
                    Thread.sleep(100); // Pausa de 100ms para simular el tiempo de llenado
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // Finalizar el proceso de llenado si se completa o si se cierra la válvula
            llenandoTanque = false; // Marcar que el llenado ha finaliz
        }).start();
    }
    
    
    //FUNCION PARA VACIAR EL TANQUE DE FORMA MANUAL
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
                cerrarValvula();//La valvula se encuentra cerrada a un 0%
                progresoTanque--;
                System.out.println("VACIAR TANQUE MANUALMENTE: Progreso = " + progresoTanque);

                // Simular el paso de agua hacia la casa
                actualizarCasa();
                
                actualizarTanque(progresoTanque); // Actualizar la interfaz gráfica

                // Verificar si el tanque está completamente vacío
                if (progresoTanque <= 0) {
                    System.out.println("El tanque está vacío.");
                    
                    //TODO REVISAR
                    //tuberiaCasa.setValue(0); // Detener el flujo de agua hacia la casa
                    vaciandoTanque = false;  // Marcar que el vaciado ha terminado
                }

                // Pausar brevemente para simular el vaciado gradual
                try {
                    Thread.sleep(100); // Pausa de 100ms para simular el tiempo de vaciado
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            vaciandoTanque = false;  
          
        }).start();
    }
   
    //FUNCION CON INSTRUCCIONES PARA SIMULAR EL PASO DE AGUA A LA CASA
    private void actualizarCasa(){
        porcentajeValvulaCasa.setText("100%"); 
        ColorValvulaCasa.setBackground(Color.GREEN);
        TuberiaSalida2.setValue(100);
    }
    
    public void actualizarTanque(int nivel) {
        tanque.setValue(nivel);
        controlNivel.setNivelActual(nivel);
        transmisorNivel.setNivelAgua(nivel);
        
        // Convertir el nivel a metros
        double nivelMetros = (nivel / 100.0) * ALTURA_MAXIMA_METROS;
        txtPorcentaje.setText(String.format("%.1f m", nivelMetros));


      
    }
    
    public void actualizarValvula(){
          // Actualizar color de la válvula dependiendo de su estado
        SwingUtilities.invokeLater(() -> {
            if (valvulaAbierta) {
                ColorValvula.setBackground(Color.GREEN);
            } else{
                ColorValvula.setBackground(Color.RED);
            }
        });
    }
    
}
