package model;

import java.util.List;

public class Admin extends User {

    public Admin(String username, String password) {
        super(username, password);
    }

    public void tambahSaham(List<Saham> daftarSaham, Saham saham) {
        daftarSaham.add(saham);
    }

    public void ubahHargaSaham(Saham saham, double hargaBaru) {
        saham.setHarga(hargaBaru);
    }

    public void tambahSBN(List<SBN> daftarSBN, SBN sbn) {
        daftarSBN.add(sbn);
    }
}
