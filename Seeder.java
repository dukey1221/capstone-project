/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.storemanager;

import com.lentrix.storemanager.db.ItemController;
import com.lentrix.storemanager.db.SalesController;
import com.lentrix.storemanager.db.SalesItemController;
import com.lentrix.storemanager.db.UserController;
import com.lentrix.storemanager.models.ItemModel;
import com.lentrix.storemanager.models.SalesItemModel;
import com.lentrix.storemanager.models.SalesModel;
import com.lentrix.storemanager.models.UserModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author lentrix
 */
public class Seeder {
    private static String randomString(int n) {
        String alphabeth = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer strb = new StringBuffer();
        for(int i=0; i<n; i++) {
            strb.append(alphabeth.charAt((int)(Math.random()*n)));
        }
        return strb.toString();
    }
    
    
    public static void seedItems(int n) {
        Random rnd = new Random();
        int barcodeSeries = 12345;
        
        try {
            for(int i=0; i<n; i++) {
                int wsp = rnd.nextInt(250)+20;
                ItemModel itm = new ItemModel(
                        -1, String.valueOf(barcodeSeries++),
                        randomString(10),
                        randomString(20),
                        randomString(5),
                        randomString(5),
                        rnd.nextInt(100),
                        wsp,
                        randomString(4),
                        wsp/10,
                        100

                );

                ItemController.insert(itm);
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static void seedSalesTransaction(int n, String date) {
        try{
            UserModel user = UserController.get(1);
            Random rnd = new Random();
            for(int i=0; i<n; i++) {
                SalesModel sales = new SalesModel(-1,null,null,user,0.00f);
                sales = SalesController.create(0.00f, date);
                System.out.println("Sales created. " + sales.getId());
                int rNum = rnd.nextInt(4)+1;
                List<SalesItemModel> items = new ArrayList();
                for(int j=0; j<rNum; j++) {
                    
                    int rndItemId = rnd.nextInt(500);
                    
                    ItemModel item = ItemController.findId(rndItemId);
                    
                    SalesItemModel sItem = new SalesItemModel(-1, item, 1, true);
                    
                    items.add(sItem);
                    
                    System.out.println("...Sales Item: " + sItem.getItem());
                }
                
                SalesItemController.insert(items, sales.getId());
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
       
    public static void main(String[] args) throws Exception {
        Helper.currentUser = UserController.get(1);
        seedSalesTransaction(60,"2021-08-03");
    }
}
