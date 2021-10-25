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
public class CustomerModel {
    private int id;
    private String name;
    private String address;
    private String phone;
    private float creditLimit;

    public CustomerModel(int id, String name, String address, String phone, float creditLimit) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.creditLimit = creditLimit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(float creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Override
    public String toString() {
        return this.name;
    }
    
    
}
