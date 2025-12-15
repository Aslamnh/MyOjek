/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myojektest.controller;

import myojektest.view.PassengerHistoryView;
import myojektest.view.DriverHistoryView;
import myojektest.view.HistoryItemPanel;
import myojektest.view.MainPassengerView;
import myojektest.view.MainDriverView;

import myojektest.model.OrderDAO;
import myojektest.model.Order;

import javax.swing.JPanel;
import java.util.ArrayList;
import myojektest.model.DriverDAO;
import myojektest.model.PassengerDAO;

/**
 *
 * @author aslam
 */
public class HistoryController {
    private OrderDAO orderDAO;
    private PassengerHistoryView viewPassengerHistory;
    private DriverHistoryView viewDriverHistory;
    private String noHp;
    private DriverDAO driverDAO;
    private PassengerDAO passengerDAO;
    
    public HistoryController(PassengerHistoryView view, OrderDAO orderDAO, String noHp,DriverDAO driverDAO,PassengerDAO passengerDAO) {
        this.viewPassengerHistory = view;
        this.viewPassengerHistory.addBackListener(e -> handleBackPassenger());
        this.noHp = noHp;
        this.passengerDAO= passengerDAO;
        this.orderDAO = orderDAO;

        int passengerId = passengerDAO.getIDFromHP(noHp);
        loadHistoryForPassenger(passengerId, view);
    }
    
    public HistoryController(DriverHistoryView view, OrderDAO orderDAO, String noHp,DriverDAO driverDAO,PassengerDAO passengerDAO) {
        this.viewDriverHistory = view;
        this.viewDriverHistory.addBackListener(e -> handleBackDriver());
        this.passengerDAO=passengerDAO;
        this.driverDAO=driverDAO;
        this.orderDAO = orderDAO;
        this.noHp = noHp;
        int driverId = driverDAO.getIDFromHP(noHp);
        loadHistoryForDriver(driverId, view);
    }

    public void loadHistoryForPassenger(int passengerId, PassengerHistoryView view) {
        System.out.println("METHOD JALAN");
        float totalPembayaran = 0;
        ArrayList<Order> orderList = orderDAO.findPassenger(passengerId);
        //ArrayList<Order> orderList = orderDAO.findPassenger(Integer.parseInt(noHp));
        if (orderList.isEmpty()) System.out.println("LIST KOSONG");
        else System.out.println("UKURAN LIST: " + orderList.size());
        
        JPanel container = view.getHistoryList();
        container.removeAll();

        for (Order order : orderList) {
            HistoryItemPanel item = new HistoryItemPanel();
            item.setHistoryItemDriver(order);
            container.add(item);
            if (order.getDriverName() != null) {
                totalPembayaran += order.getBiaya();
            }
        }

        view.setLabelPembayaran(totalPembayaran);
        container.revalidate();
        container.repaint(); 
    } 
    
    public void loadHistoryForDriver(int driverId, DriverHistoryView view) {
        System.out.println("METHOD JALAN");
        float totalPendapatan = 0;
        ArrayList<Order> orderList = orderDAO.findDriver(driverId);
        if (orderList.isEmpty()) System.out.println("LIST KOSONG");
        else System.out.println("UKURAN LIST: " + orderList.size());
        
        JPanel container = view.getHistoryList();
        container.removeAll();

        for (Order order : orderList) {
            HistoryItemPanel item = new HistoryItemPanel();
            item.setHistoryItemPassenger(order);
            container.add(item);
            totalPendapatan += order.getBiaya();
        }

        view.setLabelPendapatan(totalPendapatan);
        container.revalidate();
        container.repaint(); 
    }   
    
    public void handleBackPassenger() {
        viewPassengerHistory.dispose();
        new MainPassengerView(this.orderDAO, passengerDAO,this.noHp,driverDAO).setVisible(true);
    }
    
    public void handleBackDriver() {
        viewDriverHistory.dispose();
        new MainDriverView(this.orderDAO, this.noHp,driverDAO,passengerDAO).setVisible(true);
    }
}
