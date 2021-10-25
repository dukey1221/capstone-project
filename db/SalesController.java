/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.storemanager.db;

import com.lentrix.storemanager.Helper;
import com.lentrix.storemanager.models.SalesModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author lentrix
 */
public class SalesController {
    public static SalesModel create(float discount) throws SQLException {
        Connection conn = DB.connect();
        
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO sales (`user_id`, `discount`) "
                        + "VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        
        ps.setInt(1, Helper.currentUser.getId());
        ps.setFloat(2, discount);
        ps.executeUpdate();
        
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        
        int key = rs.getInt(1);
        
        SalesModel sales = SalesController.get(key);
        
        return sales;
    }
    
    public static SalesModel create(float discount, String date) throws SQLException {
        Connection conn = DB.connect();
        
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO sales (`date`, `user_id`, `discount`) "
                        + "VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        
        ps.setString(1, date);
        ps.setInt(2, Helper.currentUser.getId());
        ps.setFloat(3, discount);
        ps.executeUpdate();
        
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        
        int key = rs.getInt(1);
        
        SalesModel sales = SalesController.get(key);
        
        return sales;
    }
    
    
    public static SalesModel get(int id) throws SQLException {
        Connection conn = DB.connect();
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM sales WHERE id=" + id);
        if(rs.next()) {
            return new SalesModel(
                    rs.getInt("id"),
                    rs.getDate("date").toLocalDate(),
                    rs.getTime("time").toLocalTime(),
                    UserController.get(rs.getInt("user_id")),
                    rs.getFloat("discount")
            );
        }else {
            return null;
        }
    }
}
