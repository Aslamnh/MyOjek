package myojektest;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAO {

    public OrderDAO() {
        createTable();
    }

<<<<<<< HEAD
=======
    // Membuat tabel
>>>>>>> cb0b79ff9598008fafde8fe49ce9c58224b11344
    private static void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS ride_order (
                order_id INTEGER PRIMARY KEY AUTOINCREMENT,
                tanggal TEXT,
                alamat_jemput TEXT,
                alamat_antar TEXT,
                passenger_id INTEGER,
                driver_id INTEGER,
                biaya REAL,
                jarak_km REAL,
                accepted INTEGER DEFAULT 0,
                finished INTEGER DEFAULT 0,
                FOREIGN KEY(passenger_id) REFERENCES passenger(passenger_id),
                FOREIGN KEY(driver_id) REFERENCES driver(driver_id)
            );
        """;

<<<<<<< HEAD
        try (Connection c = Database.getConnection(); Statement st = c.createStatement()) {

=======
        try (Connection c = Database.getConnection();
             Statement st = c.createStatement()) {
>>>>>>> cb0b79ff9598008fafde8fe49ce9c58224b11344
            st.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
    public static boolean insert(Order o) {
        createTable();
        String sql = "INSERT INTO ride_order(tanggal,alamat_jemput,alamat_antar,passenger_id,driver_id,biaya,jarak_km) VALUES(?,?,?,?,?,?,?)";
=======
    // Insert
    public static void insert(Order o) {
        String sql = "INSERT INTO ride_order(tanggal,alamat_jemput,alamat_antar,passenger_id,driver_id,biaya,jarak_km,accepted,finished) VALUES(?,?,?,?,?,?,?,?,?)";
>>>>>>> cb0b79ff9598008fafde8fe49ce9c58224b11344

        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, o.tanggal);
            ps.setString(2, o.alamatJemput);
            ps.setString(3, o.alamatTujuan);
            ps.setInt(4, o.passenger_id);
            ps.setInt(5, o.driver_id);
            ps.setFloat(6, o.biaya);
            ps.setFloat(7, o.jarak_km);
            ps.setInt(8, o.accepted ? 1 : 0);
            ps.setInt(9, o.finisihed ? 1 : 0);

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false; 
        }
    }

    // Get all
    public static ArrayList<Order> getAll() {
        ArrayList<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM ride_order";

        try (Connection c = Database.getConnection(); Statement st = c.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(extractOrder(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Update
    public static void update(Order o) {
        String sql = """
            UPDATE ride_order SET
                tanggal=?,
                alamat_jemput=?,
                alamat_antar=?,
                passenger_id=?,
                driver_id=?,
                biaya=?,
                jarak_km=?,
                accepted=?,
                finished=?
            WHERE order_id=?
        """;

        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, o.tanggal);
            ps.setString(2, o.alamatJemput);
            ps.setString(3, o.alamatTujuan);
            ps.setInt(4, o.passenger_id);
            ps.setInt(5, o.driver_id);
            ps.setFloat(6, o.biaya);
            ps.setFloat(7, o.jarak_km);
            ps.setInt(8, o.accepted ? 1 : 0);
            ps.setInt(9, o.finisihed ? 1 : 0);
            ps.setInt(10, o.order_id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete
    public static void delete(int id) {
        String sql = "DELETE FROM ride_order WHERE order_id=?";

        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Search fleksibel
    public static ArrayList<Order> search(
            String tanggal,
            String alamatJemput,
            String alamatAntar,
            Integer passengerId,
            Integer driverId,
            Boolean accepted,
            Boolean finished
    ) {
        ArrayList<Order> list = new ArrayList<>();

        StringBuilder sb = new StringBuilder(
                "SELECT * FROM ride_order WHERE tanggal LIKE ? AND alamat_jemput LIKE ? AND alamat_antar LIKE ? "
        );

        if (passengerId != null) sb.append("AND passenger_id=? ");
        if (driverId != null) sb.append("AND driver_id=? ");
        if (accepted != null) sb.append("AND accepted=? ");
        if (finished != null) sb.append("AND finished=? ");

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sb.toString())) {

            int idx = 1;

            ps.setString(idx++, tanggal == null ? "%" : tanggal);
            ps.setString(idx++, alamatJemput == null ? "%" : "%" + alamatJemput + "%");
            ps.setString(idx++, alamatAntar == null ? "%" : "%" + alamatAntar + "%");

            if (passengerId != null) ps.setInt(idx++, passengerId);
            if (driverId != null) ps.setInt(idx++, driverId);
            if (accepted != null) ps.setInt(idx++, accepted ? 1 : 0);
            if (finished != null) ps.setInt(idx++, finished ? 1 : 0);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(extractOrder(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    // Helper extract dari ResultSet
    private static Order extractOrder(ResultSet rs) throws Exception {
        Order o = new Order();

        o.order_id = rs.getInt("order_id");
        o.tanggal = rs.getString("tanggal");
        o.alamatJemput = rs.getString("alamat_jemput");
        o.alamatTujuan = rs.getString("alamat_antar");
        o.passenger_id = rs.getInt("passenger_id");
        o.driver_id = rs.getInt("driver_id");
        o.biaya = rs.getFloat("biaya");
        o.jarak_km = rs.getFloat("jarak_km");
        o.accepted = rs.getInt("accepted") == 1;
        o.finisihed = rs.getInt("finished") == 1;

        return o;
    }
}
