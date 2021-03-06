/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.storemanager;

import com.lentrix.storemanager.db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author lentrix
 */
public class ItemizedDailyReport extends javax.swing.JPanel {

    LocalDate dateNow = LocalDate.now();
    /**
     * Creates new form ItemizedDailyReport
     */
    public ItemizedDailyReport() {
        initComponents();
        
        dateLabel.setText("For the month of " + dateNow.format(DateTimeFormatter.ofPattern("MMMM")));
                
        String query = "SELECT items.bar_code, items.name, "
                + "SUM(sales_items.qty*sales_items.price) AS 'total' "
                + "FROM sales "
                + "LEFT JOIN sales_items ON sales_items.sales_id = sales.id "
                + "LEFT JOIN items ON sales_items.item_id = items.id "
                + "WHERE sales.date=? "
                + "GROUP BY sales_items.id";
        
        System.out.println(query);
        
        Connection conn = DB.connect();
        
        try {
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, dateNow.toString());
            
            ResultSet rs = ps.executeQuery();
            
            renderTable(rs);
        }catch(SQLException ex) {
            Helper.error(ex.getMessage(), this);
        }
    }
    
    private void renderTable(ResultSet rs) throws SQLException  {
        float total = 0;
        String[] headers = {"Bar Code", "Item Name", "Sales Amount"};
        
        DefaultTableModel model = new DefaultTableModel(headers, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        while(rs.next()) {
            model.addRow(new Object[]{
                rs.getString("bar_code"),
                rs.getString("items.name"),
                rs.getFloat("total")
            });
            
            total += rs.getFloat("total");
        }
        
        jTable1.setModel(model);
        totalLabel.setText(String.format("%,.2f", total));
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
        dateLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        totalLabel = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setText("Itemized Daily Report: ");

        dateLabel.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        dateLabel.setText("May 9, 2021");

        jPanel1.setLayout(new java.awt.BorderLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        totalLabel.setFont(new java.awt.Font("Raleway", 0, 36)); // NOI18N
        totalLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        totalLabel.setText("0.00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(totalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(dateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel totalLabel;
    // End of variables declaration//GEN-END:variables
}
