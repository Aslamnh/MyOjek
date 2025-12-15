/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myojektest.model;

/**
 *
 * @author rafael
 */

import java.sql.*;
import java.util.ArrayList;

public class DriverDAO {

    public DriverDAO() {
        createTable();
    }

    private void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS driver (
                driver_id INTEGER PRIMARY KEY AUTOINCREMENT,
                no_hp TEXT,
                nama TEXT,
                password TEXT,
                     email TEXT,
                simNumber TEXT,
                stnkNumber TEXT,
                plateNumber TEXT
            );
            """;

        try (Connection c = Database.getConnection();
             Statement st = c.createStatement()) {

            st.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(Driver d) {
        String sql = "INSERT INTO driver(no_hp,nama,password,email,simNumber,stnkNumber,plateNumber) VALUES(?,?,?,?,?,?,?)";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, d.no_hp);
            ps.setString(2, d.nama);
            ps.setString(3, d.password);
            ps.setString(4, d.email);
            ps.setString(5, d.simNumber);
            ps.setString(6, d.stnkNumber);
            ps.setString(7, d.plateNumber);
            System.out.println(ps.toString());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int getIDFromHP(String nohp) {
        String sql = "SELECT driver_id FROM driver WHERE no_hp = ?";
        int passengerId = -1;

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, nohp);
            System.out.println(ps.toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    passengerId = rs.getInt("driver_id");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return passengerId;
    }

    public ArrayList<Driver> getAll() {
        ArrayList<Driver> list = new ArrayList<>();
        String sql = "SELECT * FROM driver";

        try (Connection c = Database.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Driver d = new Driver();
                d.driver_id = rs.getInt("driver_id");
                d.no_hp = rs.getString("no_hp");
                d.nama = rs.getString("nama");
                d.password = rs.getString("password");
                d.email= rs.getString("Email");
                d.simNumber = rs.getString("simNumber");
                d.stnkNumber = rs.getString("stnkNumber");
                d.plateNumber = rs.getString("plateNumber");
                list.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

        public  ArrayList<Driver> getFiltered(Integer driver_id, String noHp, String email, String nama) {
    ArrayList<Driver> list = new ArrayList<>();

    String sql = "SELECT * FROM driver WHERE "
            + "driver_id LIKE ? AND "
            + "no_hp LIKE ? AND "
            + "email LIKE ? AND "
            + "nama LIKE ?";

    try (Connection c = Database.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {

        // passenger_id (integer harus diubah ke string agar bisa LIKE)
        if (driver_id == null) {
            ps.setString(1, "%");
        } else {
            ps.setString(1, driver_id.toString());
        }

        // no_hp
        if (noHp == null) {
            ps.setString(2, "%");
        } else {
            ps.setString(2, "%" + noHp + "%");
        }

        // email
        if (email == null) {
            ps.setString(3, "%");
        } else {
            ps.setString(3, "%" + email + "%");
        }

        // nama
        if (nama == null) {
            ps.setString(4, "%");
        } else {
            ps.setString(4, "%" + nama + "%");
        }

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Driver d = new Driver();
            d.driver_id = rs.getInt("driver_id");
            d.no_hp = rs.getString("no_hp");
            d.email = rs.getString("email");
            d.nama = rs.getString("nama");
            d.password = rs.getString("password");
            list.add(d);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

    
    public boolean isPhoneNumberExists(String no_hp) {
        String sql = "SELECT COUNT(*) FROM driver WHERE no_hp=?";
    
        try (Connection c = Database.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
        
            ps.setString(1, no_hp);
        
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Jika count > 0, maka Nomor HP sudah ada
                }
            }
        } catch (Exception e) {
         e.printStackTrace();
        }
        return false;
    }
    
    public Driver authenticate(String no_hp, String password) {
        String sql = "SELECT * FROM driver WHERE no_hp=? AND password=?";
        Driver d = null;

        try (Connection c = Database.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, no_hp);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    d = new Driver();
                    d.driver_id = rs.getInt("driver_id");
                    d.no_hp = rs.getString("no_hp");
                    d.email = rs.getString("email");
                    d.nama = rs.getString("nama");
                    d.password = rs.getString("password"); 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }
    
    
    public void update(Driver d) {
        String sql = "UPDATE driver SET no_hp=?,nama=?,password=?,email=?,simNumber=?,stnkNumber=?,plateNumber=? WHERE driver_id=?";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, d.no_hp);
            ps.setString(2, d.nama);
            ps.setString(3, d.password);
            ps.setString(4, d.email);
            ps.setString(5, d.simNumber);
            ps.setString(6, d.stnkNumber);
            ps.setString(7, d.plateNumber);
            ps.setInt(8, d.driver_id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM driver WHERE driver_id=?";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public  String findNameFromID(int driverId) {
        String sql = "SELECT nama FROM driver WHERE driver_id=?";
        String nama = null;

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, driverId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nama = rs.getString("nama");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nama;
    }
}

