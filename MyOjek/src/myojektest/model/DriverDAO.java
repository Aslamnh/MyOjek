/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myojektest.model;

/**
 *
 * @author rafae
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
        String sql = "INSERT INTO driver(no_hp,nama,password,simNumber,stnkNumber,plateNumber) VALUES(?,?,?,?,?,?)";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, d.no_hp);
            ps.setString(2, d.nama);
            ps.setString(3, d.password);
            ps.setString(4, d.simNumber);
            ps.setString(5, d.stnkNumber);
            ps.setString(6, d.plateNumber);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
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

    
    
    
    public void update(Driver d) {
        String sql = "UPDATE driver SET no_hp=?,nama=?,password=?,simNumber=?,stnkNumber=?,plateNumber=? WHERE driver_id=?";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, d.no_hp);
            ps.setString(2, d.nama);
            ps.setString(3, d.password);
            ps.setString(4, d.simNumber);
            ps.setString(5, d.stnkNumber);
            ps.setString(6, d.plateNumber);
            ps.setInt(7, d.driver_id);
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
    
    public static String findNameFromID(int passengerId) {
        String sql = "SELECT nama FROM passenger WHERE passenger_id=?";
        String nama = null;

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, passengerId);

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

