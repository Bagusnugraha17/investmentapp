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
        System.out.println("+--------------------------------------------------------------------------------------+");
        System.out.println("| Kode       | Nama Perusahaan                |   Jumlah Lembar | Nilai (Rp)           |");
        System.out.println("+--------------------------------------------------------------------------------------+");
        double nilai = jumlahLembar * saham.getHarga();
        return String.format(
                "| %-10s | %-30s | %15d | Rp%,17.2f",
                saham.getKode(),
                saham.getNamaPerusahaan(),
                jumlahLembar,
                nilai
        );

    }
}
