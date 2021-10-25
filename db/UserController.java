/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.storemanager.db;

import com.lentrix.storemanager.models.UserModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lentrix
 */
public class UserController {
    public static UserModel login(String username, String password) throws SQLException {
        Connection conn = DB.connect();
        
        PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM users WHERE username=? AND PASSWORD=MD5(?)", 
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if(rs.first()) {
            return new UserModel(
                    rs.getInt("id"),
                    rs.getString("fullname"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getTimestamp("last_login").toLocalDateTime()
            );
        }else{
            return null;
        }
    }
    
    public static void insert(UserModel user) throws SQLException {
        Connection conn = DB.connect();
        
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users (fullname, username, password, role) "
                + "VALUES (?,?,MD5(?),?)");
        ps.setString(1, user.getFullname());
        ps.setString(2, user.getUsername());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getRole());
        
        int ins = ps.executeUpdate();
    }
    
    public static void update(UserModel user) throws SQLException
    {
        Connection conn = DB.connect();
        
        PreparedStatement ps = conn.prepareStatement("UPDATE users SET "
                + "fullname=?, username=?, role=? "
                + "WHERE id=?");
        ps.setString(1, user.getFullname());
        ps.setString(2, user.getUsername());
        ps.setString(3, user.getRole());
        ps.setInt(4, user.getId());
        
        ps.executeUpdate();
    }
    
    public static List<UserModel> get(String filter) throws SQLException {
        Connection conn = DB.connect();
        ResultSet rs = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                .executeQuery("SELECT * FROM users " + filter);
        
        ArrayList<UserModel> users = new ArrayList<UserModel>();
        
        rs.beforeFirst();
        
        while(rs.next()) {
            users.add(new UserModel(
                    rs.getInt("id"),
                    rs.getString("fullname"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getTimestamp("last_login").toLocalDateTime()
            ));
        }
        
        return users;
    }
    
    public static void delete(UserModel user) throws SQLException {
        Connection conn = DB.connect();
        
        PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE id=?");
        ps.setInt(1, user.getId());
        ps.execute();
    }
    
    public static UserModel get(int id) throws SQLException {
        Connection conn = DB.connect();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM users WHERE id=" + id);
        
        if(rs.next()) {
            return new UserModel(
                    rs.getInt("id"),
                    rs.getString("fullname"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getTimestamp("last_login").toLocalDateTime()
            );
        }else {
            return null;
        }
    }
}
