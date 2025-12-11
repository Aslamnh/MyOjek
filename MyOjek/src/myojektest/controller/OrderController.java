/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myojektest.controller;

import myojektest.model.Order;
import myojektest.model.OrderDAO;
import myojektest.model.OrderQueue;

import java.util.ArrayList;

/**
 *
 * @author rafae
 */
public class OrderController {

    private static ArrayList<Order> orders = new ArrayList<Order>();

    static int harga = 0;
    private static final int ADMIN_FEE = 2000;
    private static final int TARIF_PER_KM = 5000;
    static int total = 0;

    public int calculateTotalBiaya(double jarakKm) {
        int biayaDasar = (int) (jarakKm * TARIF_PER_KM);
        return biayaDasar + ADMIN_FEE;
    }
    
    public int getAdminFee() {
        return ADMIN_FEE;
    }

    public boolean submitNewOrder(Order order) {
        return OrderDAO.insert(order);
    }
    
    static void setHarga(int p) {
        harga = p;
        total = p + ADMIN_FEE;
    }

    static boolean newOrder(Order order) {
        boolean dbSuccess = OrderDAO.insert(order);

        if (dbSuccess) {
            try {
                orders.add(0, order);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    public static Order[] getOrders() {
        return (Order[]) orders.toArray();
    }

    public static void acceptOrder(Order order) {
        order.setAccepted(true);
    }

    public static void finishOrder() {

    }

    public static void buatOrder(Order order) {
        OrderQueue.newOrder(order);
    }
    
    // File: OrderController.java (Melakukan perhitungan dan update View)

}
