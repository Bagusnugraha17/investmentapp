package model;

import java.text.NumberFormat;
import java.util.Locale;

public class SBNPortfolioItem {
    private SBN sbn;
    private double nominal;

    public SBNPortfolioItem(SBN sbn, double nominal) {
        this.sbn = sbn;
        this.nominal = nominal;
    }

    public SBN getSbn() {
        return sbn;
    }

    public double getNominal() {
        return nominal;
    }

    @Override
    public String toString() {
        System.out.println("+-------------------------------------------------------------------------------------------------------------+");
        System.out.println("| Nama SBN                       |        Nominal  |    Kupon/bln    | Bunga    | Jangka      | Jatuh Tempo   |");
        System.out.println("+-------------------------------------------------------------------------------------------------------------+");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        double bungaPerBulan = sbn.getBunga() / 12 / 100 * nominal;
        return String.format(
                "| %-30s | %15s | %15s | %6.2f%% | %6d tahun | %-12s |",
                sbn.getNama(),
                formatter.format(nominal),
                formatter.format(bungaPerBulan),
                sbn.getBunga(),
                sbn.getJangkaWaktu(),
                sbn.getJatuhTempo()
        );
    }
}

