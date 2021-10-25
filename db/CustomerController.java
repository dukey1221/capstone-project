/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.storemanager.db;

import com.lentrix.storemanager.models.CustomerModel;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.List;

/**
 *
 * @author lentrix
 */
public class CustomerController {
    
    public static List<CustomerModel> get(String filter) throws SQLException {
        ArrayList<CustomerModel> customers = new ArrayList();
        Connection conn = DB.connect();
        
        String filterStr = "";
        if(!filter.isEmpty()) {
            filterStr = " WHERE `name` LIKE '%" + filter + "%' OR `address` LIKE '%" + filter + "%'";
        }
        
        ResultSet rs = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                .executeQuery("SELECT * FROM customers " + filterStr + " ORDER BY name");
        
        rs.beforeFirst();
        
        while(rs.next()) {
            customers.add(new CustomerModel(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("phone"),
                    rs.getFloat("credit_limit")
            ));
        }
        
        return customers;
    }
    
    public static void insert(CustomerModel customer) throws SQLException {
        Connection conn = DB.connect();
        
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO customers ( `name`, `address`, `phone`, `credit_limit` ) "
                        + "VALUES (?,?,?,?)");
        ps.setString(1, customer.getName());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getPhone());
        ps.setFloat(4, customer.getCreditLimit());
        
        ps.execute();
        
        ps.close();
    }
    
    public static void update(CustomerModel customer) throws SQLException {
        Connection conn = DB.connect();
        PreparedStatement ps = conn.prepareStatement(
                "UPDATE customers SET name=?, address=?, phone=?, credit_limit=? "
                        + "WHERE id=?");
        
        ps.setString(1, customer.getName());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getPhone());
        ps.setFloat(4, customer.getCreditLimit());
        ps.setInt(5, customer.getId());
        
        ps.execute();
        
        ps.close();
    }
    
    public static void delete(CustomerModel customer) throws SQLException {
        Connection conn = DB.connect();
        PreparedStatement ps = conn.prepareCall("DELETE FROM customers WHERE id=?");
        ps.setInt(1, customer.getId());
        ps.execute();
        ps.close();
    }
}
