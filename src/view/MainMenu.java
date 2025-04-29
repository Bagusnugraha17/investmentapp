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
            drawHeader("SELAMAT DATANG");
            System.out.println("1. Login");
            System.out.println("0. Keluar");
            drawFooter();

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
                    new AdminMenu(sc, daftarSaham, daftarSBN).tampil(
                            new AdminController(daftarSaham, daftarSBN)
                    );
                } else {
                    new CustomerMenu(sc, daftarSaham, daftarSBN).tampil(
                            new CustomerController(daftarSaham, daftarSBN)
                    );
                }
            } else {
                System.out.println("Terima kasih. Sampai jumpa!");
                break;
            }
        }
    }

    private void drawHeader(String title) {
        System.out.println("+--------------------------------------------+");
        System.out.printf("| %-40s   |%n", title);
        System.out.println("+--------------------------------------------+");
    }

    private void drawFooter() {
        System.out.println("+--------------------------------------------+");
    }

    private String promptNonEmpty(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (input.isEmpty()) System.out.println("Input tidak boleh kosong.");
        } while (input.isEmpty());
        return input;
    }

    private String promptMenuInput(String prompt, int min, int max) {
        String input;
        int num;
        do {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            try {
                num = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Pilihan tidak valid. Masukkan angka antara " + min + " dan " + max + ".");
                continue;
            }
            if (num < min || num > max) {
                System.out.println("Pilihan tidak valid. Masukkan angka antara " + min + " dan " + max + ".");
            } else break;
        } while (true);
        return input;
    }
}

// ---------------------- Admin Menu ----------------------
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
            drawHeader("ADMIN MENU");
            System.out.println("1. Tambah Saham");
            System.out.println("2. Ubah Harga Saham");
            System.out.println("3. Tambah SBN");
            System.out.println("0. Kembali");
            drawFooter();

            String c = promptMenuInput("Pilih menu: ", 0, 3);
            if (c.equals("1")) tambahSaham(ac);
            else if (c.equals("2")) ubahHargaSaham(ac);
            else if (c.equals("3")) tambahSBN(ac);
            else break;
        }
    }

    private void tambahSaham(AdminController ac) {
        String k = promptNonEmpty("Kode Saham: ");
        String n = promptNonEmpty("Nama Perusahaan: ");
        double h = promptPositiveDouble("Harga Saham: ");
        ac.tambahSaham(new Saham(k, n, h));
        System.out.println(">> Saham " + k + " berhasil ditambahkan.");
        tungguEnter();
    }

    private void ubahHargaSaham(AdminController ac) {
        String kc = promptNonEmpty("Kode Saham: ");
        Saham s = daftarSaham.stream()
                .filter(x -> x.getKode().equalsIgnoreCase(kc))
                .findFirst().orElse(null);
        if (s == null) {
            System.out.println("Saham tidak ditemukan.");
            tungguEnter(); return;
        }
        double hb = promptPositiveDouble("Harga baru: ");
        ac.ubahHargaSaham(kc, hb);
        System.out.println(">> Harga saham " + kc + " diubah menjadi Rp" + hb);
        tungguEnter();
    }

    private void tambahSBN(AdminController ac) {
        String ns = promptNonEmpty("Nama SBN: ");
        double b = promptPositiveDouble("Bunga (%): ");
        int jw = promptPositiveInt("Jangka waktu (tahun): ");
        String jt = promptNonEmpty("Tanggal Jatuh Tempo: ");
        double quota = promptPositiveDouble("Kuota Nasional (Rp): ");
        ac.tambahSBN(new SBN(ns, b, jw, jt, quota));
        System.out.println(">> SBN " + ns + " berhasil ditambahkan.");
        tungguEnter();
    }

    private void drawHeader(String title) {
        System.out.println("+--------------------------------------------+");
        System.out.printf("| %-40s   |%n", title);
        System.out.println("+--------------------------------------------+");
    }

    private void drawFooter() {
        System.out.println("+--------------------------------------------+");
    }

    private void tungguEnter() {
        System.out.println("Tekan Enter untuk melanjutkan...");
        sc.nextLine();
    }

    private String promptNonEmpty(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (input.isEmpty()) System.out.println("Input tidak boleh kosong.");
        } while (input.isEmpty());
        return input;
    }

    private int promptPositiveInt(String prompt) {
        int val;
        while (true) {
            try {
                System.out.print(prompt);
                val = Integer.parseInt(sc.nextLine());
                if (val > 0) return val;
            } catch (NumberFormatException ignored) {}
        }
    }

    private double promptPositiveDouble(String prompt) {
        double val;
        while (true) {
            try {
                System.out.print(prompt);
                val = Double.parseDouble(sc.nextLine());
                if (val > 0) return val;
            } catch (NumberFormatException ignored) {}
        }
    }

    private String promptMenuInput(String prompt, int min, int max) {
        String input;
        int num;
        while (true) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            try { num = Integer.parseInt(input); }
            catch (NumberFormatException e) { continue; }
            if (num >= min && num <= max) return input;
        }
    }
}

