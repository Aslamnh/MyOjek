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

public class PassengerDAO {

    public PassengerDAO() {
        createTable();
    }

    private void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS passenger (
                passenger_id INTEGER PRIMARY KEY AUTOINCREMENT,
                no_hp TEXT,
                email TEXT,
                nama TEXT,
                password TEXT
            );
            """;

        try (Connection c = Database.getConnection();
             Statement st = c.createStatement()) {

            st.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(Passenger p) {
        String sql = "INSERT INTO passenger(no_hp,email,nama,password) VALUES(?,?,?,?)";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, p.no_hp);
            ps.setString(2, p.email);
            ps.setString(3, p.nama);
            ps.setString(4, p.password);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getIDFromHP(String nohp) {
        String sql = "SELECT passenger_id FROM passenger WHERE no_hp = ?";
        int passengerId = -1;

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, nohp);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    passengerId = rs.getInt("passenger_id");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return passengerId;
    }

    public ArrayList<Passenger> getAll() {
        ArrayList<Passenger> list = new ArrayList<>();
        String sql = "SELECT * FROM passenger";

        try (Connection c = Database.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Passenger p = new Passenger();
                p.passenger_id = rs.getInt("passenger_id");
                p.no_hp = rs.getString("no_hp");
                p.email = rs.getString("email");
                p.nama = rs.getString("nama");
                p.password = rs.getString("password");
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    public  ArrayList<Passenger> getFiltered(Integer passengerId, String noHp, String email, String nama) {
    ArrayList<Passenger> list = new ArrayList<>();

    String sql = "SELECT * FROM passenger WHERE "
            + "passenger_id LIKE ? AND "
            + "no_hp LIKE ? AND "
            + "email LIKE ? AND "
            + "nama LIKE ?";

    try (Connection c = Database.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {

        // passenger_id (integer harus diubah ke string agar bisa LIKE)
        if (passengerId == null) {
            ps.setString(1, "%");
        } else {
            ps.setString(1, passengerId.toString());
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
            Passenger p = new Passenger();
            p.passenger_id = rs.getInt("passenger_id");
            p.no_hp = rs.getString("no_hp");
            p.email = rs.getString("email");
            p.nama = rs.getString("nama");
            p.password = rs.getString("password");
            list.add(p);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}


    public void update(Passenger p) {
        String sql = "UPDATE passenger SET no_hp=?,email=?,nama=?,password=? WHERE passenger_id=?";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, p.no_hp);
            ps.setString(2, p.email);
            ps.setString(3, p.nama);
            ps.setString(4, p.password);
            ps.setInt(5, p.passenger_id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM passenger WHERE passenger_id=?";

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
    
    public Passenger authenticate(String no_hp, String password) {
        String sql = "SELECT * FROM passenger WHERE no_hp=? AND password=?";
        Passenger p = null;

        try (Connection c = Database.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, no_hp);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p = new Passenger();
                    p.passenger_id = rs.getInt("passenger_id");
                    p.no_hp = rs.getString("no_hp");
                    p.email = rs.getString("email");
                    p.nama = rs.getString("nama");
                    p.password = rs.getString("password"); 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }
    
    
    
    public boolean isPhoneNumberExists(String no_hp) {
        String sql = "SELECT COUNT(*) FROM passenger WHERE no_hp=?";
    
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
}
