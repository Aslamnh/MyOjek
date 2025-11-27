/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myojektest;

import java.util.ArrayList;

/**
 *
 * @author rafae
 */
public class OrderQueue {
        private static ArrayList<Order> orders= new ArrayList<Order>();
    
    static void newOrder(Order order){
        orders.addFirst(order);
        OrderDAO.insert(order);
        javax.swing.JOptionPane.showMessageDialog(order.guiPassanger, "Pemesanan Order Berhasil, Menunggu Driver");
    }


    public static Order[] getOrders(){
        return (Order[]) orders.toArray();
    }
    
    
    
    public static void acceptOrder(Order order){
       order.accepted=true;
       
    }
    
    
    
    public static void finishOrder(){
        
    }
    
}
