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
    private JPanel ColorValvulaCasa2;
    private JTextField porcentajeValvulaCasa;
    private JTextField porcentajeValvula;
    private JProgressBar TuberiaEntrada1;
    private JProgressBar TuberiaEntrada2;
    private JProgressBar TuberiaSalida1;
    private JProgressBar TuberiaSalida2;
    private JTextField txtPorcentaje;
    private JPanel ColorValvula;
    private JPanel ColorValvula2;
    
    
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
    //camilo creo
    private int contadorArtificial = 0;
    
    
    //INSTANCIAS DE CLASES 
    private TanqueController tanqueController;
    private CajaSeguridad cajaSeguridad;
    private ControlNivel controlNivel;
    private TransmisorNivel transmisorNivel;
    private Valvula valvulaModel;

    public ControlLoop(JProgressBar tanque, JPanel ColorValvulaCasa, JPanel ColorValvulaCasa2, JTextField porcentajeValvulaCasa, 
            JTextField porcentajeValvula, JProgressBar TuberiaEntrada1, JProgressBar TuberiaEntrada2, JProgressBar TuberiaSalida1, JProgressBar TuberiaSalida2, JTextField txtPorcentaje, JPanel ColorValvula, JPanel ColorValvula2,
            CajaSeguridad cajaSeguridad, ControlNivel controlNivel, TransmisorNivel transmisorNivel, Valvula valvulaModel) {
        this.tanque = tanque;
        this.ColorValvulaCasa = ColorValvulaCasa;
        this.ColorValvulaCasa2 = ColorValvulaCasa2;
        this.porcentajeValvulaCasa = porcentajeValvulaCasa;
        this.porcentajeValvula = porcentajeValvula;
        this.TuberiaEntrada1 = TuberiaEntrada1;
        this.TuberiaEntrada2 = TuberiaEntrada2;
        this.TuberiaSalida1 = TuberiaSalida1;
        this.TuberiaSalida2 = TuberiaSalida2;
        this.txtPorcentaje = txtPorcentaje;
        this.ColorValvula = ColorValvula;
        this.ColorValvula2 = ColorValvula2;
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

    public void setProgresoTanque(int progresoTanque) {
        this.progresoTanque = progresoTanque;
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

    public boolean isIsAutomaticSelected() {
        return isAutomaticSelected;
    }

    public boolean isIsManualSelected() {
        return isManualSelected;
    }
    
    

    
    public void detenerSimulacion() {
        if (isRunning) {
            isRunning = false;
            System.out.println("ControlLoop detenido.");
        }
    }
    
    public void reanudarSimulacion() {
        if (!isRunning) {
            isRunning = true;
            System.out.println("ControlLoop reanudado.");
        }
    }

    
     //INICIO DE LA SIMULACION DEL TANQUE DE AGUA
     public void iniciarSimulacion() {
        
         System.out.println("ENTRO DE NUEVO AL CONTROL LOOP ");

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
                       modoAutomatico();
                        //LOGICA PARA EL MODO MANUAL
                    } else if (isManualSelected) {
                        System.out.println("VALVULA ESTADO: " + valvulaAbierta);
                        System.out.println("VALVULA ESTADO");
                        modoManual();
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
     
    public void modoAutomatico()throws InterruptedException{
         // Llenar el tanque por primera vez
        System.out.println("Iniciando modo automático...");

        // Abrir válvula para comenzar el llenado
        valvulaAbierta = true;
        actualizarValvula();
        
        System.out.println("PROGRESO TANQUE CONTROL LOOP " + progresoTanque);

        if (progresoTanque == 0) {
            System.out.println("LLENADO INICIAL DESDE 0 HASTA 100%");
            llenarTanqueAutomatico(0, 100, "100"); // Llenar desde 0 hasta 100
        } 
        
        // Comenzar el ciclo de llenado y vaciado
        while (isRunning && isAutomaticSelected) {
            // Mantener la válvula de la casa abierta
            actualizarCasa();
            if(contadorArtificial == 3){
                 if (progresoTanque < 30) {
                    System.out.println("LLENANDO TANQUE HASTA 60%");
                    //cajaSeguridad.mostrarAlerta("Nivel critico bajo detectado");
                    llenarTanqueAutomatico(30, 80, "60");
                }

                if (progresoTanque >= 60) {
                    System.out.println("VACIANDO TANQUE A 30%");
                    vaciarTanque(progresoTanque, 30);
                }             
            } else {
               // Si el tanque está lleno (100%) o llega a 80, vaciar hasta 60
                if (progresoTanque >= 80) {
                    System.out.println("VACIANDO TANQUE HASTA 60%");
                    vaciarTanque(progresoTanque, 60); 
                }

                // Si el tanque está en 60, llenar hasta 80
                if (progresoTanque <= 60) {
                    System.out.println("LLENANDO TANQUE DE 60 A 80%");
                    llenarTanqueAutomatico(progresoTanque, 80, "80");
                }
            }
            
           contadorArtificial++;
        } 
        
    }
    
    
    public void llenarTanqueAutomatico(int inicio, int fin, String porcentaje) throws InterruptedException {
        System.out.println("LLENAR TANQUE DESDE " + inicio + " HASTA " + fin);
        for (progresoTanque = inicio; progresoTanque <= fin; progresoTanque++) {
            System.out.println("Progreso llenando " + progresoTanque);
            if (!isRunning || !isAutomaticSelected) break;

            // Actualizar porcentaje de la válvula
            valvulaAbierta = true;
            actualizarValvula();
            TuberiaEntrada1.setValue(100);
            TuberiaEntrada2.setValue(100);
            porcentajeValvula.setText( porcentaje + "%");

            // Actualizar interfaz gráfica
            actualizarTanque(progresoTanque);
            Thread.sleep(100); // Simular tiempo de llenado
            
        }
        TuberiaEntrada1.setValue(0);
        TuberiaEntrada2.setValue(0);
    }
    
     
    //FUNCION PARA VACIAR EL TANQUE EN MODO AUTOMATICO
    public void vaciarTanque(int inicio, int fin) throws InterruptedException {
        
        for(progresoTanque = inicio; progresoTanque >= fin; progresoTanque--){
            System.out.println("Progreso vaciando " + progresoTanque);
           
            if (!isRunning ) break;
            
            porcentajeValvula.setText("40%"); //La valvula esta abierta a un 50%
            valvulaAbierta = false;
            actualizarValvula();
            progresoTanque--; // Reducir el nivel del tanque

            //System.out.println("PRIMER VACIADO DEL TANQUE");
            actualizarTanque(progresoTanque); // Actualizar la interfaz gráfica
            Thread.sleep(100); // Esperar un momento para simular el flujo 
        
        }
        System.out.println("VACIADO DEL TANQUE");
    }
    
    
    //FUNCIONES PARA MODO MANUAL
    
    public void modoManual(){
        
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

        System.out.println("VALVULA ABIERTA EN MODO MANUAL CONTROL LOOP " + valvulaAbierta);
        if (valvulaAbierta) {
            System.out.println("LLENAR TANQUE MANUALMENTE");
            vaciandoTanque = false;
            llenarTanqueManualmente();

        }else{
            System.out.println("VACIAR TANQUE MANUALMENTE");
            llenandoTanque = false;
            vaciarTanqueManual();
        }
    }
    
    //FUNCION PARA ABRIR LA VALVULA
    public void abrirValvula() {
        System.out.println("ABRIR VALVULA");
        valvulaAbierta = true;
        valvulaModel.abrir(100); // Abrir la válvula completamente
        porcentajeValvula.setText("100%");
        actualizarValvula();
        setValvulaAbierta(true);
    }
    
    //FUNCION PARA CERRAR LA VALVULA
    public void cerrarValvula() {
        System.out.println("CERRAR VALVULA");
        valvulaAbierta = false;
        valvulaModel.abrir(0); // Cerrar la válvula
        porcentajeValvula.setText("0%");
        actualizarValvula();
        setValvulaAbierta(false);
    }
    
    
    //FUNCION PARA LLENAR EL TANQUE DE FORMA MANUAL
    private synchronized void llenarTanqueManualmente() {
        // Verificar si ya se está llenando el tanque o si se está vaciando
        System.out.println("PROGRESO TANQUE LLENADO MANUAL " + progresoTanque);
        
        System.out.println("VACIANDO TANQUE MANUALMENTE FLAG " + vaciandoTanque);
        System.out.println("LLENANDO TANQUE MANUALMENTE FLAG " + llenandoTanque);
        
        System.out.println("IS RUNNING  " + isRunning);
        
        
        if (llenandoTanque || vaciandoTanque ) {
            System.out.println("Otro proceso ya esta en curso 1.");
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
                TuberiaEntrada1.setValue(100);
                TuberiaEntrada2.setValue(100);
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
    public synchronized void vaciarTanqueManual() {
        System.out.println("PROGRESO TANQUE VACIADO MANUAL " + progresoTanque);
        // Verificar si ya se está vaciando el tanque o si se está llenando
        System.out.println("VACIANDO TANQUE MANUALMENTE FLAG " + vaciandoTanque);
        System.out.println("LLENANDO TANQUE MANUALMENTE FLAG " + llenandoTanque);
        
        System.out.println("IS RUNNING  " + isRunning);
        
        
        if (vaciandoTanque || llenandoTanque ) {
            System.out.println("Otro proceso ya está en curso 2.");
            return; // Salir si ya hay un proceso activo
        }

        // Marcar que el vaciado está en curso
        vaciandoTanque = true;
        llenandoTanque = false; // Asegurarse de que no se está llenando
        

        System.out.println("Iniciando vaciado del tanque...");

        // Crear un hilo para realizar el vaciado continuo
        new Thread(() -> {
            while (!valvulaAbierta && progresoTanque > 0 && vaciandoTanque) {
                // Reducir el progreso del tanque
                cerrarValvula();//La valvula se encuentra cerrada a un 0%
                TuberiaEntrada1.setValue(0);
                TuberiaEntrada2.setValue(0);
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
    public void actualizarCasa(){
        porcentajeValvulaCasa.setText("100%"); 
        ColorValvulaCasa.setBackground(Color.GREEN);
        ColorValvulaCasa2.setBackground(Color.GREEN);
        TuberiaSalida1.setValue(100);
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
                ColorValvula2.setBackground(Color.GREEN);
            } else{
                ColorValvula.setBackground(Color.RED);
                 ColorValvula2.setBackground(Color.RED);
            }
        });
    }
    
}
