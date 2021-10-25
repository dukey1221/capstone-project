/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.storemanager.models;

import java.time.LocalDateTime;

/**
 *
 * @author lentrix
 */
public class UserModel {
    private int id;
    private String fullname;
    private String username;
    private String role;
    private String password;
    private LocalDateTime lastLogin;

    public UserModel(int id, String fullname, String username, String password, String role, LocalDateTime lastLogin) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.role = role;
        this.lastLogin = lastLogin;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return this.fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
