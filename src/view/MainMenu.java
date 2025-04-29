package view;

import controller.AdminController;
import controller.CustomerController;
import model.Saham;
import model.SBN;
import model.User;
import util.LoginUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private Scanner sc = new Scanner(System.in);
    private List<Saham> daftarSaham = new ArrayList<>();
    private List<SBN> daftarSBN = new ArrayList<>();

    public void start() {
        while (true) {
            printLine();
            System.out.println("|         SELAMAT DATANG          |");
            printLine();
            System.out.println("| 1. Login                        |");
            System.out.println("| 0. Keluar                       |");
            printLine();
            String pilihan = promptMenuInput("Pilih menu: ", 0, 1);

            if (pilihan.equals("1")) {
                String u = promptNonEmpty("Username: ");
                String p = promptNonEmpty("Password: ");

                User user = LoginUtil.authenticate(u, p);
                if (user == null) {
                    System.out.println("Login gagal. Username atau password salah.");
                    continue;
                }

                if (user instanceof model.Admin) {
                    AdminController ac = new AdminController(daftarSaham, daftarSBN);
                    new AdminMenu(sc, daftarSaham, daftarSBN).tampil(ac);
                } else {
                    CustomerController cc = new CustomerController(daftarSaham, daftarSBN);
                    new CustomerMenu(sc, daftarSaham, daftarSBN).tampil(cc);
                }
            } else {
                System.out.println("Terima kasih. Sampai jumpa!");
                break;
            }
        }
    }

    private String promptNonEmpty(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (!input.isEmpty()) break;
            System.out.println("Input tidak boleh kosong.");
        }
        return input;
    }

    private String promptMenuInput(String prompt, int min, int max) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (input.matches("\\d+")) {
                int number = Integer.parseInt(input);
                if (number >= min && number <= max) break;
            }
            System.out.println("Pilihan tidak valid. Masukkan angka antara " + min + " dan " + max + ".");
        }
        return input;
    }

    private void printLine() {
        System.out.println("+---------------------------------+");
    }
}

// ======================= ADMIN MENU ===========================
class AdminMenu {
    private Scanner sc;
    private List<Saham> daftarSaham;
    private List<SBN> daftarSBN;

    public AdminMenu(Scanner sc, List<Saham> daftarSaham, List<SBN> daftarSBN) {
        this.sc = sc;
        this.daftarSaham = daftarSaham;
        this.daftarSBN = daftarSBN;
    }

    public void tampil(AdminController ac) {
        while (true) {
            printLine();
            System.out.println("|          ADMIN MENU             |");
            printLine();
            System.out.println("| 1. Tambah Saham                 |");
            System.out.println("| 2. Ubah Harga Saham             |");
            System.out.println("| 3. Tambah SBN                   |");
            System.out.println("| 0. Logout                       |");
            printLine();
            String c = promptMenuInput("Pilih menu: ", 0, 3);

            switch (c) {
                case "1" -> tambahSaham(ac);
                case "2" -> ubahHargaSaham(ac);
                case "3" -> tambahSBN(ac);
                case "0" -> { return; }
            }
        }
    }

    private void tambahSaham(AdminController ac) {
        String k = promptNonEmpty("Kode Saham: ");
        String n = promptNonEmpty("Nama Perusahaan: ");
        double h = promptPositiveDouble("Harga Saham: ");
        ac.tambahSaham(new Saham(k, n, h));
    }

    private void ubahHargaSaham(AdminController ac) {
        String kc = promptNonEmpty("Kode Saham: ");
        Saham saham = cariSaham(kc);
        if (saham == null) {
            System.out.println("Kode saham tidak ditemukan.");
            return;
        }
        double hb = promptPositiveDouble("Harga baru: ");
        ac.ubahHargaSaham(kc, hb);
    }

    private void tambahSBN(AdminController ac) {
        String ns = promptNonEmpty("Nama SBN: ");
        double b = promptPositiveDouble("Bunga (%/th): ");
        int jw = promptPositiveInt("Jangka waktu (tahun): ");
        String jt = promptNonEmpty("Tanggal Jatuh Tempo: ");
        double quota = promptPositiveDouble("Kuota Nasional: ");
        ac.tambahSBN(new SBN(ns, b, jw, jt, quota));
    }

    private Saham cariSaham(String kode) {
        for (Saham s : daftarSaham) {
            if (s.getKode().equalsIgnoreCase(kode)) return s;
        }
        return null;
    }

    // Helper input
    private String promptNonEmpty(String prompt) {
        System.out.print(prompt);
        String input = sc.nextLine().trim();
        return input.isEmpty() ? promptNonEmpty(prompt) : input;
    }

    private int promptPositiveInt(String prompt) {
        try {
            System.out.print(prompt);
            int val = Integer.parseInt(sc.nextLine());
            return val > 0 ? val : promptPositiveInt(prompt);
        } catch (Exception e) {
            return promptPositiveInt(prompt);
        }
    }

    private double promptPositiveDouble(String prompt) {
        try {
            System.out.print(prompt);
            double val = Double.parseDouble(sc.nextLine());
            return val > 0 ? val : promptPositiveDouble(prompt);
        } catch (Exception e) {
            return promptPositiveDouble(prompt);
        }
    }

    private String promptMenuInput(String prompt, int min, int max) {
        System.out.print(prompt);
        try {
            int val = Integer.parseInt(sc.nextLine());
            return (val >= min && val <= max) ? String.valueOf(val) : promptMenuInput(prompt, min, max);
        } catch (Exception e) {
            return promptMenuInput(prompt, min, max);
        }
    }

