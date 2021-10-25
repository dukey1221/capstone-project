/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.storemanager.db;

import com.lentrix.storemanager.models.ItemModel;
import java.sql.Connection;
import java.util.List;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
/**
 *
 * @author lentrix
 */
public class ItemController {
    
    public static List<ItemModel> get(String filter) throws SQLException {
        Connection conn = DB.connect();
        
        String filterString = "";
        
        if(!filter.isEmpty()) {
            filterString = "WHERE name LIKE '%" + filter + "%' OR description LIKE '%" + filter + "%'";
        }
        
        ResultSet rs = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                .executeQuery("SELECT * FROM items " + filterString + " ORDER BY name, description");
        
        ArrayList<ItemModel> items = new ArrayList();
        
        rs.beforeFirst();
        while(rs.next()) {
            items.add(new ItemModel(
                    rs.getInt("id"),
                    rs.getString("bar_code"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("volume"),
                    rs.getString("ws_unit"),
                    rs.getInt("ws_qty"),
                    rs.getFloat("ws_price"),
                    rs.getString("rt_unit"),
                    rs.getFloat("rt_price"),
                    rs.getInt("qty")
            ));
        }
        
        return items;
    }
    
    public static void insert(ItemModel item) throws SQLException {
        Connection conn = DB.connect();
        
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO items (bar_code, name, description, volume, ws_unit, ws_qty, ws_price, rt_unit, rt_price, qty) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1, item.getBarCode());
        ps.setString(2, item.getItemName());
        ps.setString(3, item.getItemDescription());
        ps.setString(4, item.getVolume());
        ps.setString(5, item.getWsUnit());
        ps.setInt(6, item.getWsQty());
        ps.setFloat(7, item.getWsPrice());
        ps.setString(8, item.getRtUnit());
        ps.setFloat(9, item.getRtPrice());
        ps.setInt(10, item.getQty());
        
        ps.execute();
    }
    
    public static void update(ItemModel item) throws SQLException {
        Connection conn = DB.connect();
        PreparedStatement ps = conn.prepareStatement("UPDATE items "
                + "SET bar_code=?, name=?, description=?, volume=?, ws_unit=?, "
                + "ws_qty=?, ws_price=?, rt_unit=?, rt_price=?, qty=? "
                + "WHERE id=?");
        
        ps.setString(1, item.getBarCode());
        ps.setString(2, item.getItemName());
        ps.setString(3, item.getItemDescription());
        ps.setString(4, item.getVolume());
        ps.setString(5, item.getWsUnit());
        ps.setInt(6, item.getWsQty());
        ps.setFloat(7, item.getWsPrice());
        ps.setString(8, item.getRtUnit());
        ps.setFloat(9, item.getRtPrice());
        ps.setInt(10, item.getQty());
        ps.setInt(11, item.getId());
        
        ps.execute();
    }
    
    public static void delete(ItemModel item) throws SQLException {
        Connection conn = DB.connect();
        PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM items WHERE id=?");
        ps.setInt(1, item.getId());
        ps.execute();
    }
    
    public static ItemModel findCode(String barCode) throws SQLException {
        Connection conn = DB.connect();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM items WHERE bar_code=?");
        ps.setString(1, barCode);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            return new ItemModel(
                    rs.getInt("id"),
                    rs.getString("bar_code"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("volume"),
                    rs.getString("ws_unit"),
                    rs.getInt("ws_qty"),
                    rs.getFloat("ws_price"),
                    rs.getString("rt_unit"),
                    rs.getFloat("rt_price"),
                    rs.getInt("qty"));
        }else {
            return null;
        }
    }
    
    public static ItemModel findId(int id) throws SQLException {
        Connection conn = DB.connect();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM items WHERE id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()) {
            return new ItemModel(
                    rs.getInt("id"),
                    rs.getString("bar_code"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("volume"),
                    rs.getString("ws_unit"),
                    rs.getInt("ws_qty"),
                    rs.getFloat("ws_price"),
                    rs.getString("rt_unit"),
                    rs.getFloat("rt_price"),
                    rs.getInt("qty"));
        }
        
        return null;
    }
}
