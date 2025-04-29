import java.util.Scanner;
import model.User;
import util.LoginUtil;

public class LoginMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("+------------------------------+");
            System.out.println("|          W E L C O M E       |");
            System.out.println("|         LOGIN PAGE           |");
            System.out.println("+------------------------------+");
            System.out.println("| Pilih Tipe User Di bawah     |");
            System.out.println("| [1] Administrator            |");
            System.out.println("| [2] Customer                 |");
            System.out.println("| [0] Keluar Program           |");
            System.out.println("+------------------------------+");
            System.out.print("Masukan pilihan : ");
            
            pilihan = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (pilihan) {
                case 1:
                    login(scanner, "Administrator");
                    break;
                case 2:
                    login(scanner, "Customer");
                    break;
                case 0:
                    System.out.println("Keluar dari program...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan != 0);
        
        scanner.close();
    }

    private static void login(Scanner scanner, String tipeUser) {
        System.out.println("=== Login " + tipeUser + " ===");
        System.out.print("Username : ");
        String user = scanner.nextLine();
        System.out.print("Password : ");
        String pass = scanner.nextLine();

        User loggedInUser = LoginUtil.authenticate(user, pass);
        if (loggedInUser != null) {
            System.out.println("Login berhasil sebagai " + tipeUser + "!\n");
            // Di sini bisa arahkan ke menu masing-masing role
        } else {
            System.out.println("Login gagal. Username atau password salah.\n");
        }
    }
}
