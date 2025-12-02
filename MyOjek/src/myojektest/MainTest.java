package myojektest;

import myojektest.model.Passenger;
import myojektest.model.OrderDAO;
import myojektest.model.Order;
import myojektest.model.PassengerDAO;
import myojektest.model.DriverDAO;
import myojektest.model.Driver;


public class MainTest {
    public static void main(String[] args) {

        PassengerDAO passDao = new PassengerDAO();
        DriverDAO driverDao = new DriverDAO();
        OrderDAO orderDao = new OrderDAO();

        Passenger p = new Passenger();
        p.no_hp = "08123";
        p.email = "mail@test.com";
        p.nama = "Budi";
        p.password = "hashed";
        passDao.insert(p);

        Driver d = new Driver();
        d.no_hp = "08999";
        d.nama = "Agus";
        d.password = "hash2";
        d.simNumber = "SIM12";
        d.stnkNumber = "STNK45";
        d.plateNumber = "N1234AB";
        driverDao.insert(d);

        Order o = new Order();
        o.tanggal = "2025-01-01";
        o.setAlamatJemput("Jalan Mawar");
        o.setAlamatTujuan("Jalan Melati");
        o.passenger_id = 1;
        o.driver_id = 1;
        o.biaya = 15000;
        o.jarak_km = 3.4f;     // ← jarak tempuh
        orderDao.insert(o);


        System.out.println("Done");
    }
}
