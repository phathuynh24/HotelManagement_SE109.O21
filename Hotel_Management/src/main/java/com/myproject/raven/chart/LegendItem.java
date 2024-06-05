/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.myproject.raven.chart;

/**
 *
 * @author sangq
 */
public class LegendItem extends javax.swing.JPanel {

    /**
     * Creates new form LegendItem
     */
    public LegendItem(ModelLegend data) {
        initComponents();
        setOpaque(false);
        labelColor1.setBackground(data.getColor());
        jLabel1.setText(data.getName());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelColor1 = new com.myproject.raven.chart.LabelColor();
        jLabel1 = new javax.swing.JLabel();

        labelColor1.setBackground(new java.awt.Color(0, 255, 255));
        labelColor1.setText("labelColor1");

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labelColor1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelColor1, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private com.myproject.raven.chart.LabelColor labelColor1;
    // End of variables declaration//GEN-END:variables
}