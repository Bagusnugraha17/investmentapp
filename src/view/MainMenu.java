package view;

import controller.AdminController;
import controller.CustomerController;
import model.Saham;
import model.SBN;
import model.User;
import util.LoginUtil;

import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private Scanner sc = new Scanner(System.in);
    private List<Saham> daftarSaham = new java.util.ArrayList<>();
    private List<SBN> daftarSBN = new java.util.ArrayList<>();

    public void start() {
        while (true) {
            System.out.println("--- Selamat Datang ---");
            System.out.println("1. Login");
            System.out.println("0. Keluar");
            System.out.print("> ");
            String pilihan = sc.nextLine();

            if (pilihan.equals("1")) {
                System.out.print("Username: ");
                String u = sc.nextLine();
                System.out.print("Password: ");
                String p = sc.nextLine();

                User user = LoginUtil.authenticate(u, p);
                if (user == null) {
                    System.out.println("Login gagal.");
                    continue;
                }

                if (user instanceof model.Admin) {
                    AdminController ac = new AdminController(daftarSaham, daftarSBN);
                    menuAdmin(ac);
                } else {
                    CustomerController cc = new CustomerController(daftarSaham, daftarSBN);
                    menuCustomer(cc);
                }
            } else if (pilihan.equals("0")) {
                System.out.println("Terima kasih. Sampai jumpa!");
                System.exit(0);
            } else {
                System.out.println("Pilihan salah. Silakan pilih 1 atau 0.");
            }
        }
    }

    private void menuAdmin(AdminController ac) {
        while (true) {
            System.out.println("--- Admin Menu ---");
            System.out.println("1. Tambah Saham");
            System.out.println("2. Ubah Harga Saham");
            System.out.println("3. Tambah SBN");
            System.out.println("0. Logout");
            System.out.print("> ");
            String c = sc.nextLine();
            switch (c) {
                case "1":
                    tambahSaham(ac);
                    break;
                case "2":
                    ubahHargaSaham(ac);
                    break;
                case "3":
                    tambahSBN(ac);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Pilihan salah.");
            }
        }
    }

    private void tambahSaham(AdminController ac) {
        String k = promptNonEmpty("Kode: ");
        String n = promptNonEmpty("Nama: ");
        double h = promptPositiveDouble("Harga: ");
        ac.tambahSaham(new Saham(k, n, h));
    }

    private void ubahHargaSaham(AdminController ac) {
        String kc = promptNonEmpty("Kode: ");
        double hb = promptPositiveDouble("Harga baru: ");
        ac.ubahHargaSaham(kc, hb);
    }

    private void tambahSBN(AdminController ac) {
        String ns = promptNonEmpty("Nama SBN: ");
        double b = promptPositiveDouble("Bunga (%/th): ");
        int jw = promptPositiveInt("Jangka (tahun): ");
        String jt = promptNonEmpty("Tanggal Jatuh Tempo: ");
        double quota = promptPositiveDouble("Kuota Nasional: ");
        ac.tambahSBN(new SBN(ns, b, jw, jt, quota));
    }

    private void menuCustomer(CustomerController cc) {
        while (true) {
            System.out.println("--- Customer Menu ---");
            System.out.println("1. Beli Saham");
            System.out.println("2. Jual Saham");
            System.out.println("3. Beli SBN");
            System.out.println("4. Simulasi SBN");
            System.out.println("5. Lihat Portofolio");
            System.out.println("0. Logout");
            System.out.print("> ");
            String c = sc.nextLine();
            switch (c) {
                case "1":
                    beliSaham(cc);
                    break;
                case "2":
                    jualSaham(cc);
                    break;
                case "3":
                    beliSBN(cc);
                    break;
                case "4":
                    simulasiSBN(cc);
                    break;
                case "5":
                    cc.lihatPortofolio();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Pilihan salah.");
            }
        }
    }

    private void beliSaham(CustomerController cc) {
        List<Saham> bisaBeli = cc.lihatSahamBisaDibeli();
        System.out.println("-- Saham tersedia untuk dibeli --");
        bisaBeli.forEach(s -> System.out.println(s.getKode() + " | " + s.getNamaPerusahaan() + " | " + s.getHarga()));
        String ks = promptNonEmpty("Kode Saham: ");
        int jm = promptPositiveInt("Jumlah: ");
        cc.beliSaham(ks, jm);
    }

    private void jualSaham(CustomerController cc) {
        List<Saham> bisaJual = cc.lihatSahamBisaDijual();
        System.out.println("-- Saham tersedia untuk dijual --");
        bisaJual.forEach(s -> System.out.println(s.getKode() + " | " + s.getNamaPerusahaan() + " | " + s.getHarga()));
        String ks2 = promptNonEmpty("Kode Saham: ");
        int jm2 = promptPositiveInt("Jumlah: ");
        cc.jualSaham(ks2, jm2);
    }

    private void beliSBN(CustomerController cc) {
        List<SBN> sbnBeli = cc.lihatSNBBisaDibeli();
        System.out.println("-- SBN tersedia untuk dibeli --");
        sbnBeli.forEach(sbn -> System.out.println(sbn.getNama() + " | Kuota: " + sbn.getKuotaNasional() + " | Bunga: " + sbn.getBunga()));
        String ns3 = promptNonEmpty("Nama SBN: ");
        double nom = promptPositiveDouble("Nominal: ");
        cc.beliSBN(ns3, nom);
    }

    private void simulasiSBN(CustomerController cc) {
        List<SBN> sbnJual = cc.lihatSNBDijual();
        System.out.println("-- SBN tersedia untuk dijual --");
        sbnJual.forEach(sbn -> System.out.println(sbn.getNama() + " | Nominal dimiliki: " + sbn.getKuotaNasional()));
        String ns2 = promptNonEmpty("Nama SBN: ");
        double nom2 = promptPositiveDouble("Nominal: ");
        cc.simulasiSBN(ns2, nom2);
    }

    // Helper methods
    private String promptNonEmpty(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = sc.nextLine();
            if (!input.isEmpty()) {
                break;
            }
            System.out.println("Input tidak boleh kosong.");
        }
        return input;
    }

    private int promptPositiveInt(String prompt) {
        int input;
        while (true) {
            System.out.print(prompt);
            try {
                input = Integer.parseInt(sc.nextLine());
                if (input > 0) {
                    break;
                }
                System.out.println("Input harus lebih besar dari nol.");
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
            }
        }
        return input;
    }

    private double promptPositiveDouble(String prompt) {
        double input;
        while (true) {
            System.out.print(prompt);
            try {
                input = Double.parseDouble(sc.nextLine());
                if (input > 0) {
                    break;
                }
                System.out.println("Input harus lebih besar dari nol.");
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
            }
        }
        return input;
    }
}
