/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;


import controller.ControlLoop;
import controller.SecurityLoop;
import controller.TanqueController;
import java.awt.Color;
import javax.swing.Timer;
import model.CajaSeguridad;
import model.ControlNivel;
import model.TransmisorNivel;
import model.Valvula;

/**
 *
 * @author maria
 */
public class TanqueAgua extends javax.swing.JFrame {

    /**
     * Creates new form TanqueAgua
     */
    private Valvula valvula;
    private TransmisorNivel transmisorNivel;
    private ControlNivel controlNivel;
    private CajaSeguridad cajaSeguridad;
    private TanqueController tanqueController;
    private ControlLoop controlLoop;
    private SecurityLoop securityLoop;
    
    public TanqueAgua() {
        initComponents();
        valvula = new Valvula();
        transmisorNivel = new TransmisorNivel();
        controlNivel = new ControlNivel();
        cajaSeguridad = new CajaSeguridad(Alerta);
        
        controlLoop = new ControlLoop(Tanque, ColorValvulaCasa, ColorValvulaCasa2, porcentajeValvulaCasa, porcentajeValvula, 
                TuberiaEntrada1, TuberiaEntrada2, TuberiaSalida1, TuberiaSalida2, txtPorcentaje, ColorValvula, ColorValvula2, cajaSeguridad, controlNivel, transmisorNivel, valvula);
        securityLoop = new SecurityLoop(transmisorNivel, cajaSeguridad, controlLoop, porcentajeValvula);
        
        tanqueController = new TanqueController(Tanque, AbrirValvula, CerrarValvula, botonIniciar, botonDetener, 
                modoAutomatico, modoManual, TuberiaEntrada1, TuberiaEntrada2, TuberiaSalida1, TuberiaSalida2, 
                Valvula, ValvulaEntradaSeg, ValvulaSalidaSeg, txtPorcentaje, ColorValvula, ColorValvula2, ColorValvulaCasa, 
                ColorValvulaCasa2, porcentajeValvulaCasa, porcentajeValvula, CajaSeguridad, controlNivel, transmisorNivel, 
                valvula, controlLoop, securityLoop);
        
   
        
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        controlador = new javax.swing.JLabel();
        txtPorcentaje = new javax.swing.JTextField();
        ColorValvula = new javax.swing.JPanel();
        porcentajeValvula = new javax.swing.JTextField();
        Tanque = new javax.swing.JProgressBar();
        ColorValvulaCasa2 = new javax.swing.JPanel();
        ColorValvula2 = new javax.swing.JPanel();
        ColorValvulaCasa = new javax.swing.JPanel();
        porcentajeValvulaCasa = new javax.swing.JTextField();
        cableTnivel = new javax.swing.JProgressBar();
        Valvula = new javax.swing.JLabel();
        modoAutomatico = new javax.swing.JRadioButton();
        Casa = new javax.swing.JLabel();
        modoManual = new javax.swing.JRadioButton();
        botonIniciar = new javax.swing.JButton();
        botonDetener = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        ValvulaEntradaSeg = new javax.swing.JLabel();
        cableTnivel2 = new javax.swing.JProgressBar();
        TuberiaEntrada1 = new javax.swing.JProgressBar();
        CerrarValvula = new javax.swing.JButton();
        ValvulaSegEntrada = new javax.swing.JLabel();
        AbrirValvula = new javax.swing.JButton();
        ValvulaCasa = new javax.swing.JLabel();
        ValvulaSalidaSeg = new javax.swing.JLabel();
        TuberiaEntrada2 = new javax.swing.JProgressBar();
        CajaSeguridad = new javax.swing.JLabel();
        TransmisorLevelSeg = new javax.swing.JLabel();
        jProgressBar2 = new javax.swing.JProgressBar();
        TuberiaSalida2 = new javax.swing.JProgressBar();
        jProgressBar4 = new javax.swing.JProgressBar();
        Alerta = new javax.swing.JTextField();
        cableTnivel3 = new javax.swing.JProgressBar();
        jProgressBar5 = new javax.swing.JProgressBar();
        jProgressBar1 = new javax.swing.JProgressBar();
        jProgressBar3 = new javax.swing.JProgressBar();
        jProgressBar7 = new javax.swing.JProgressBar();
        jProgressBar8 = new javax.swing.JProgressBar();
        jProgressBar6 = new javax.swing.JProgressBar();
        cableTnivel1 = new javax.swing.JProgressBar();
        TuberiaSalida1 = new javax.swing.JProgressBar();
        Fondo = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ventana");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        controlador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/circulo1.png"))); // NOI18N
        getContentPane().add(controlador, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, 64, 64));

        txtPorcentaje.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtPorcentaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPorcentajeActionPerformed(evt);
            }
        });
        getContentPane().add(txtPorcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 110, 50));

        ColorValvula.setBackground(new java.awt.Color(255, 51, 51));
        ColorValvula.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        porcentajeValvula.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        porcentajeValvula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porcentajeValvulaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ColorValvulaLayout = new javax.swing.GroupLayout(ColorValvula);
        ColorValvula.setLayout(ColorValvulaLayout);
        ColorValvulaLayout.setHorizontalGroup(
            ColorValvulaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ColorValvulaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(porcentajeValvula, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ColorValvulaLayout.setVerticalGroup(
            ColorValvulaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ColorValvulaLayout.createSequentialGroup()
                .addComponent(porcentajeValvula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        getContentPane().add(ColorValvula, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 120, 60, 40));
        ColorValvula.getAccessibleContext().setAccessibleDescription("");

        Tanque.setBackground(new Color(204, 204, 204));
        Tanque.setOrientation(1);
        Tanque.setToolTipText("");
        Tanque.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(Tanque, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 230, 220, 280));
        Tanque.setForeground(new Color(51, 204, 255)); // Color azul (agua)
        Tanque.setMinimum(0);
        Tanque.setMaximum(100);
        Tanque.setValue(0);

        // Añadir el JProgressBar al JFrame

        javax.swing.GroupLayout ColorValvulaCasa2Layout = new javax.swing.GroupLayout(ColorValvulaCasa2);
        ColorValvulaCasa2.setLayout(ColorValvulaCasa2Layout);
        ColorValvulaCasa2Layout.setHorizontalGroup(
            ColorValvulaCasa2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        ColorValvulaCasa2Layout.setVerticalGroup(
            ColorValvulaCasa2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        getContentPane().add(ColorValvulaCasa2, new org.netbeans.lib.awtextra.AbsoluteConstraints(632, 490, 40, 20));

        javax.swing.GroupLayout ColorValvula2Layout = new javax.swing.GroupLayout(ColorValvula2);
        ColorValvula2.setLayout(ColorValvula2Layout);
        ColorValvula2Layout.setHorizontalGroup(
            ColorValvula2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        ColorValvula2Layout.setVerticalGroup(
            ColorValvula2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        getContentPane().add(ColorValvula2, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 135, 40, 20));

        ColorValvulaCasa.setBackground(new java.awt.Color(255, 51, 51));
        ColorValvulaCasa.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        porcentajeValvulaCasa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        porcentajeValvulaCasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porcentajeValvulaCasaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ColorValvulaCasaLayout = new javax.swing.GroupLayout(ColorValvulaCasa);
        ColorValvulaCasa.setLayout(ColorValvulaCasaLayout);
        ColorValvulaCasaLayout.setHorizontalGroup(
            ColorValvulaCasaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ColorValvulaCasaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(porcentajeValvulaCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ColorValvulaCasaLayout.setVerticalGroup(
            ColorValvulaCasaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ColorValvulaCasaLayout.createSequentialGroup()
                .addComponent(porcentajeValvulaCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        getContentPane().add(ColorValvulaCasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(476, 470, 60, 40));

        cableTnivel.setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().add(cableTnivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 60, 10));

        Valvula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/llave-de-cierre.png"))); // NOI18N
        getContentPane().add(Valvula, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 88, 130, 150));

        modoAutomatico.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        modoAutomatico.setText("Automatico");
        getContentPane().add(modoAutomatico, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 190, -1, -1));

        Casa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/casa.png"))); // NOI18N
        getContentPane().add(Casa, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 390, 130, 130));

        modoManual.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        modoManual.setText("Manual");
        getContentPane().add(modoManual, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 230, 90, -1));

        botonIniciar.setBackground(new java.awt.Color(51, 153, 255));
        botonIniciar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botonIniciar.setForeground(new java.awt.Color(255, 255, 255));
        botonIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/proximo.png"))); // NOI18N
        botonIniciar.setText("Iniciar");
        botonIniciar.setBorder(null);
        botonIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonIniciarActionPerformed(evt);
            }
        });
        getContentPane().add(botonIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 60, 110, 30));

        botonDetener.setBackground(new java.awt.Color(0, 153, 255));
        botonDetener.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botonDetener.setForeground(new java.awt.Color(255, 255, 255));
        botonDetener.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/prohibido.png"))); // NOI18N
        botonDetener.setText("Detener");
        botonDetener.setBorder(null);
        botonDetener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDetenerActionPerformed(evt);
            }
        });
        getContentPane().add(botonDetener, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 110, 110, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/circulo2.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        ValvulaEntradaSeg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/valvulaCasa.png"))); // NOI18N
        getContentPane().add(ValvulaEntradaSeg, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 80, -1, -1));

        cableTnivel2.setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().add(cableTnivel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 10, 380));

        TuberiaEntrada1.setForeground(new java.awt.Color(102, 204, 255));
        getContentPane().add(TuberiaEntrada1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 20, 20));

        CerrarValvula.setBackground(new java.awt.Color(51, 153, 255));
        CerrarValvula.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        CerrarValvula.setForeground(new java.awt.Color(255, 255, 255));
        CerrarValvula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrar.png"))); // NOI18N
        CerrarValvula.setText("Cerrar Valvula");
        CerrarValvula.setBorder(null);
        getContentPane().add(CerrarValvula, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, 120, 30));

        ValvulaSegEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/tuberia1.png"))); // NOI18N
        getContentPane().add(ValvulaSegEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 108, -1, 50));

        AbrirValvula.setBackground(new java.awt.Color(51, 153, 255));
        AbrirValvula.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AbrirValvula.setForeground(new java.awt.Color(255, 255, 255));
        AbrirValvula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrar-con-llave.png"))); // NOI18N
        AbrirValvula.setText("Abrir Valvula");
        AbrirValvula.setBorder(null);
        AbrirValvula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirValvulaActionPerformed(evt);
            }
        });
        getContentPane().add(AbrirValvula, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, 120, 30));

        ValvulaCasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/valvulaCasa.png"))); // NOI18N
        getContentPane().add(ValvulaCasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 437, 110, 80));

        ValvulaSalidaSeg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/tuberia1.png"))); // NOI18N
        getContentPane().add(ValvulaSalidaSeg, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 465, 70, 50));

        TuberiaEntrada2.setForeground(new java.awt.Color(102, 204, 255));
        getContentPane().add(TuberiaEntrada2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 80, 20));

        CajaSeguridad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cajaSeguridad.png"))); // NOI18N
        getContentPane().add(CajaSeguridad, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 190, -1, 90));

        TransmisorLevelSeg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/circulo1.png"))); // NOI18N
        getContentPane().add(TransmisorLevelSeg, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 330, -1, -1));

        jProgressBar2.setBackground(new java.awt.Color(204, 0, 204));
        getContentPane().add(jProgressBar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 90, 10, 250));

        TuberiaSalida2.setForeground(new java.awt.Color(102, 204, 255));
        getContentPane().add(TuberiaSalida2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 490, 40, 18));

        jProgressBar4.setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jProgressBar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 10, 240));

        Alerta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Alerta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlertaActionPerformed(evt);
            }
        });
        getContentPane().add(Alerta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 490, 40));

        cableTnivel3.setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().add(cableTnivel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 490, 60, 10));

        jProgressBar5.setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jProgressBar5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 530, 440, 10));

        jProgressBar1.setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 510, 10, 30));

        jProgressBar3.setBackground(new java.awt.Color(204, 0, 204));
        getContentPane().add(jProgressBar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 310, 10));

        jProgressBar7.setBackground(new java.awt.Color(204, 0, 204));
        getContentPane().add(jProgressBar7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 10, 40));

        jProgressBar8.setBackground(new java.awt.Color(204, 0, 204));
        getContentPane().add(jProgressBar8, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 370, 200, 10));

        jProgressBar6.setBackground(new java.awt.Color(204, 0, 204));
        getContentPane().add(jProgressBar6, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 370, 10, 120));

        cableTnivel1.setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().add(cableTnivel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 130, 10));

        TuberiaSalida1.setForeground(new java.awt.Color(102, 204, 255));
        getContentPane().add(TuberiaSalida1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 488, 72, 18));

        Fondo.setBackground(new java.awt.Color(204, 255, 255));
        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo1.png"))); // NOI18N
        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 980, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonIniciarActionPerformed
        // TODO add your handling code here:
      // tanqueController.iniciarSimulacion();
       
    }//GEN-LAST:event_botonIniciarActionPerformed

    private void txtPorcentajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPorcentajeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPorcentajeActionPerformed

    private void botonDetenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDetenerActionPerformed
        // TODO add your handling code here:
        //tanqueController.detenerSimulacion();
    }//GEN-LAST:event_botonDetenerActionPerformed

    private void porcentajeValvulaCasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porcentajeValvulaCasaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_porcentajeValvulaCasaActionPerformed

    private void porcentajeValvulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porcentajeValvulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_porcentajeValvulaActionPerformed

    private void AbrirValvulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbrirValvulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AbrirValvulaActionPerformed

    private void AlertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlertaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AlertaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TanqueAgua.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TanqueAgua.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TanqueAgua.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TanqueAgua.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TanqueAgua().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AbrirValvula;
    private javax.swing.JTextField Alerta;
    private javax.swing.JLabel CajaSeguridad;
    private javax.swing.JLabel Casa;
    private javax.swing.JButton CerrarValvula;
    private javax.swing.JPanel ColorValvula;
    private javax.swing.JPanel ColorValvula2;
    private javax.swing.JPanel ColorValvulaCasa;
    private javax.swing.JPanel ColorValvulaCasa2;
    private javax.swing.JLabel Fondo;
    private javax.swing.JProgressBar Tanque;
    private javax.swing.JLabel TransmisorLevelSeg;
    private javax.swing.JProgressBar TuberiaEntrada1;
    private javax.swing.JProgressBar TuberiaEntrada2;
    private javax.swing.JProgressBar TuberiaSalida1;
    private javax.swing.JProgressBar TuberiaSalida2;
    private javax.swing.JLabel Valvula;
    private javax.swing.JLabel ValvulaCasa;
    private javax.swing.JLabel ValvulaEntradaSeg;
    private javax.swing.JLabel ValvulaSalidaSeg;
    private javax.swing.JLabel ValvulaSegEntrada;
    private javax.swing.JButton botonDetener;
    private javax.swing.JButton botonIniciar;
    private javax.swing.JProgressBar cableTnivel;
    private javax.swing.JProgressBar cableTnivel1;
    private javax.swing.JProgressBar cableTnivel2;
    private javax.swing.JProgressBar cableTnivel3;
    private javax.swing.JLabel controlador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JProgressBar jProgressBar4;
    private javax.swing.JProgressBar jProgressBar5;
    private javax.swing.JProgressBar jProgressBar6;
    private javax.swing.JProgressBar jProgressBar7;
    private javax.swing.JProgressBar jProgressBar8;
    private javax.swing.JRadioButton modoAutomatico;
    private javax.swing.JRadioButton modoManual;
    private javax.swing.JTextField porcentajeValvula;
    private javax.swing.JTextField porcentajeValvulaCasa;
    private javax.swing.JTextField txtPorcentaje;
    // End of variables declaration//GEN-END:variables
}
