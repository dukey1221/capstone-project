/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.storemanager.models;

/**
 *
 * @author lentrix
 */
public class SalesItemModel {
    private int id;
    private ItemModel item;
    private int qty;
    private boolean isWholeSale;
    private float price;
    private SalesModel sales;

    public SalesItemModel(int id, ItemModel item, int qty, boolean isWholeSale) {
        this.id = id;
        this.item = item;
        this.qty = qty;
        this.isWholeSale = isWholeSale;
    }
    
    public SalesItemModel(int id, ItemModel item, int qty, boolean isWholeSale, SalesModel sales) {
        this.id = id;
        this.item = item;
        this.qty = qty;
        this.isWholeSale = isWholeSale;
        this.sales = sales;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public boolean isIsWholeSale() {
        return isWholeSale;
    }

    public void setIsWholeSale(boolean isWholeSale) {
        this.isWholeSale = isWholeSale;
    }

    @Override
    public String toString() {
        return this.item.getItemName() + "[" + this.qty + "]";
    }
    
    public float getPrice() {
        if(this.price==0f)
            this.price = isWholeSale ? item.getWsPrice() : item.getRtPrice();
        return this.price;
    }
    
    public float renewPrice() {
        this.price = isWholeSale ? item.getWsPrice() : item.getRtPrice();
        return this.price;
    }

    public SalesModel getSales() {
        return sales;
    }

    public void setSales(SalesModel sales) {
        this.sales = sales;
    }
}
