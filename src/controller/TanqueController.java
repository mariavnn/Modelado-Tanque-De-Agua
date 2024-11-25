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
import model.TransmisorNivel;
import model.Valvula;

public class TanqueController {
    //DECLARACION DE VARIABLES COMPONENTES DE LA INTERFAZ
    private JProgressBar tanque;
    private JButton AbrirValvula;
    private JButton CerrarValvula;
    private JButton btnIniciar;
    private JButton btnDetener;
    private JRadioButton modoAutomatico;
    private JRadioButton modoManual;
    private JProgressBar TuberiaEntrada1;
    private JProgressBar TuberiaEntrada2;
    private JProgressBar TuberiaSalida1;
    private JProgressBar TuberiaSalida2;
    private JLabel valvula;
    private JLabel valvulaEntradaSeg;
    private JLabel valvulaSalidaSeg;
    private JTextField txtPorcentaje;
    private JPanel ColorValvula;
    private JPanel ColorValvula2;
    private JPanel ColorValvulaCasa;
    private JPanel ColorValvulaCasa2;
    private JTextField porcentajeValvulaCasa;
    private JTextField porcentajeValvula;
    private JLabel CajaSeguridad;
   
    private int progresoTanque = 0;
    
    //INSTANCIA DE CLASES 
    private ControlNivel controlNivel;
    private TransmisorNivel transmisorNivel;
    private Valvula valvulaModel;
    private ControlLoop controlLoop;
    private SecurityLoop securityLoop;

    public TanqueController(JProgressBar tanque, JButton AbrirValvula, JButton CerrarValvula, JButton btnIniciar, 
            JButton btnDetener, JRadioButton modoAutomatico, JRadioButton modoManual, JProgressBar TuberiaEntrada1, 
            JProgressBar TuberiaEntrada2, JProgressBar TuberiaSalida1, JProgressBar TuberiaSalida2, JLabel valvula, 
            JLabel valvulaEntradaSeg, JLabel valvulaSalidaSeg, JTextField txtPorcentaje, JPanel ColorValvula, 
            JPanel ColorValvula2, JPanel ColorValvulaCasa, JPanel ColorValvulaCasa2, JTextField porcentajeValvulaCasa, 
            JTextField porcentajeValvula, JLabel CajaSeguridad, ControlNivel controlNivel, TransmisorNivel transmisorNivel, 
            Valvula valvulaModel, ControlLoop controlLoop, SecurityLoop securityLoop) {
        this.tanque = tanque;
        this.AbrirValvula = AbrirValvula;
        this.CerrarValvula = CerrarValvula;
        this.btnIniciar = btnIniciar;
        this.btnDetener = btnDetener;
        this.modoAutomatico = modoAutomatico;
        this.modoManual = modoManual;
        this.TuberiaEntrada1 = TuberiaEntrada1;
        this.TuberiaEntrada2 = TuberiaEntrada2;
        this.TuberiaSalida1 = TuberiaSalida1;
        this.TuberiaSalida2 = TuberiaSalida2;
        this.valvula = valvula;
        this.valvulaEntradaSeg = valvulaEntradaSeg;
        this.valvulaSalidaSeg = valvulaSalidaSeg;
        this.txtPorcentaje = txtPorcentaje;
        this.ColorValvula = ColorValvula;
        this.ColorValvula2 = ColorValvula2;
        this.ColorValvulaCasa = ColorValvulaCasa;
        this.ColorValvulaCasa2 = ColorValvulaCasa2;
        this.porcentajeValvulaCasa = porcentajeValvulaCasa;
        this.porcentajeValvula = porcentajeValvula;
        this.CajaSeguridad = CajaSeguridad;
        this.controlNivel = controlNivel;
        this.transmisorNivel = transmisorNivel;
        this.valvulaModel = valvulaModel;
        this.controlLoop = controlLoop;
        this.securityLoop = securityLoop;
        
        
         // Configurar el estado inicial
        //modoAutomatico.setSelected(true);
        AbrirValvula.setEnabled(false);
        CerrarValvula.setEnabled(false);

        btnIniciar.addActionListener(e -> iniciarSimulacion());
        btnDetener.addActionListener(e -> detenerSimulacion() );
        
        // Listeners para los JRadioButton
        modoAutomatico.addActionListener(e -> activarModo(true));
        modoManual.addActionListener(e -> activarModo(false));
        
        // Listeners para los botones de válvula
        AbrirValvula.addActionListener(e -> controlLoop.abrirValvula());
        CerrarValvula.addActionListener(e -> controlLoop.cerrarValvula());
        
        estadoInicio();
        
    }
    
    
    private void estadoInicio() {
        // Iniciar la válvula cerrada y tanque vacío
        valvulaModel.abrir(0);
        tanque.setValue(0);
        
        TuberiaEntrada1.setValue(0);
        TuberiaEntrada2.setValue(0);
        TuberiaSalida1.setValue(0);
        TuberiaSalida2.setValue(0);
    
        porcentajeValvula.setText("0%");
        porcentajeValvulaCasa.setText("0%");
        
        ColorValvula.setBackground(Color.RED);
        ColorValvula2.setBackground(Color.RED);
        ColorValvulaCasa.setBackground(Color.RED);
        ColorValvulaCasa2.setBackground(Color.RED);
        
        
    }
    
    public void iniciarSimulacion(){
        controlLoop.iniciarSimulacion();
        securityLoop.iniciar();
    }
    
    public void detenerSimulacion() {
        progresoTanque = controlLoop.getProgresoTanque();
        System.out.println("PROGRESO TANQUE DETENER SIMULACION " + progresoTanque);
        controlLoop.actualizarTanque(progresoTanque);
        
        controlLoop.setIsRunning(false);
        controlLoop.setLlenandoTanque(false); // Detener cualquier proceso de llenado en curso
        controlLoop.setVaciandoTanque(false); // Detener cualquier proceso de vaciado en curso
        controlLoop.setValvulaAbierta(false);  // Cerrar la válvula para evitar llenado
        
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
    
    private void activarModo(boolean modoActivado) {
        System.out.println("MODO ACTIVADO" + modoActivado);
        modoAutomatico.setSelected(modoActivado);
        controlLoop.setIsAutomaticSelected(modoActivado);
        
        modoManual.setSelected(!modoActivado);
        controlLoop.setIsManualSelected(!modoActivado);
        
        AbrirValvula.setEnabled(!modoActivado);
        CerrarValvula.setEnabled(!modoActivado);
        
        if(modoAutomatico.isSelected()){
            modoManual.setEnabled(false);
        }else{
            modoAutomatico.setEnabled(false);
        }
    }
}
