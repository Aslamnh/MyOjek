package myojektest;

public class AuthController {

    private final AuthService auth = new AuthService();

    public void registerPassenger(String hp, String email, String nama, String pass) {
        boolean ok = auth.registerPassenger(hp, email, nama, pass);
        if (ok) {
            System.out.println("Registrasi penumpang berhasil.");
        } else {
            System.out.println("Nomor sudah terdaftar.");
        }
    }

    public Passenger loginPassenger(String hp, String pass) {
        Passenger p = auth.loginPassenger(hp, pass);
        if (p == null) {
            System.out.println("Login gagal.");
        } else {
            System.out.println("Login berhasil. Selamat datang " + p.nama);
        }
        return p;
    }

    public Driver loginDriver(String hp, String pass) {
        Driver d = auth.loginDriver(hp, pass);
        if (d == null) {
            System.out.println("Login driver gagal.");
        } else {
            System.out.println("Driver login sukses " + d.nama);
        }
        return d;
    }
}
