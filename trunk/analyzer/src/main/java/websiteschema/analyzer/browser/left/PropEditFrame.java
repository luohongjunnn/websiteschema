/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PropEditFrame.java
 *
 * Created on Dec 30, 2011, 6:46:25 PM
 */
package websiteschema.analyzer.browser.left;

import java.util.Map;
import websiteschema.analyzer.browser.utils.TextAreaSearch;

/**
 *
 * @author ray
 */
public class PropEditFrame extends javax.swing.JFrame {

    AnalysisPanel analysisPanel;
    String propName;
    String propValue;
    TextAreaSearch tas;

    /** Creates new form PropEditFrame */
    public PropEditFrame() {
        initComponents();

        tas = new TextAreaSearch(this.displayArea);

        int screenWidth = ((int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
        int screenHeight = ((int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
        int sizeWidth = this.getWidth();
        int sizeHeight = this.getHeight();
        this.setLocation((screenWidth - sizeWidth) / 2, (screenHeight - sizeHeight) / 2);

        displayArea.setLineWrap(this.wrapLineCheckBox.isSelected());
        displayArea.updateUI();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        displayArea = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        confirmButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        wrapLineCheckBox = new javax.swing.JCheckBox();
        searchField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        displayArea.setColumns(20);
        displayArea.setRows(5);
        jScrollPane1.setViewportView(displayArea);

        confirmButton.setText("确定");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        resetButton.setText("还原");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        wrapLineCheckBox.setSelected(true);
        wrapLineCheckBox.setText("自动换行");
        wrapLineCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wrapLineCheckBoxActionPerformed(evt);
            }
        });

        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });

        jLabel1.setText("搜索:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(confirmButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resetButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchField, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wrapLineCheckBox))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmButton)
                    .addComponent(resetButton)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wrapLineCheckBox)
                    .addComponent(jLabel1))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        // TODO add your handling code here:
        Map<String, String> prop = analysisPanel.getProperties();
        prop.put(propName, getPropValue());
        analysisPanel.setProperties(prop);
        analysisPanel.save();
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        // TODO add your handling code here:
        Map<String, String> prop = analysisPanel.getProperties();
        String value = prop.get(propName);
        this.displayArea.setText(value);
    }//GEN-LAST:event_resetButtonActionPerformed

    private void wrapLineCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wrapLineCheckBoxActionPerformed
        // TODO add your handling code here:
        displayArea.setLineWrap(this.wrapLineCheckBox.isSelected());
        displayArea.updateUI();
    }//GEN-LAST:event_wrapLineCheckBoxActionPerformed

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        // TODO add your handling code here:
        String target = this.searchField.getText();
        tas.next(target);
    }//GEN-LAST:event_searchFieldActionPerformed

    private String getPropValue() {
        return this.displayArea.getText();
    }

    public void setAnalysisPanel(AnalysisPanel analysisPanel) {
        this.analysisPanel = analysisPanel;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public void setPropValue(String propValue) {
        this.propValue = propValue;
        this.displayArea.setText(propValue);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton confirmButton;
    private javax.swing.JTextArea displayArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton resetButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JCheckBox wrapLineCheckBox;
    // End of variables declaration//GEN-END:variables
}
