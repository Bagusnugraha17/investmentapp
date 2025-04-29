package util;

import model.Admin;
import model.Customer;
import model.User;

public class LoginUtil {
    public static User authenticate(String user, String pass) {
        if ("Bayusuputra24117".equals(user) && "bayuganteng2".equals(pass)) {
            return new Admin(user, pass);
        }
        if ("Bagusnugraha24139".equals(user) && "tugus123".equals(pass)) {
            return new Customer(user, pass);
        }
        return null;
    }
}
