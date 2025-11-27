/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myojektest;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author rafae
 */
public class OrderController {

    private static ArrayList<Order> orders = new ArrayList<Order>();

    static int harga = 0;
    static int admin = 2_000;
    static int total = 0;

    static void setHarga(int p) {
        harga = p;
        total = p + admin;
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
        order.accepted = true;
    }

    public static void finishOrder() {

    }

    public static void buatOrder(Order order) {
        OrderQueue.newOrder(order);
    }

}
