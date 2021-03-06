/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.storemanager;

import com.lentrix.storemanager.db.SalesController;
import com.lentrix.storemanager.db.SalesItemController;
import com.lentrix.storemanager.models.SalesItemModel;
import com.lentrix.storemanager.models.SalesModel;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author hawkm
 */
public class FinalizeDialog extends javax.swing.JDialog {
    private List<SalesItemModel> salesItems;
    private float discount = 0f;
    private float total;
    private float subTotal;
    private float change;
    private float cashTender;
    java.awt.Frame parent;
    
    private final int PWIDTH=32;
    /**
     * Creates new form FinalizeDialog
     */
    public FinalizeDialog(java.awt.Frame parent, boolean modal, List<SalesItemModel> salesItems) {
        super(parent, modal);
        initComponents();
        this.parent = parent;
        this.salesItems = salesItems;
        
        total = computeTotal();
        totalField.setText(String.format("%,.2f", total));
        
        updateSubTotal();
        
        discountField.setSelectionStart(0);
        discountField.setSelectionEnd(discountField.getText().length());
    }
    
    private void updateSubTotal() {
         subTotal = total - discount;
         subTotalField.setText(String.format("%,.2f", subTotal));
    }
    
    private float computeTotal() {
        float total = 0;
        for(SalesItemModel salesItem: salesItems) {
            total += salesItem.getPrice()*salesItem.getQty();
        }
        return total;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        totalField = new javax.swing.JTextField();
        discountField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        subTotalField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cashTenderField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Finalize Sales");
        setModal(true);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Sales Total:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Discount:");

        totalField.setEditable(false);
        totalField.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        totalField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        totalField.setFocusable(false);

        discountField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        discountField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        discountField.setText("0.00");
        discountField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountFieldActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Sub Total:");

        subTotalField.setEditable(false);
        subTotalField.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        subTotalField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        subTotalField.setFocusable(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Cash Tender:");

        cashTenderField.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cashTenderField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        cashTenderField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashTenderFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 85, Short.MAX_VALUE)
                                .addComponent(discountField, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(totalField))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(subTotalField)
                            .addComponent(cashTenderField))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(totalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(discountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(subTotalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cashTenderField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(110, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(479, 361));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cashTenderFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashTenderFieldActionPerformed
        String cashTenderStr = cashTenderField.getText();
        try {
            cashTender = Float.parseFloat(cashTenderStr);
            
            if(cashTender<subTotal) {
                Helper.error("The cash tender is less than the sub total", this);
                cashTenderField.setSelectionStart(0);
                cashTenderField.setSelectionEnd(cashTenderStr.length());
                cashTenderField.grabFocus();
                return;
            }
            
            change = cashTender-subTotal;
            
            saveTransaction();
            
            MyPrintService.printString(createPrintableString());
            
            salesItems.clear();
            
        }catch(NumberFormatException ex) {
            Helper.error("You entered an invalid value.", this);
            cashTenderField.setSelectionStart(0);
            cashTenderField.setSelectionEnd(cashTenderStr.length());
            cashTenderField.grabFocus();
        }catch(Exception ex) {
            ex.printStackTrace();
            Helper.error("There is an unknown problem. Please contact the developer.", this);
        }
    }//GEN-LAST:event_cashTenderFieldActionPerformed

    private String createPrintableString() {
        StringBuffer strBuff = new StringBuffer();
        
        strBuff.append(Helper.centerString("S & F Store", PWIDTH) + "\n");
        strBuff.append(Helper.centerString("Poblacion, Ubay, Bohol", PWIDTH) + "\n\n");
        strBuff.append(Helper.centerString("------Sales Invoice------", PWIDTH) + "\n");
        strBuff.append("\n");
        
        for(SalesItemModel item: salesItems) {
            String prod=Helper.strMax(item.getItem().getItemName(), PWIDTH-10);
            String price=Helper.strPad(String.format("%,.2f", item.getPrice()*item.getQty()), 9);
            strBuff.append(Helper.expander(prod,price,PWIDTH,'.') + "\n");
        }
        
        String totalStr = Helper.strPad(String.format("%,.2f", total), 9);
        String discStr = Helper.strPad(String.format("%,.2f", discount), 9);
        String subTotalStr = Helper.strPad(String.format("%,.2f", subTotal), 9);
        String cashTenderStr = Helper.strPad(String.format("%,.2f", cashTender), 9);
        String changeStr = Helper.strPad(String.format("%,.2f", change), 9);
        
        strBuff.append(Helper.expander("","----------",PWIDTH,' ') + "\n");
        strBuff.append(Helper.expander("Total", totalStr, PWIDTH,'.'));
        strBuff.append(Helper.expander("Discount", discStr, PWIDTH,'.'));
        strBuff.append(Helper.expander("Sub Total", subTotalStr, PWIDTH,'.'));
        strBuff.append(Helper.expander("","==========",PWIDTH,' ') + "\n");
        strBuff.append(Helper.expander("Cash Tender", cashTenderStr, PWIDTH,'.'));
        strBuff.append(Helper.expander("CHANGE", changeStr, PWIDTH,'.'));
        strBuff.append("\n");
        strBuff.append(Helper.centerString("=== THANK YOU ===", PWIDTH));
                
        strBuff.append("\n\n");
        
        return strBuff.toString();
    }
    
    private void saveTransaction() {
        try {
            SalesModel sales = SalesController.create(discount);
            SalesItemController.insert(salesItems, sales.getId());
            salesItems.clear();
            this.dispose();
            new ShowChangeDialog(parent, true, change).setVisible(true);
        }catch(SQLException ex) {
            Helper.error(ex.getMessage(), this);
            ex.printStackTrace();
        }
    }
    
    private void discountFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountFieldActionPerformed
        String discountStr = discountField.getText();
        try {    
            discount = Float.parseFloat(discountStr);
            updateSubTotal();
            cashTenderField.grabFocus();
        }catch(NumberFormatException ex) {
            Helper.error("You entered an invalid value", this);
            discountField.setSelectionStart(0);
            discountField.setSelectionEnd(discountStr.length());
            discountField.grabFocus();
        }
    }//GEN-LAST:event_discountFieldActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cashTenderField;
    private javax.swing.JTextField discountField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField subTotalField;
    private javax.swing.JTextField totalField;
    // End of variables declaration//GEN-END:variables
}
