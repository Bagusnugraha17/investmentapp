package controller;

import model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            if (s.getKode().equalsIgnoreCase(kode) && portSaham.stream().noneMatch(it -> it.getSaham().getKode().equalsIgnoreCase(kode))) {
                portSaham.add(new PortfolioItem(s, jumlah));
                System.out.println(">> Membeli " + jumlah + " lembar " + kode);
                return;
            }
            if (s.getKode().equalsIgnoreCase(kode)) {
                portSaham.stream().filter(it -> it.getSaham().getKode().equalsIgnoreCase(kode))
                        .findFirst().ifPresent(it -> it.tambahLembar(jumlah));
                System.out.println(">> Ditambahkan " + jumlah + " lembar " + kode);
                return;
            }
        }
        System.out.println(">> Saham tidak ditemukan.");
    }

    public void jualSaham(String kode, int jumlah) {
        for (int i = 0; i < portSaham.size(); i++) {
            PortfolioItem it = portSaham.get(i);
            if (it.getSaham().getKode().equalsIgnoreCase(kode)) {
                if (it.getJumlahLembar() >= jumlah) {
                    it.kurangiLembar(jumlah);
                    System.out.println(">> Menjual " + jumlah + " lembar " + kode);

                    // Hapus dari portofolio jika jumlahnya 0
                    if (it.getJumlahLembar() == 0) {
                        portSaham.remove(i);
                        System.out.println(">> Saham " + kode + " telah dihapus dari portofolio.");
                    }
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
            if (sbn.getNama().equalsIgnoreCase(nama) && sbn.getKuotaNasional() >= nominal) {
                sbn.setKuotaNasional(sbn.getKuotaNasional() - nominal);
                portSBN.add(new SBNPortfolioItem(sbn, nominal));
                System.out.println(">> Membeli SBN " + nama + " nominal " + nominal);
                return;
            }
            if (sbn.getNama().equalsIgnoreCase(nama)) {
                System.out.println(">> Gagal membeli: Kuota nasional tersisa hanya " + sbn.getKuotaNasional());
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
        System.out.println();
        System.out.println("=== Portofolio SBN ===");
        if (portSBN.isEmpty()) System.out.println("— Kosong —");
        else portSBN.forEach(i -> System.out.println(i));
    }

    public List<Saham> lihatSahamBisaDibeli() {
        return daftarSaham.stream()
                .filter(s -> portSaham.stream()
                        .noneMatch(item -> item.getSaham().getKode().equalsIgnoreCase(s.getKode())))
                .collect(Collectors.toList());
    }

    public List<Saham> lihatSahamBisaDijual() {
        return portSaham.stream()
                .map(PortfolioItem::getSaham)
                .collect(Collectors.toList());
    }

    public List<SBN> lihatSNBBisaDibeli() {
        return daftarSBN.stream()
                .filter(sbn -> sbn.getKuotaNasional() > 0)
                .collect(Collectors.toList());
    }

    public List<SBN> lihatSNBDijual() {
        return portSBN.stream()
                .map(SBNPortfolioItem::getSbn)
                .collect(Collectors.toList());
    }
}
