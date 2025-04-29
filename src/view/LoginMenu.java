package view;

import java.util.Scanner;
import model.User;
import util.LoginUtil;

public class LoginMenu {
    public void show() {
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("========================================");
            System.out.println("|              W E L C O M E           |");
            System.out.println("|              LOGIN PAGE              |");
            System.out.println("========================================");
            System.out.println("| Pilih Tipe User Di bawah             |");
            System.out.println("| [1] Administrator                    |");
            System.out.println("| [2] Customer                         |");
            System.out.println("| [0] Keluar Program                   |");
            System.out.println("========================================");
            System.out.print("Masukan pilihan : ");

            pilihan = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (pilihan == 1 || pilihan == 2) {
                System.out.println("========================================");
                System.out.println("|              L O G I N               |");
                System.out.println("|  Masukkan Username dan Password      |");
                System.out.println("========================================");

                System.out.print("Masukkan username Anda: ");
                String username = scanner.nextLine();
                System.out.print("Masukkan password Anda: ");
                String password = scanner.nextLine();

                User user = LoginUtil.authenticate(username, password);

                if (user != null) {
                    System.out.println("\nLogin berhasil sebagai " + user.getClass().getSimpleName());
                } else {
                    System.out.println("\nLogin gagal. Username atau password salah.");
                }

            } else if (pilihan == 0) {
                System.out.println("Keluar dari program...");
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);

        scanner.close();
    }
}
