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
public class ItemModel {
    private int id;
    private String barCode;
    private String itemName;
    private String itemDescription;
    private String volume;
    private String wsUnit;
    private int wsQty;
    private float wsPrice;
    private String rtUnit;
    private float rtPrice;
    private int qty;

    public ItemModel(int id, String barCode, String itemName, String itemDescription, String volume, String wsUnit, int wsQty, float wsPrice, String rtUnit, float rtPrice, int qty) {
        this.id = id;
        this.barCode = barCode;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.volume = volume;
        this.wsUnit = wsUnit;
        this.wsQty = wsQty;
        this.wsPrice = wsPrice;
        this.rtUnit = rtUnit;
        this.rtPrice = rtPrice;
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getWsUnit() {
        return wsUnit;
    }

    public void setWsUnit(String wsUnit) {
        this.wsUnit = wsUnit;
    }

    public int getWsQty() {
        return wsQty;
    }

    public void setWsQty(int wsQty) {
        this.wsQty = wsQty;
    }

    public float getWsPrice() {
        return wsPrice;
    }

    public void setWsPrice(float wsPrice) {
        this.wsPrice = wsPrice;
    }

    public String getRtUnit() {
        return rtUnit;
    }

    public void setRtUnit(String rtUnit) {
        this.rtUnit = rtUnit;
    }

    public float getRtPrice() {
        return rtPrice;
    }

    public void setRtPrice(float rtPrice) {
        this.rtPrice = rtPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    
    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    @Override
    public String toString() {
        return this.itemName;
    }
}
