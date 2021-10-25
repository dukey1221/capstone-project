/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.storemanager;

import com.lentrix.storemanager.models.UserModel;
import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author lentrix
 */
public class Helper {
    public static UserModel currentUser;
    
    public static void error(String message, Component parent) {
        JOptionPane.showMessageDialog(parent, message, "Error!", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void info(String message, Component parent) {
        JOptionPane.showMessageDialog(parent, message, "Error!", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static String removeComma(String str) {
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if(c!=',') builder.append(c);
        }
        return builder.toString();
    }
    
    public static String expander(String a, String b, int length, char c) {
        int dots = length - (a.length()+b.length());
        StringBuffer dotStr = new StringBuffer();
        for(int i=0; i<dots; i++) {
            dotStr.append(c);
        }
        return a + dotStr + b;
    }
    
    public static String centerString(String str, int length) {
        int padding = (length-str.length())/2;
        StringBuffer paddingStr = new StringBuffer();
        for(int i=0; i<padding; i++){
            paddingStr.append(" ");
        }
        String pStr = paddingStr.toString();
        
        return pStr+str+pStr;
    }
    
    public static String strMax(String str, int max) {
        return str.length() > max ? str.substring(0, max-1) : str;
    }
    
    public static String strPad(String str, int length) {
        StringBuffer dots = new StringBuffer();
        int n = length-str.length();
        for(int i=0; i<n; i++) 
            dots.append(".");
        return dots.toString() + str;
    }
}
