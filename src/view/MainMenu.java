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
        System.out.print("Username: ");
        String u = sc.nextLine();
        System.out.print("Password: ");
        String p = sc.nextLine();

        User user = LoginUtil.authenticate(u, p);
        if (user == null) {
            System.out.println("Login gagal.");
            return;
        }
        if (user.getClass().getSimpleName().equals("Admin")) {
            AdminController ac = new AdminController(daftarSaham, daftarSBN);
            menuAdmin(ac);
        } else {
            CustomerController cc = new CustomerController(daftarSaham, daftarSBN);
            menuCustomer(cc);
        }
    }

    private void menuAdmin(AdminController ac) {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Tambah Saham");
            System.out.println("2. Ubah Harga Saham");
            System.out.println("3. Tambah SBN");
            System.out.println("0. Logout");
            System.out.print("> ");
            String c = sc.nextLine();
            switch (c) {
                case "1":
                    System.out.print("Kode: "); String k = sc.nextLine();
                    System.out.print("Nama: "); String n = sc.nextLine();
                    System.out.print("Harga: "); double h = Double.parseDouble(sc.nextLine());
                    ac.tambahSaham(new Saham(k,n,h));
                    break;
                case "2":
                    System.out.print("Kode: "); String kc = sc.nextLine();
                    System.out.print("Harga baru: "); double hb = Double.parseDouble(sc.nextLine());
                    ac.ubahHargaSaham(kc, hb);
                    break;
                case "3":
                    System.out.print("Nama SBN: "); String ns = sc.nextLine();
                    System.out.print("Bunga (%/th): "); double b = Double.parseDouble(sc.nextLine());
                    System.out.print("Jangka (tahun): "); int j = Integer.parseInt(sc.nextLine());
                    System.out.print("Tanggal Jatuh Tempo: "); String t = sc.nextLine();
                    ac.tambahSBN(new SBN(ns, b, j, t));
                    break;
                case "0": return;
                default: System.out.println("Pilihan salah.");
            }
        }
    }

    private void menuCustomer(CustomerController cc) {
        while (true) {
            System.out.println("\n--- Customer Menu ---");
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
                    System.out.print("Kode Saham: "); String ks = sc.nextLine();
                    System.out.print("Jumlah: "); int jm = Integer.parseInt(sc.nextLine());
                    cc.beliSaham(ks, jm);
                    break;
                case "2":
                    System.out.print("Kode Saham: "); String ks2 = sc.nextLine();
                    System.out.print("Jumlah: "); int jm2 = Integer.parseInt(sc.nextLine());
                    cc.jualSaham(ks2, jm2);
                    break;
                case "3":
                    System.out.print("Nama SBN: "); String ns = sc.nextLine();
                    System.out.print("Nominal: "); double nom = Double.parseDouble(sc.nextLine());
                    cc.beliSBN(ns, nom);
                    break;
                case "4":
                    System.out.print("Nama SBN: "); String ns2 = sc.nextLine();
                    System.out.print("Nominal: "); double nom2 = Double.parseDouble(sc.nextLine());
                    cc.simulasiSBN(ns2, nom2);
                    break;
                case "5":
                    cc.lihatPortofolio();
                    break;
                case "0": return;
                default: System.out.println("Pilihan salah.");
            }
        }
    }
}
