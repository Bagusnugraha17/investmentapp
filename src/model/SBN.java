package model;

public class SBN {
    private String nama;
    private double bunga; // persen per tahun
    private int jangkaWaktu; // tahun
    private String jatuhTempo;

    public SBN(String nama, double bunga, int jangkaWaktu, String jatuhTempo) {
        this.nama = nama;
        this.bunga = bunga;
        this.jangkaWaktu = jangkaWaktu;
        this.jatuhTempo = jatuhTempo;
    }

    public String getNama() {
        return nama;
    }

    public double getBunga() {
        return bunga;
    }

    public int getJangkaWaktu() {
        return jangkaWaktu;
    }

    public String getJatuhTempo() {
        return jatuhTempo;
    }
}
