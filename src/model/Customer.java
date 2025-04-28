package model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private List<PortfolioItem> portofolioSaham = new ArrayList<>();
    private List<SBNPortfolioItem> portofolioSBN = new ArrayList<>();

    public Customer(String username, String password) {
        super(username, password);
    }

    public List<PortfolioItem> getPortofolioSaham() {
        return portofolioSaham;
    }

    public List<SBNPortfolioItem> getPortofolioSBN() {
        return portofolioSBN;
    }
}
