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
        tanqueController = new TanqueController(  Tanque, TuberiaCasa, TuberiaValvula, Valvula, txtPorcentaje, botonIniciar, AbrirValvula, CerrarValvula, ColorValvula, modoAutomatico, modoManual, controlNivel, transmisorNivel, valvula);
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
        controlador = new javax.swing.JLabel();
        txtPorcentaje = new javax.swing.JTextField();
        ColorValvula = new javax.swing.JPanel();
        Tanque = new javax.swing.JProgressBar();
        cableTnivel2 = new javax.swing.JProgressBar();
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
        TuberiaCasa = new javax.swing.JProgressBar();
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

        javax.swing.GroupLayout ColorValvulaLayout = new javax.swing.GroupLayout(ColorValvula);
        ColorValvula.setLayout(ColorValvulaLayout);
        ColorValvulaLayout.setHorizontalGroup(
            ColorValvulaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ColorValvulaLayout.setVerticalGroup(
            ColorValvulaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
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

        cableTnivel.setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().add(cableTnivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, -1, 10));

        Valvula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/llave-de-cierre.png"))); // NOI18N
        getContentPane().add(Valvula, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, 120));

        modoAutomatico.setText("Automatico");
        getContentPane().add(modoAutomatico, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, -1, -1));

        Casa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/casa.png"))); // NOI18N
        getContentPane().add(Casa, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 280, 130, 130));

        modoManual.setText("Manual");
        getContentPane().add(modoManual, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, 90, -1));
        getContentPane().add(TuberiaValvula, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 120, 20));

        botonIniciar.setText("INICIAR");
        botonIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonIniciarActionPerformed(evt);
            }
        });
        getContentPane().add(botonIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, 110, 30));

        botonDetener.setText("DETENER");
        botonDetener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDetenerActionPerformed(evt);
            }
        });
        getContentPane().add(botonDetener, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, 110, 30));

        CerrarValvula.setText("CerrarValvula");
        getContentPane().add(CerrarValvula, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, -1, -1));

        AbrirValvula.setText("Abrir Valvula");
        getContentPane().add(AbrirValvula, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 110, -1));
        getContentPane().add(TuberiaCasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 380, 120, 20));

        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fond2.png"))); // NOI18N
        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, 470));

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
    private javax.swing.JLabel Fondo;
    private javax.swing.JProgressBar Tanque;
    private javax.swing.JProgressBar TuberiaCasa;
    private javax.swing.JProgressBar TuberiaValvula;
    private javax.swing.JLabel Valvula;
    private javax.swing.JButton botonDetener;
    private javax.swing.JButton botonIniciar;
    private javax.swing.JProgressBar cableTnivel;
    private javax.swing.JProgressBar cableTnivel2;
    private javax.swing.JLabel controlador;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton modoAutomatico;
    private javax.swing.JRadioButton modoManual;
    private javax.swing.JTextField txtPorcentaje;
    // End of variables declaration//GEN-END:variables
}
