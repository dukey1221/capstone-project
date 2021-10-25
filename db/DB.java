/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.storemanager.db;

import com.lentrix.storemanager.Helper;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author lentrix
 */
public class DB {
    private static Connection conn;
    
    public static Connection connect() {
        if(conn==null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost/storemanager","root","");
            }catch(Exception ex) {
                Helper.error(ex.getMessage(), null);
                ex.printStackTrace();
            }
        }
        
        return conn;
    }
}
