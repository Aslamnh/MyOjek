package myojektest.model;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class AuthService {

    private final PassengerDAO passengerDAO = new PassengerDAO();
    private final DriverDAO driverDAO = new DriverDAO();
    private final SecureRandom random = new SecureRandom();

    // -----------------------------
    // Generate random salt
    // -----------------------------
    private String generateSalt() {
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // -----------------------------
    // Hash password + salt
    // -----------------------------
    private String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(Base64.getDecoder().decode(salt));
            byte[] hashed = md.digest(password.getBytes());

            return Base64.getEncoder().encodeToString(hashed);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ========================================================
    //                 REGISTER PENUMPANG
    // ========================================================
    public boolean registerPassenger(String noHp, String email, String nama, String plainPassword) {

        // cek nomor sudah dipakai?
        for (Passenger p : passengerDAO.getAll()) {
            if (p.no_hp.equals(noHp)) {
                return false; // sudah ada
            }
        }

        String salt = generateSalt();
        String hash = hashPassword(plainPassword, salt);
        String stored = hash + ":" + salt;   // simpan gabungan

        Passenger p = new Passenger();
        p.no_hp = noHp;
        p.email = email;
        p.nama = nama;
        p.password = stored;

        passengerDAO.insert(p);
        return true;
    }

    // ========================================================
    //                 LOGIN PENUMPANG (by no HP)
    // ========================================================
    public Passenger loginPassenger(String noHp, String plainPassword) {
        for (Passenger p : passengerDAO.getAll()) {
            if (!p.no_hp.equals(noHp)) continue;

            if (p.password == null || !p.password.contains(":")) return null;

            String[] parts = p.password.split(":");
            String storedHash = parts[0];
            String salt = parts[1];

            String hashedInput = hashPassword(plainPassword, salt);

            if (hashedInput.equals(storedHash)) {
                return p;
            }
        }
        return null;
    }

    // ========================================================
    //                 DRIVER REGISTER
    // ========================================================
    public boolean registerDriver(String noHp, String nama, String plainPassword,
                                  String sim, String stnk, String plate) {

        for (Driver d : driverDAO.getAll()) {
            if (d.no_hp.equals(noHp)) {
                return false;
            }
        }

        String salt = generateSalt();
        String hash = hashPassword(plainPassword, salt);
        String stored = hash + ":" + salt;

        Driver d = new Driver();
        d.no_hp = noHp;
        d.nama = nama;
        d.password = stored;
        d.simNumber = sim;
        d.stnkNumber = stnk;
        d.plateNumber = plate;

        driverDAO.insert(d);
        return true;
    }

    // ========================================================
    //                 DRIVER LOGIN
    // ========================================================
    public Driver loginDriver(String noHp, String plainPassword) {

        for (Driver d : driverDAO.getAll()) {
            if (!d.no_hp.equals(noHp)) continue;

            String[] parts = d.password.split(":");
            String storedHash = parts[0];
            String salt = parts[1];

            String hashedInput = hashPassword(plainPassword, salt);

            if (hashedInput.equals(storedHash)) {
                return d;
            }
        }
        return null;
    }
}
