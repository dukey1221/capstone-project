/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.storemanager.db;

import com.lentrix.storemanager.models.SalesItemModel;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.List;


/**
 *
 * @author lentrix
 */
public class SalesItemController {
    
    public static void insert(List<SalesItemModel> salesItems, int salesId) throws SQLException {
        Connection conn = DB.connect();
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO sales_items (`item_id`, `sales_id`, `qty`, `price`, `is_wholesale`) "
                        + "VALUES (?,?,?,?,?)");
        
        for(SalesItemModel salesItem: salesItems) {
            ps.setInt(1, salesItem.getItem().getId());
            ps.setInt(2, salesId);
            ps.setInt(3, salesItem.getQty());
            ps.setFloat(4, salesItem.getPrice());
            ps.setBoolean(5, salesItem.isIsWholeSale());
            ps.addBatch();
        }
        
        ps.executeBatch();
        
        ps.clearBatch();
        ps.close();
        
    }
    
}
