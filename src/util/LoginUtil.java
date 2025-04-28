package util;

import model.Admin;
import model.Customer;
import model.User;

public class LoginUtil {
    public static User authenticate(String user, String pass) {
        if ("admin".equals(user) && "admin123".equals(pass)) {
            return new Admin(user, pass);
        }
        if ("customer".equals(user) && "cust123".equals(pass)) {
            return new Customer(user, pass);
        }
        return null;
    }
}
