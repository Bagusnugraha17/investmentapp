package controller;

import model.*;
import java.util.ArrayList;
import java.util.List;

public class AdminController {
    private List<Saham> daftarSaham = new ArrayList<>();
    private List<SBN> daftarSBN = new ArrayList<>();

    public AdminController(List<Saham> daftarSaham, List<SBN> daftarSBN) {
        this.daftarSaham = daftarSaham;
        this.daftarSBN = daftarSBN;
    }

    public void tambahSaham(Saham saham) {
        daftarSaham.add(saham);
    }

    public void ubahHargaSaham(String kodeSaham, double hargaBaru) {
        for (Saham saham : daftarSaham) {
            if (saham.getKode().equals(kodeSaham)) {
                saham.setHarga(hargaBaru);
                break;
            }
        }
    }

    public void tambahSBN(SBN sbn) {
        daftarSBN.add(sbn);
    }
}