// ---------------------- Customer Menu ----------------------
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
            drawHeader("CUSTOMER MENU");
            System.out.println("1. Beli Saham");
            System.out.println("2. Jual Saham");
            System.out.println("3. Beli SBN");
            System.out.println("4. Simulasi SBN");
            System.out.println("5. Lihat Portofolio");
            System.out.println("0. Kembali");
            drawFooter();

            String c = promptMenuInput("Pilih menu: ", 0, 5);
            switch (c) {
                case "1": beliSaham(cc); break;
                case "2": jualSaham(cc); break;
                case "3": beliSBN(cc); break;
                case "4": simulasiSBN(cc); break;
                case "5":
                    cc.lihatPortofolio();
                    tungguEnter();
                    break;
                case "0": return;
            }
        }
    }

    private void beliSaham(CustomerController cc) {
        var bisaBeli = cc.lihatSahamBisaDibeli();
        System.out.println("+------------------------------------------------------------------+");
        System.out.printf("| %-10s | %-30s | %-15s    |%n", "Kode", "Nama Perusahaan", "Harga (Rp)");
        System.out.println("+------------------------------------------------------------------+");
        bisaBeli.forEach(s ->
                System.out.printf("| %-10s | %-30s | Rp%,15.2f |%n",
                        s.getKode(), s.getNamaPerusahaan(), s.getHarga())
        );
        System.out.println("+------------------------------------------------------------------+");

        String ks = promptNonEmpty("Kode Saham: ");
        if (!sahamAda(ks)) {
            System.out.println("Saham tidak ditemukan.");
            tungguEnter(); return;
        }
        int jm = promptPositiveInt("Jumlah: ");
        cc.beliSaham(ks, jm);
        System.out.println(">> Berhasil beli " + jm + " lembar " + ks);
        tungguEnter();
    }

    private void jualSaham(CustomerController cc) {
        var bisaJual = cc.lihatSahamBisaDijual();
        System.out.println("+-----------------------------------------------------------------+");
        System.out.printf("| %-10s | %-30s | %-15s   |%n", "Kode", "Nama Perusahaan", "Harga (Rp)");
        System.out.println("+-----------------------------------------------------------------+");
        bisaJual.forEach(s ->
                System.out.printf("| %-10s | %-30s | Rp%,15.2f |%n",
                        s.getKode(), s.getNamaPerusahaan(), s.getHarga())
        );
        System.out.println("+-----------------------------------------------------------------+");

        String ks = promptNonEmpty("Kode Saham: ");
        if (!sahamAda(ks)) {
            System.out.println("Saham tidak ditemukan.");
            tungguEnter(); return;
        }
        int jm = promptPositiveInt("Jumlah: ");
        cc.jualSaham(ks, jm);
        System.out.println(">> Berhasil jual " + jm + " lembar " + ks);
        tungguEnter();
    }

    private void beliSBN(CustomerController cc) {
        var sbnBeli = cc.lihatSNBBisaDibeli();
        System.out.println("+------------------------------------------------------+");
        System.out.printf("| %-20s | %-15s | %-10s  |%n", "Nama SBN", "Kuota (Rp)", "Bunga (%%)");
        System.out.println("+------------------------------------------------------+");
        sbnBeli.forEach(sbn ->
                System.out.printf("| %-20s | Rp%,13.2f | %10.2f%% |%n",
                        sbn.getNama(), sbn.getKuotaNasional(), sbn.getBunga())
        );
        System.out.println("+------------------------------------------------------+");

        String ns = promptNonEmpty("Nama SBN: ");
        if (!sbnAda(ns)) {
            System.out.println("SBN tidak ditemukan.");
            tungguEnter(); return;
        }
        double nom = promptPositiveDouble("Nominal (Rp): ");
        cc.beliSBN(ns, nom);
        System.out.println(">> Berhasil beli SBN " + ns);
        tungguEnter();
    }

    private void simulasiSBN(CustomerController cc) {
        var sbnJual = cc.lihatSNBDijual();
        System.out.println("+---------------------------------------------+");
        System.out.printf("| %-20s | %-20s |%n", "Nama SBN", "Dimiliki (Rp)");
        System.out.println("+---------------------------------------------+");
        sbnJual.forEach(sbn ->
                System.out.printf("| %-20s | Rp%,18.2f |%n",
                        sbn.getNama(), sbn.getKuotaNasional())
        );
        System.out.println("+---------------------------------------------+");

        String ns = promptNonEmpty("Nama SBN: ");
        if (!sbnAda(ns)) {
            System.out.println("SBN tidak ditemukan.");
            tungguEnter(); return;
        }
        double nom2 = promptPositiveDouble("Nominal (Rp): ");
        cc.simulasiSBN(ns, nom2);
        tungguEnter();
    }

    private void drawHeader(String title) {
        System.out.println("+------------------------------------------------------------+");
        System.out.printf("| %-56s |%n", title);
        System.out.println("+------------------------------------------------------------+");
    }

    private void drawFooter() {
        System.out.println("+------------------------------------------------------------+");
    }

    private void tungguEnter() {
        System.out.println("Tekan Enter untuk melanjutkan...");
        sc.nextLine();
    }

    private String promptNonEmpty(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = sc.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    private int promptPositiveInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int val = Integer.parseInt(sc.nextLine());
                if (val > 0) return val;
            } catch (NumberFormatException ignored) {}
        }
    }

    private double promptPositiveDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double val = Double.parseDouble(sc.nextLine());
                if (val > 0) return val;
            } catch (NumberFormatException ignored) {}
        }
    }

    private String promptMenuInput(String prompt, int min, int max) {
        String input;
        int num;
        while (true) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            try { num = Integer.parseInt(input); }
            catch (NumberFormatException e) { continue; }
            if (num >= min && num <= max) return input;
        }
    }

    private boolean sahamAda(String kode) {
        return daftarSaham.stream()
                .anyMatch(s -> s.getKode().equalsIgnoreCase(kode));
    }

    private boolean sbnAda(String nama) {
        return daftarSBN.stream()
                .anyMatch(s -> s.getNama().equalsIgnoreCase(nama));
    }
}
