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
import model.CajaSeguridad;
import model.ControlNivel;
import model.EstadoTanque;
import model.TransmisorNivel;
import model.Valvula;

public class TanqueController {

    private JProgressBar tanque;
    private JProgressBar TuberiaSalida1;
    private JProgressBar TuberiaSalida2;
    private JProgressBar TuberiaEntrada1;
    private JProgressBar TuberiaEntrada2;
    private JLabel valvula;
    private JLabel valvulaEntradaSeg;
    private JLabel valvulaSalidaSeg;
    private JTextField txtPorcentaje;
    private JButton btnIniciar;
    private JButton AbrirValvula;
    private JButton CerrarValvula;
    private JPanel ColorValvula;
    private JPanel ColorValvula2;
    private JPanel ColorValvulaCasa;
     private JPanel ColorValvulaCasa2;
    private JRadioButton modoAutomatico;
    private JRadioButton modoManual;
    private JTextField porcentajeValvulaCasa;
    private JTextField porcentajeValvula;
    private JLabel CajaSeguridad;
    private boolean isRunning = false;

    // Variables de lógica del sistema
    private double ALTURA_MAXIMA_METROS = 1.0;
    private int progresoTanque = 0;
    private boolean valvulaAbierta;
    private boolean vaciandoTanque = false;
    private boolean llenandoTanque = false;
    private ControlNivel controlNivel;
    private TransmisorNivel transmisorNivel;
    private Valvula valvulaModel;
    private CajaSeguridad cajaSeguridad;

    public TanqueController(JProgressBar tanque, JProgressBar TuberiaSalida1,  JProgressBar TuberiaSalida2,  JProgressBar TuberiaEntrada1, JProgressBar TuberiaEntrada2, JLabel valvula, 
            JLabel valvulaEntradaSeg, JLabel valvulaSalidaSeg,  JTextField txtPorcentaje, JTextField porcentajeValvula, JTextField porcentajeValvulaCasa, JButton btnIniciar, JButton AbrirValvula, 
            JButton CerrarValvula, JPanel ColorValvula, JPanel ColorValvula2, JPanel ColorValvulaCasa, JPanel ColorValvulaCasa2, JRadioButton modoAutomatico, 
            JRadioButton modoManual, ControlNivel controlNivel, TransmisorNivel transmisorNivel, Valvula valvulaModel,  JLabel CajaSeguridad, CajaSeguridad cajaSeguridad) {
        this.tanque = tanque;
        this.TuberiaSalida1 = TuberiaSalida1;
        this.TuberiaSalida2 = TuberiaSalida2;
        this.TuberiaEntrada1 = TuberiaEntrada1;
        this.TuberiaEntrada2 = TuberiaEntrada2;
        this.valvula = valvula;
        this.valvulaEntradaSeg = valvulaEntradaSeg;
        this.valvula = valvulaSalidaSeg;
        this.txtPorcentaje = txtPorcentaje;
        this.btnIniciar = btnIniciar;
        this.AbrirValvula = AbrirValvula;
        this.CerrarValvula = CerrarValvula;
        this.ColorValvula = ColorValvula;
        this.ColorValvula2 = ColorValvula2;
        this.ColorValvulaCasa = ColorValvulaCasa;
        this.ColorValvulaCasa2 = ColorValvulaCasa2;
        this.modoAutomatico = modoAutomatico;
        this.modoManual = modoManual;
        this.controlNivel = controlNivel;
        this.transmisorNivel = transmisorNivel;
        this.valvulaModel = valvulaModel;
        this.CajaSeguridad = CajaSeguridad;
        this.cajaSeguridad = cajaSeguridad;
        this.porcentajeValvula = porcentajeValvula;
        this.porcentajeValvulaCasa = porcentajeValvulaCasa;

        // Configurar el estado inicial
        //modoAutomatico.setSelected(true);
        AbrirValvula.setEnabled(false);
        CerrarValvula.setEnabled(false);

        btnIniciar.addActionListener(e -> iniciarSimulacion());
        
        // Listeners para los JRadioButton
        modoAutomatico.addActionListener(e -> activarModo(true));
        modoManual.addActionListener(e -> activarModo(false));
        
        // Listeners para los botones de válvula
        AbrirValvula.addActionListener(e -> abrirValvula());
        CerrarValvula.addActionListener(e -> cerrarValvula());
    }
    
