/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.bean;

import java.util.Vector;

/**
 *
 * @author phunglv
 */
public class BeanCart {
private Vector cart;

    public BeanCart() {
        cart = new Vector();
    }
    public void addToCart(String item){
        cart.addElement(item);
    }

    public void deleteItem(String item){
        cart.remove(item);
    }
    /**
     * @return the cart
     */
    public Vector getCart() {
        return cart;
    }
}