    private void printLine() {
        System.out.println("+---------------------------------+");
    }
}

// ======================= CUSTOMER MENU ===========================
class CustomerMenu {
    private Scanner sc;
    private List<Saham> daftarSaham;
    private List<SBN> daftarSBN;

    public CustomerMenu(Scanner sc, List<Saham> daftarSaham, List<SBN> daftarSBN) {
        this.sc = sc;
        this.daftarSaham = daftarSaham;
        this.daftarSBN = daftarSBN;
    }

    public void tampil(CustomerController cc) {
        while (true) {
            printLine();
            System.out.println("|        CUSTOMER MENU            |");
            printLine();
            System.out.println("| 1. Beli Saham                   |");
            System.out.println("| 2. Jual Saham                   |");
            System.out.println("| 3. Beli SBN                     |");
            System.out.println("| 4. Simulasi SBN                 |");
            System.out.println("| 5. Lihat Portofolio             |");
            System.out.println("| 0. Logout                       |");
            printLine();
            String c = promptMenuInput("Pilih menu: ", 0, 5);

            switch (c) {
                case "1" -> beliSaham(cc);
                case "2" -> jualSaham(cc);
                case "3" -> beliSBN(cc);
                case "4" -> simulasiSBN(cc);
                case "5" -> cc.lihatPortofolio();
                case "0" -> { return; }
            }
        }
    }

    private void beliSaham(CustomerController cc) {
        var bisaBeli = cc.lihatSahamBisaDibeli();
        System.out.println("-- Saham tersedia untuk dibeli --");
        bisaBeli.forEach(s -> System.out.println(s.getKode() + " | " + s.getNamaPerusahaan() + " | " + s.getHarga()));
        String ks = promptNonEmpty("Kode Saham: ");
        if (!sahamAda(ks)) {
            System.out.println("Kode saham tidak ditemukan.");
            return;
        }
        int jm = promptPositiveInt("Jumlah: ");
        cc.beliSaham(ks, jm);
    }

    private void jualSaham(CustomerController cc) {
        var bisaJual = cc.lihatSahamBisaDijual();
        System.out.println("-- Saham tersedia untuk dijual --");
        bisaJual.forEach(s -> System.out.println(s.getKode() + " | " + s.getNamaPerusahaan() + " | " + s.getHarga()));
        String ks2 = promptNonEmpty("Kode Saham: ");
        if (!sahamAda(ks2)) {
            System.out.println("Kode saham tidak ditemukan.");
            return;
        }
        int jm2 = promptPositiveInt("Jumlah: ");
        cc.jualSaham(ks2, jm2);
    }

    private void beliSBN(CustomerController cc) {
        var sbnBeli = cc.lihatSNBBisaDibeli();
        System.out.println("-- SBN tersedia untuk dibeli --");
        sbnBeli.forEach(sbn -> System.out.println(sbn.getNama() + " | Kuota: " + sbn.getKuotaNasional() + " | Bunga: " + sbn.getBunga()));
        String ns3 = promptNonEmpty("Nama SBN: ");
        if (!sbnAda(ns3)) {
            System.out.println("Nama SBN tidak ditemukan.");
            return;
        }
        double nom = promptPositiveDouble("Nominal: ");
        cc.beliSBN(ns3, nom);
    }

    private void simulasiSBN(CustomerController cc) {
        var sbnJual = cc.lihatSNBDijual();
        System.out.println("-- SBN tersedia untuk dijual --");
        sbnJual.forEach(sbn -> System.out.println(sbn.getNama() + " | Nominal dimiliki: " + sbn.getKuotaNasional()));
        String ns2 = promptNonEmpty("Nama SBN: ");
        if (!sbnAda(ns2)) {
            System.out.println("Nama SBN tidak ditemukan.");
            return;
        }
        double nom2 = promptPositiveDouble("Nominal: ");
        cc.simulasiSBN(ns2, nom2);
    }

    private boolean sahamAda(String kode) {
        return daftarSaham.stream().anyMatch(s -> s.getKode().equalsIgnoreCase(kode));
    }

    private boolean sbnAda(String nama) {
        return daftarSBN.stream().anyMatch(s -> s.getNama().equalsIgnoreCase(nama));
    }

    private String promptNonEmpty(String prompt) {
        System.out.print(prompt);
        String input = sc.nextLine().trim();
        return input.isEmpty() ? promptNonEmpty(prompt) : input;
    }

    private int promptPositiveInt(String prompt) {
        try {
            System.out.print(prompt);
            int val = Integer.parseInt(sc.nextLine());
            return val > 0 ? val : promptPositiveInt(prompt);
        } catch (Exception e) {
            return promptPositiveInt(prompt);
        }
    }

    private double promptPositiveDouble(String prompt) {
        try {
            System.out.print(prompt);
            double val = Double.parseDouble(sc.nextLine());
            return val > 0 ? val : promptPositiveDouble(prompt);
        } catch (Exception e) {
            return promptPositiveDouble(prompt);
        }
    }

    private String promptMenuInput(String prompt, int min, int max) {
        System.out.print(prompt);
        try {
            int val = Integer.parseInt(sc.nextLine());
            return (val >= min && val <= max) ? String.valueOf(val) : promptMenuInput(prompt, min, max);
        } catch (Exception e) {
            return promptMenuInput(prompt, min, max);
        }
    }

    private void printLine() {
        System.out.println("+---------------------------------+");
    }
}
