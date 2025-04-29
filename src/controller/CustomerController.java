package controller;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    private List<Saham> daftarSaham;
    private List<SBN> daftarSBN;
    private List<PortfolioItem> portSaham = new ArrayList<>();
    private List<SBNPortfolioItem> portSBN = new ArrayList<>();

    public CustomerController(List<Saham> daftarSaham, List<SBN> daftarSBN) {
        this.daftarSaham = daftarSaham;
        this.daftarSBN = daftarSBN;
    }

    public void beliSaham(String kode, int jumlah) {
        for (Saham s : daftarSaham) {
            if (s.getKode().equalsIgnoreCase(kode)) {
                for (PortfolioItem it : portSaham) {
                    if (it.getSaham().getKode().equalsIgnoreCase(kode)) {
                        it.tambahLembar(jumlah);
                        System.out.println(">> Ditambahkan " + jumlah + " lembar " + kode);
                        return;
                    }
                }
                portSaham.add(new PortfolioItem(s, jumlah));
                System.out.println(">> Membeli " + jumlah + " lembar " + kode);
                return;
            }
        }
        System.out.println(">> Saham tidak ditemukan.");
    }

    public void jualSaham(String kode, int jumlah) {
        for (PortfolioItem it : portSaham) {
            if (it.getSaham().getKode().equalsIgnoreCase(kode)) {
                if (it.getJumlahLembar() >= jumlah) {
                    it.kurangiLembar(jumlah);
                    System.out.println(">> Menjual " + jumlah + " lembar " + kode);
                } else {
                    System.out.println(">> Gagal: lembar tidak cukup.");
                }
                return;
            }
        }
        System.out.println(">> Anda tidak memiliki saham " + kode);
    }

    public void beliSBN(String nama, double nominal) {
        for (SBN sbn : daftarSBN) {
            if (sbn.getNama().equalsIgnoreCase(nama)) {
                portSBN.add(new SBNPortfolioItem(sbn, nominal));
                System.out.println(">> Membeli SBN " + nama + " nominal " + nominal);
                return;
            }
        }
        System.out.println(">> SBN tidak ditemukan.");
    }

    public void simulasiSBN(String nama, double nominal) {
        for (SBN sbn : daftarSBN) {
            if (sbn.getNama().equalsIgnoreCase(nama)) {
                double kuponPerBulan = nominal * sbn.getBunga() / 12 / 100;
                System.out.println(">> Simulasi kupon/bln: " + kuponPerBulan);
                return;
            }
        }
        System.out.println(">> SBN tidak ditemukan.");
    }

    public void lihatPortofolio() {
        System.out.println("=== Portofolio Saham ===");
        if (portSaham.isEmpty()) System.out.println("— Kosong —");
        else portSaham.forEach(i -> System.out.println(i));
        System.out.println("=== Portofolio SBN ===");
        if (portSBN.isEmpty()) System.out.println("— Kosong —");
        else portSBN.forEach(i -> System.out.println(i));
    }

    // ===== Tambahan method untuk MainMenu =====

    public List<Saham> lihatSahamBisaDibeli() {
        return daftarSaham;
    }

    public List<Saham> lihatSahamBisaDijual() {
        List<Saham> sahamBisaJual = new ArrayList<>();
        for (PortfolioItem it : portSaham) {
            sahamBisaJual.add(it.getSaham());
        }
        return sahamBisaJual;
    }

    public List<SBN> lihatSNBBisaDibeli() {
        return daftarSBN;
    }

    public List<SBN> lihatSNBDijual() {
        List<SBN> sbnDimiliki = new ArrayList<>();
        for (SBNPortfolioItem it : portSBN) {
            sbnDimiliki.add(it.getSbn());
        }
        return sbnDimiliki;
    }
}
