/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myojektest;

/**
 *
 * @author rafae
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
}