    private void estadoInicio() {
        // Iniciar la válvula cerrada y tanque vacío
        valvulaModel.abrir(0);
        tanque.setValue(0);
        
        TuberiaEntrada1.setValue(100);
        TuberiaEntrada2.setValue(100);
        TuberiaSalida1.setValue(100);
    
        porcentajeValvula.setText("0%");
        porcentajeValvulaCasa.setText("0%");
        
        ColorValvula2.setBackground(Color.GREEN);
        ColorValvulaCasa2.setBackground(Color.GREEN);
        
        if(!modoAutomatico.isSelected() && !modoManual.isSelected()){
            //SI NINGUN MODO ESTA SELECCIONADO AL INICIAR LA SIMULACION MUESTRA UN DIALOG QUE PIDA ESCOGER UN MODO
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un modo (Automático o Manual) para iniciar la simulación.", "Modo no seleccionado", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void detenerSimulacion() {
        
        actualizarTanque(progresoTanque);
        
        isRunning = false;
        llenandoTanque = false;  // Detener cualquier proceso de llenado en curso
        vaciandoTanque = false;  // Detener cualquier proceso de vaciado en curso
        valvulaAbierta = false;  // Cerrar la válvula para evitar llenado
        
        porcentajeValvulaCasa.setText("0%");
        porcentajeValvula.setText("0%");
        ColorValvulaCasa.setBackground(Color.RED);
        ColorValvula2.setBackground(Color.RED);
        ColorValvulaCasa2.setBackground(Color.RED);
        
        TuberiaEntrada1.setValue(0);
        TuberiaEntrada2.setValue(0);
        TuberiaSalida1.setValue(0);
        
        
        modoAutomatico.setEnabled(true);
        modoManual.setEnabled(true);
        
        if(modoManual.isSelected()){
            AbrirValvula.setEnabled(true);
            CerrarValvula.setEnabled(true);
        } else{
            AbrirValvula.setEnabled(false);
            CerrarValvula.setEnabled(false);
        }
        
        System.out.println("Simulación detenida.");
    }
        

    public void iniciarSimulacion() {
        if (isRunning) {
            return; // Evitar múltiples hilos
        }
        isRunning = true;
        estadoInicio();
        
        //INICIA LA SIMULACION E INICIA EL MONITOREO DE LA CAJA DE SEGURIDAD
        manejarEstadoTanque();
        cajaSeguridad.monitorear();
        

        // Hilo para el llenado del tanque
        new Thread(() -> {
            try {
                while (isRunning) {
                    //LOGICA PARA EL MODO AUTOMATICO
                    if (modoAutomatico.isSelected()) {
                        // Llenar el tanque por primera vez
                        System.out.println("LLENADO DEL TANQUE DESDE 0");
                        valvulaAbierta = true;
                        
                        llenarTanqueAutomatico(0, 100, "100"); //Llenar al 100%
                        

                        // Comenzar el ciclo de llenado y vaciado
                        while (isRunning && modoAutomatico.isSelected()) {
                            
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
                    } else if (modoManual.isSelected()) {
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
    
    //FUNCION PARA ACTIVAR MODO MANUAL O AUTOMATICO
    private void activarModo(boolean modoActivado) {
        System.out.println("MODO ACTIVADO" + modoActivado);
        modoAutomatico.setSelected(modoActivado);
        modoManual.setSelected(!modoActivado);
        
        AbrirValvula.setEnabled(!modoActivado);
        CerrarValvula.setEnabled(!modoActivado);
        
        if(modoAutomatico.isSelected()){
            modoManual.setEnabled(false);
        }else{
            modoAutomatico.setEnabled(false);
        }
    }
    
    //FUNCIONES PARA MODO AUTOMATICO
    
    //FUNCION PARA LLENAR EL TANQUE EN EL MODO AUTOMATICO
    //Esta funcion recibe como parametro desde donde y hasta donde se llena el tanque
    private void llenarTanqueAutomatico(int inicio, int fin, String porcentaje) throws InterruptedException {
        System.out.println("LLENAR TANQUE DESDE " + inicio + " HASTA " + fin);
        for (progresoTanque = inicio; progresoTanque <= fin; progresoTanque++) {
            if (!isRunning || !modoAutomatico.isSelected()) return;

            // Actualizar porcentaje de la válvula
            valvulaAbierta = true;
            porcentajeValvula.setText( porcentaje + "%");

            // Actualizar interfaz gráfica
            actualizarTanque(progresoTanque);
            Thread.sleep(100); // Simular tiempo de llenado
        }
    }

    
    //FUNCION PARA VACIAR EL TANQUE EN MODO AUTOMATICO
    public void vaciarTanque() throws InterruptedException {
        while (isRunning && progresoTanque > 60) {
            porcentajeValvula.setText("40%"); //La valvula esta abierta a un 50%
            valvulaAbierta = false;
            progresoTanque--; // Reducir el nivel del tanque
            
            System.out.println("PRIMER VACIADO DEL TANQUE");
            actualizarTanque(progresoTanque); // Actualizar la interfaz gráfica
            Thread.sleep(100); // Esperar un momento para simular el flujo
        }
        System.out.println("VACIADO DEL TANQUE A 60");
    }


    //FUNCIONES PARA MODO MANUAL
    
    //FUNCION PARA ABRIR LA VALVULA
    private void abrirValvula() {
        System.out.println("ABRIR VALVULA");
        valvulaAbierta = true;
        valvulaModel.abrir(100); // Abrir la válvula completamente
        porcentajeValvula.setText("100%");
    }
    
    //FUNCION PARA CERRAR LA VALVULA
    private void cerrarValvula() {
        System.out.println("CERRAR VALVULA");
        valvulaAbierta = false;
        valvulaModel.abrir(0); // Cerrar la válvula
        porcentajeValvula.setText("0%");
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
   
    
    public void manejarEstadoTanque() {
        EstadoTanque estado = cajaSeguridad.getEstadoTanque(); 
        
        System.out.println("ESTADO DEL TANQUE RECIBIDO EN CONTROLADOR " + estado);

        switch (estado) {
            case DESBORDANDO -> {
                // Si el tanque está desbordando, vaciar el tanque o tomar otra acción
                try {
                    vaciarTanque();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            case NIVEL_BAJO -> // Si el nivel es bajo, puedes añadir más funcionalidades aquí
                System.out.println("Nivel bajo de agua detectado.");

            case NORMAL -> // El tanque está funcionando correctamente
                System.out.println("El tanque está funcionando correctamente.");
        }
    }
    
    
    //FUNCION CON INSTRUCCIONES PARA SIMULAR EL PASO DE AGUA A LA CASA
    private void actualizarCasa(){
        porcentajeValvulaCasa.setText("100%"); 
        ColorValvulaCasa.setBackground(Color.GREEN);
        TuberiaSalida2.setValue(100);
    }
    
    private void actualizarTanque(int nivel) {
        tanque.setValue(nivel);
        controlNivel.setNivelActual(nivel);
        transmisorNivel.setNivelAgua(nivel);
        
        // Convertir el nivel a metros
        double nivelMetros = (nivel / 100.0) * ALTURA_MAXIMA_METROS;
        txtPorcentaje.setText(String.format("%.1f m", nivelMetros));


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
