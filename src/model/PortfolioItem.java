package model;

public class PortfolioItem {
    private Saham saham;
    private int jumlahLembar;

    public PortfolioItem(Saham saham, int jumlahLembar) {
        this.saham = saham;
        this.jumlahLembar = jumlahLembar;
    }

    public Saham getSaham() {
        return saham;
    }

    public int getJumlahLembar() {
        return jumlahLembar;
    }

    public void tambahLembar(int n) {
        this.jumlahLembar += n;
    }

    public void kurangiLembar(int n) {
        this.jumlahLembar -= n;
    }

    @Override
    public String toString() {
        return saham.getKode() + " | " + saham.getNamaPerusahaan() +
                " | lembar: " + jumlahLembar +
                " | nilai: " + (jumlahLembar * saham.getHarga());
    }
}
