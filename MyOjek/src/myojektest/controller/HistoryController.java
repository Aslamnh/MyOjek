/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myojektest.controller;

import myojektest.view.PassengerHistoryView;
import myojektest.view.DriverHistoryView;
import myojektest.model.OrderDAO;
import myojektest.model.Order;
import myojektest.view.HistoryItemPanel;
import myojektest.view.MainPassengerView;
import myojektest.view.MainDriverView;

import javax.swing.JPanel;
import java.util.ArrayList;

/**
 *
 * @author aslam
 */
public class HistoryController {
    private OrderDAO orderDAO;
    private PassengerHistoryView viewPassengerHistory;
    private DriverHistoryView viewDriverHistory;
    private String nohp;
    
    public HistoryController(PassengerHistoryView view, OrderDAO orderDAO, String nohp) {
        this.viewPassengerHistory = view;
        this.viewPassengerHistory.addBackListener(e -> handleBackPassenger());
        this.nohp = nohp;
        
        this.orderDAO = orderDAO;

        int passengerId = orderDAO.getIDFromHP(nohp);
        loadHistoryForPassenger(passengerId, view);
    }
    
    public HistoryController(DriverHistoryView view, OrderDAO orderDAO) {
        this.viewDriverHistory = view;
        this.viewDriverHistory.addBackListener(e -> handleBackDriver());
        
        this.orderDAO = orderDAO;

        int passengerId = orderDAO.getIDFromHP(nohp);
        loadHistoryForDriver(passengerId, view);
    }

    public void loadHistoryForPassenger(int passengerId, PassengerHistoryView view) {
        System.out.println("METHOD JALAN");
        //ArrayList<Order> orderList = orderDAO.findPassenger(passengerId); <- seharusnya ini tapi ga nemu
        ArrayList<Order> orderList = orderDAO.findPassenger(Integer.parseInt(nohp));
        if (orderList.isEmpty()) System.out.println("LIST KOSONG");
        else System.out.println("UKURAN LIST: " + orderList.size());
        
        JPanel container = view.getHistoryList();
        container.removeAll();

        for (Order order : orderList) {
            HistoryItemPanel item = new HistoryItemPanel();
            item.setHistoryItemDriver(order);
            container.add(item);
        }

        container.revalidate();
        container.repaint(); 
    } 
    
    public void loadHistoryForDriver(int driverId, DriverHistoryView view) {
        System.out.println("METHOD JALAN");
        ArrayList<Order> orderList = orderDAO.findDriver(driverId);
        if (orderList.isEmpty()) System.out.println("LIST KOSONG");
        else System.out.println("UKURAN LIST: " + orderList.size());
        
        JPanel container = view.getHistoryList();
        container.removeAll();

        for (Order order : orderList) {
            HistoryItemPanel item = new HistoryItemPanel();
            item.setHistoryItemPassenger(order);
            container.add(item);
        }

        container.revalidate();
        container.repaint(); 
    }   
    
    public void handleBackPassenger() {
        viewPassengerHistory.dispose();
        new MainPassengerView(this.orderDAO, this.nohp).setVisible(true);
    }
    
    public void handleBackDriver() {
        viewDriverHistory.dispose();
        new MainDriverView(this.orderDAO).setVisible(true);
    }
}
