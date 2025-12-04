/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myojektest.controller;

import myojektest.view.FormOrderView;
import myojektest.model.Order;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import myojektest.model.PassengerDAO;

/**
 *
 * @author aslam
 */

public class FormOrderController {
    
    private FormOrderView orderView;
    private Order orderModel;
    private OrderController orderController;
    
    
   
    
    

    public FormOrderController(FormOrderView view, Order model, OrderController controller) {
        this.orderView = view;
        this.orderModel = model;
        this.orderController = controller;

        this.orderView.addPesanListener(new PesanListener());
        //this.orderView.addJarakChangeListener(new JarakChangeListener());
        this.orderView.addJarakDocumentListener(new JarakDocumentListener());
        
        initializeView(); 
        
        try {
            String defaultJarak = orderView.getJarakInput(); 
            if (!defaultJarak.isEmpty()) {
                calculateCost(Float.parseFloat(defaultJarak));
            }
        } catch (NumberFormatException e) {
            
        }
    }
    
    private void initializeView() {
        orderView.setAdminLabelText("Rp. " + orderController.getAdminFee());
    }
    
    private void calculateCost(float jarak) {
        int total = orderController.calculateTotalBiaya(jarak);
        
        int biayaDasar = total - orderController.getAdminFee(); 
        
        orderModel.setJarak_km(jarak);
        orderModel.setBiaya(total);
        
        orderView.setBiayaValText("Rp. " + biayaDasar);
        orderView.setTotalValText("Rp. " + total);
    }
    
//    private class JarakChangeListener implements ActionListener {
//        @Override
//        public void actionPerformed(java.awt.event.ActionEvent evt) {
//            try {
//                String jarakStr = orderView.getJarakInput(); 
//                float jarak = Float.parseFloat(jarakStr);
//                
//                calculateCost(jarak);
//            } catch (NumberFormatException e) {
//                orderView.showError("Format Jarak harus angka!");
//            }
//        }
//    }
    
    private class JarakDocumentListener implements javax.swing.event.DocumentListener {
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            performCalculation();
        }
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            performCalculation();
        }
        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            performCalculation();
        }

        private void performCalculation() {
        try {
            String jarakStr = orderView.getJarakInput(); 
            float jarak = jarakStr.isEmpty() ? 0.0f : Float.parseFloat(jarakStr);
            calculateCost(jarak); 
        } catch (NumberFormatException ex) {
            orderView.showError("Format Jarak harus angka!"); 
            orderView.setBiayaValText("Rp. 0");
            orderView.setTotalValText("Rp. " + orderController.getAdminFee());
        }
    }
}
    
    
    private class PesanListener implements ActionListener {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {

            String asal = orderView.getAlamatDari();
            String tujuan = orderView.getAlamatTujuan();
            String jarakStr = orderView.getJarakInput();

            try {
                if (asal.isEmpty() || tujuan.isEmpty() || jarakStr.isEmpty()) {
                    orderView.showWarning("Harap isi semua data!");
                    return;
                }

                float jarak = Float.parseFloat(jarakStr);

                int totalBiaya = orderController.calculateTotalBiaya(jarak);

                orderModel.setAlamatJemput(asal);
                orderModel.setAlamatTujuan(tujuan);
                orderModel.setJarak_km(jarak);
                orderModel.setTanggal(LocalDate.now().toString()); 
                orderModel.setBiaya(totalBiaya); 
                orderModel.setPassengerID( PassengerDAO.getFiltered(null,orderView.getNoHP() , null, null).getFirst().passenger_id);

                
                boolean sukses = orderController.submitNewOrder(orderModel);

                if (sukses) {
                    orderView.showSuccess("Order Berhasil Disubmit!");
                } else {
                    orderView.showError("Gagal menyimpan data ke Database.");
                }

            } catch (NumberFormatException e) {
                orderView.showError("Format Jarak harus angka!");
            } catch (Exception e) {
                orderView.showError("Terjadi kesalahan: " + e.getMessage());
            }
        }
    }
}
