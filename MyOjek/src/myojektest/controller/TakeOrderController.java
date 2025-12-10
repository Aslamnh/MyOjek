/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myojektest.controller;

import java.sql.*;
import java.util.ArrayList;
import myojektest.model.Database;
import myojektest.model.Order;
import myojektest.model.OrderDAO;
import myojektest.model.OrderQueue;

/**
 *
 * @author Muhammad Dzaky
 */
public class TakeOrderController {
    
    public static ArrayList<Order> loadPesanan(){
        return OrderQueue.loadOrderan();
    }
    
    public static void ambilOrderan(int orderId, int driverId){
        ArrayList<Order> list = OrderDAO.getPendingOrders();
        Order order = null;
        
        for (Order o : list){
            if (o.order_id == orderId){
                order = o;
                break;
            }
        }
        
        if (order == null) return;
        
        OrderQueue.acceptOrder(order);
        OrderQueue.finishOrder(order);
        OrderDAO.updateFinished(orderId, driverId);
    }
}
