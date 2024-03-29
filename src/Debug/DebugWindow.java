/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Debug;

import main.GamePanel;

/**
 *
 * @author Dangerouze
 */
public class DebugWindow extends javax.swing.JFrame {
    static GamePanel gp;
    public DebugWindow(GamePanel gp) {
        this.gp = gp;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        NoClip = new javax.swing.JCheckBox();
        DisplayCollisionBox = new javax.swing.JCheckBox();
        ShowCameraPointer = new javax.swing.JCheckBox();
        ShowAIPath = new javax.swing.JCheckBox();
        DisplayHitBox = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        entityCount = new javax.swing.JLabel();
        disableAI = new javax.swing.JCheckBox();
        showCoordinates = new javax.swing.JCheckBox();
        disableZombieGen = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Reload Map");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Character Coordinates:");

        jLabel2.setText("jLabel2");

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(3, null, null, 1));
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });

        jLabel3.setText("Character Speed:");

        NoClip.setText("NoClip");
        NoClip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoClipActionPerformed(evt);
            }
        });

        DisplayCollisionBox.setText("Display Collision Box");
        DisplayCollisionBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisplayCollisionBoxActionPerformed(evt);
            }
        });

        ShowCameraPointer.setText("Show Camera Pointer");
        ShowCameraPointer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowCameraPointerActionPerformed(evt);
            }
        });

        ShowAIPath.setText("Show AI Path");
        ShowAIPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowAIPathActionPerformed(evt);
            }
        });

        DisplayHitBox.setText("Display Hit Box");
        DisplayHitBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisplayHitBoxActionPerformed(evt);
            }
        });

        jButton2.setText("Spawn Zombie");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Entity Count:");

        entityCount.setText("jLabel5");

        disableAI.setText("Disable AI");

        showCoordinates.setText("Show Coordinates on Tiles");
        showCoordinates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showCoordinatesActionPerformed(evt);
            }
        });

        disableZombieGen.setText("Disable Zombie Generation");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showCoordinates))
                    .addComponent(NoClip)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(DisplayCollisionBox)
                    .addComponent(ShowCameraPointer)
                    .addComponent(ShowAIPath)
                    .addComponent(DisplayHitBox)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(disableZombieGen))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(entityCount))
                    .addComponent(disableAI))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(showCoordinates))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NoClip)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DisplayCollisionBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ShowCameraPointer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ShowAIPath)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DisplayHitBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(disableZombieGen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(entityCount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(disableAI)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        gp.tileM.loadMap("Sprites/maps/map2.txt", true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void NoClipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoClipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoClipActionPerformed

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        gp.player.speed = (Integer)jSpinner1.getValue();
    }//GEN-LAST:event_jSpinner1StateChanged

    private void DisplayCollisionBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisplayCollisionBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DisplayCollisionBoxActionPerformed

    private void ShowCameraPointerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowCameraPointerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ShowCameraPointerActionPerformed

    private void ShowAIPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAIPathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ShowAIPathActionPerformed

    private void DisplayHitBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisplayHitBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DisplayHitBoxActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //gp.aSetter.addEntityRandomly("zombie", gp.waveFunction.zombie);
        gp.waveFunction.spawnRandomZombie();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void showCoordinatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showCoordinatesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_showCoordinatesActionPerformed

    
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
            java.util.logging.Logger.getLogger(DebugWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DebugWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DebugWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DebugWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new DebugWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JCheckBox DisplayCollisionBox;
    public javax.swing.JCheckBox DisplayHitBox;
    public javax.swing.JCheckBox NoClip;
    public javax.swing.JCheckBox ShowAIPath;
    public javax.swing.JCheckBox ShowCameraPointer;
    public javax.swing.JCheckBox disableAI;
    public javax.swing.JCheckBox disableZombieGen;
    public javax.swing.JLabel entityCount;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSpinner jSpinner1;
    public javax.swing.JCheckBox showCoordinates;
    // End of variables declaration//GEN-END:variables
}
