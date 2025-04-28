package controller;

import model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Saham> getSahamBisaDibeli(List<Saham> sahamDimiliki) {
        return daftarSaham.stream()
                .filter(s -> sahamDimiliki.stream()
                        .noneMatch(owned -> owned.getKode().equalsIgnoreCase(s.getKode())))
                .collect(Collectors.toList());
    }

    public List<SBN> getSBNBisaDibeli() {
        return daftarSBN.stream()
                .filter(sbn -> sbn.getKuotaNasional() > 0)
                .collect(Collectors.toList());
    }
}
