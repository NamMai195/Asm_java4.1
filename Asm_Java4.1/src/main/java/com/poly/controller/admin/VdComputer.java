/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.poly.controller.admin;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */



import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * FPT Polytechnic - UDPM
 * Debugging Essentials - 23.11.2024
 * @author Admin
 */
public class VdComputer {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        try {
            
            InetAddress inetAddress = InetAddress.getLocalHost();
            
            System.out.println("I am : " + inetAddress.toString());
            
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}