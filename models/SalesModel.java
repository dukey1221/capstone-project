/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.storemanager.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author lentrix
 */
public class SalesModel {
    private int id;
    private LocalDate date;
    private LocalTime time;
    private UserModel user;
    private float discount;

    public SalesModel(int id, LocalDate date, LocalTime time, UserModel user, float discount) {
        this.id = id;
        this.user = user;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return this.date.format(DateTimeFormatter.ISO_LOCAL_DATE) 
                + " " 
                + this.time.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
    
    
}
