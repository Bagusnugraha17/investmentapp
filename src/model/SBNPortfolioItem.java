package model;

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
        double bungaPerBulan = sbn.getBunga() / 12 / 100 * nominal;
        return sbn.getNama() + " | nominal: " + nominal +
                " | kupon/bln: " + bungaPerBulan;
    }
}
