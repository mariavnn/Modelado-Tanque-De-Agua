/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;


import controller.TanqueController;
import java.awt.Color;
import javax.swing.Timer;
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
    private TanqueController tanqueController;
    
    public TanqueAgua() {
        initComponents();
        valvula = new Valvula();
        transmisorNivel = new TransmisorNivel();
        controlNivel = new ControlNivel(valvula);
        tanqueController = new TanqueController(  Tanque, TuberiaCasa, TuberiaValvula, Valvula, 
                txtPorcentaje, porcentajeValvula, porcentajeValvulaCasa,
                botonIniciar, AbrirValvula, CerrarValvula, ColorValvula, 
                ColorValvulaCasa, modoAutomatico, modoManual, controlNivel, 
                transmisorNivel, valvula);
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
        cableTnivel2 = new javax.swing.JProgressBar();
        ColorValvulaCasa = new javax.swing.JPanel();
        cableTnivel = new javax.swing.JProgressBar();
        Valvula = new javax.swing.JLabel();
        modoAutomatico = new javax.swing.JRadioButton();
        Casa = new javax.swing.JLabel();
        modoManual = new javax.swing.JRadioButton();
        TuberiaValvula = new javax.swing.JProgressBar();
        botonIniciar = new javax.swing.JButton();
        botonDetener = new javax.swing.JButton();
        CerrarValvula = new javax.swing.JButton();
        AbrirValvula = new javax.swing.JButton();
        ValvulaCasa = new javax.swing.JLabel();
        TuberiaCasa = new javax.swing.JProgressBar();
        Fondo = new javax.swing.JLabel();
        porcentajeValvulaCasa = new javax.swing.JTextField();

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

        controlador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/control.png"))); // NOI18N
        getContentPane().add(controlador, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 64, 64));

        txtPorcentaje.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtPorcentaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPorcentajeActionPerformed(evt);
            }
        });
        getContentPane().add(txtPorcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 110, 60));

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ColorValvulaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(porcentajeValvula, javax.swing.GroupLayout.PREFERRED_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );
        ColorValvulaLayout.setVerticalGroup(
            ColorValvulaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ColorValvulaLayout.createSequentialGroup()
                .addComponent(porcentajeValvula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        getContentPane().add(ColorValvula, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 60, 40));
        ColorValvula.getAccessibleContext().setAccessibleDescription("");

        Tanque.setBackground(new Color(204, 204, 204));
        Tanque.setOrientation(1);
        Tanque.setToolTipText("");
        Tanque.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(Tanque, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 200, 270));
        Tanque.setForeground(new Color(51, 204, 255)); // Color azul (agua)
        Tanque.setMinimum(0);
        Tanque.setMaximum(100);
        Tanque.setValue(0);

        // Añadir el JProgressBar al JFrame

        cableTnivel2.setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().add(cableTnivel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 10, 220));

        ColorValvulaCasa.setBackground(new java.awt.Color(255, 51, 51));
        ColorValvulaCasa.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout ColorValvulaCasaLayout = new javax.swing.GroupLayout(ColorValvulaCasa);
        ColorValvulaCasa.setLayout(ColorValvulaCasaLayout);
        ColorValvulaCasaLayout.setHorizontalGroup(
            ColorValvulaCasaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 56, Short.MAX_VALUE)
        );
        ColorValvulaCasaLayout.setVerticalGroup(
            ColorValvulaCasaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        getContentPane().add(ColorValvulaCasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 377, 60, 40));

        cableTnivel.setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().add(cableTnivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, -1, 10));

        Valvula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/llave-de-cierre.png"))); // NOI18N
        getContentPane().add(Valvula, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, 120));

        modoAutomatico.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        modoAutomatico.setText("Automatico");
        getContentPane().add(modoAutomatico, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, -1, -1));

        Casa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/casa.png"))); // NOI18N
        getContentPane().add(Casa, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 310, 130, 130));

        modoManual.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        modoManual.setText("Manual");
        getContentPane().add(modoManual, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, 90, -1));
        getContentPane().add(TuberiaValvula, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 120, 20));

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
        getContentPane().add(botonIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, 110, 30));

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
        getContentPane().add(botonDetener, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, 110, 30));

        CerrarValvula.setBackground(new java.awt.Color(51, 153, 255));
        CerrarValvula.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        CerrarValvula.setForeground(new java.awt.Color(255, 255, 255));
        CerrarValvula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrar.png"))); // NOI18N
        CerrarValvula.setText("Cerrar Valvula");
        CerrarValvula.setBorder(null);
        getContentPane().add(CerrarValvula, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 120, 30));

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
        getContentPane().add(AbrirValvula, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 120, 30));

        ValvulaCasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/valvulaCasa.png"))); // NOI18N
        getContentPane().add(ValvulaCasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 360, 110, 80));

        TuberiaCasa.setForeground(new java.awt.Color(0, 153, 255));
        getContentPane().add(TuberiaCasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 400, 60, 30));

        Fondo.setBackground(new java.awt.Color(204, 255, 255));
        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo.png"))); // NOI18N
        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 510));

        porcentajeValvulaCasa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        porcentajeValvulaCasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porcentajeValvulaCasaActionPerformed(evt);
            }
        });
        getContentPane().add(porcentajeValvulaCasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 440, 44, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonIniciarActionPerformed
        // TODO add your handling code here:
       tanqueController.iniciarSimulacion();
       
    }//GEN-LAST:event_botonIniciarActionPerformed

    private void txtPorcentajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPorcentajeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPorcentajeActionPerformed

    private void botonDetenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDetenerActionPerformed
        // TODO add your handling code here:
        tanqueController.detenerSimulacion();
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
    private javax.swing.JLabel Casa;
    private javax.swing.JButton CerrarValvula;
    private javax.swing.JPanel ColorValvula;
    private javax.swing.JPanel ColorValvulaCasa;
    private javax.swing.JLabel Fondo;
    private javax.swing.JProgressBar Tanque;
    private javax.swing.JProgressBar TuberiaCasa;
    private javax.swing.JProgressBar TuberiaValvula;
    private javax.swing.JLabel Valvula;
    private javax.swing.JLabel ValvulaCasa;
    private javax.swing.JButton botonDetener;
    private javax.swing.JButton botonIniciar;
    private javax.swing.JProgressBar cableTnivel;
    private javax.swing.JProgressBar cableTnivel2;
    private javax.swing.JLabel controlador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton modoAutomatico;
    private javax.swing.JRadioButton modoManual;
    private javax.swing.JTextField porcentajeValvula;
    private javax.swing.JTextField porcentajeValvulaCasa;
    private javax.swing.JTextField txtPorcentaje;
    // End of variables declaration//GEN-END:variables
}
