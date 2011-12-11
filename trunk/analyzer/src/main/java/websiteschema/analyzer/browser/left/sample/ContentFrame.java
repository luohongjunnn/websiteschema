/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ContentFrame.java
 *
 * Created on Dec 10, 2011, 11:14:55 PM
 */
package websiteschema.analyzer.browser.left.sample;

import websiteschema.analyzer.browser.utils.TextAreaSearch;
import websiteschema.analyzer.context.BrowserContext;
import websiteschema.model.domain.cluster.DocUnits;
import websiteschema.model.domain.cluster.Sample;
import websiteschema.model.domain.cluster.Unit;
import websiteschema.persistence.hbase.SampleMapper;

/**
 *
 * @author ray
 */
public class ContentFrame extends javax.swing.JFrame {

    TextAreaSearch tas;
    String sample;

    /** Creates new form ContentFrame */
    public ContentFrame() {
        initComponents();
        this.contentTextArea.setLineWrap(this.wrapLineCheckBox.isSelected());
        tas = new TextAreaSearch(this.contentTextArea);
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
        update();
    }

    private void update() {
        SampleMapper mapper = BrowserContext.getSpringContext().getBean("sampleMapper", SampleMapper.class);
        this.contentTextArea.setText("");
        Sample s = mapper.get(sample);
        DocUnits docUnits = s.getContent();
        Unit[] units = docUnits.getUnits();
        if (null != units) {
            setTitle(s.getUrl());
            setVisible(true);
            for (Unit unit : units) {
                this.contentTextArea.append(unit.xpath + " -> " + unit.text.trim() + "\n");
            }
            this.contentTextArea.setCaretPosition(0);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        nextButton = new javax.swing.JButton();
        lastButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        wrapLineCheckBox = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        contentTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        jLabel1.setText("搜索: ");
        jToolBar1.add(jLabel1);

        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });
        jToolBar1.add(searchField);

        nextButton.setText("下一个");
        nextButton.setFocusable(false);
        nextButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nextButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(nextButton);

        lastButton.setText("上一个");
        lastButton.setFocusable(false);
        lastButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lastButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lastButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(lastButton);

        jLabel2.setText("折行: ");
        jToolBar1.add(jLabel2);

        wrapLineCheckBox.setFocusable(false);
        wrapLineCheckBox.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        wrapLineCheckBox.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        wrapLineCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wrapLineCheckBoxActionPerformed(evt);
            }
        });
        jToolBar1.add(wrapLineCheckBox);

        contentTextArea.setColumns(20);
        contentTextArea.setRows(5);
        jScrollPane1.setViewportView(contentTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        // TODO add your handling code here:
        String target = this.searchField.getText();
        tas.next(target);
    }//GEN-LAST:event_nextButtonActionPerformed

    private void lastButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastButtonActionPerformed
        // TODO add your handling code here:
        String target = this.searchField.getText();
        tas.last(target);
    }//GEN-LAST:event_lastButtonActionPerformed

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        // TODO add your handling code here:
        String target = this.searchField.getText();
        tas.next(target);
    }//GEN-LAST:event_searchFieldActionPerformed

    private void wrapLineCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wrapLineCheckBoxActionPerformed
        // TODO add your handling code here:
        this.contentTextArea.setLineWrap(this.wrapLineCheckBox.isSelected());
    }//GEN-LAST:event_wrapLineCheckBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea contentTextArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton lastButton;
    private javax.swing.JButton nextButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JCheckBox wrapLineCheckBox;
    // End of variables declaration//GEN-END:variables
}
