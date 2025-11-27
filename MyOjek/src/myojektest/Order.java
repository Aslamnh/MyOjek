/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myojektest;

/**
 *
 * @author rafae
 */
public class Order {
  String tujuan;
  String jemput;
  double harga;
  String passangerName;
  OrderFormView guiPassanger;
  String driverName;
  boolean accepted;
  boolean finisihed;
 
  
 
  String DriverName;
  //var guiDriver;
  
  


  public Order(String tujuan,String jemput,double harga,String passangerName,OrderFormView guiPassanger ){
     
    this.tujuan=tujuan;
    this.harga=harga;
    this.jemput=jemput;
    this.passangerName=passangerName;
    this.guiPassanger=guiPassanger;
    
  }
  
  
  
}
