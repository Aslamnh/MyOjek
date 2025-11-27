package myojektest;


import java.sql.*;
import java.util.ArrayList;

public class OrderDAO {

    public OrderDAO() {
        createTable();
    }

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
                FOREIGN KEY(passenger_id) REFERENCES passenger(passenger_id),
                FOREIGN KEY(driver_id) REFERENCES driver(driver_id)
            );
            """;

        try (Connection c = Database.getConnection();
             Statement st = c.createStatement()) {

            st.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insert(Order o) {
        String sql = "INSERT INTO ride_order(tanggal,alamat_jemput,alamat_antar,passenger_id,driver_id,biaya,jarak_km) VALUES(?,?,?,?,?,?,?)";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, o.tanggal);
            ps.setString(2, o.alamatJemput);
            ps.setString(3, o.alamatTujuan);
            ps.setInt(4, o.passenger_id);
            ps.setInt(5, o.driver_id);
            ps.setFloat(6, o.biaya);
            ps.setFloat(7, o.jarak_km);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Order> getAll() {
        ArrayList<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM ride_order";

        try (Connection c = Database.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Order o = new Order();
                o.order_id = rs.getInt("order_id");
                o.tanggal = rs.getString("tanggal");
                o.alamatJemput = rs.getString("alamat_jemput");
                o.alamatTujuan = rs.getString("alamat_antar");
                o.passenger_id = rs.getInt("passenger_id");
                o.driver_id = rs.getInt("driver_id");
                o.biaya = rs.getFloat("biaya");
                o.jarak_km = rs.getFloat("jarak_km");

                list.add(o);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void  update(Order o) {
        String sql = "UPDATE ride_order SET tanggal=?,alamat_jemput=?,alamat_antar=?,passenger_id=?,driver_id=?,biaya=?,jarak_km=? WHERE order_id=?";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, o.tanggal);
            ps.setString(2, o.alamatJemput);
            ps.setString(3, o.alamatTujuan);
            ps.setInt(4, o.passenger_id);
            ps.setInt(5, o.driver_id);
            ps.setFloat(6, o.biaya);
            ps.setFloat(7, o.jarak_km);
            ps.setInt(8, o.order_id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void  delete(int id) {
        String sql = "DELETE FROM ride_order WHERE order_id=?";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
