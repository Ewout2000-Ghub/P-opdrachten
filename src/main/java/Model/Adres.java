package Model;

public class Adres {

    private int id;
    private String postcode;
    private String huisnummer;
    private String straat;
    private String woonplaats;
    private int reizigerId;
    private Reiziger r;

    public Adres(int id, String ptc, String hsnr, String strt, String wnplts, int rId) {
        this.id = id;
        this.postcode = ptc;
        this.huisnummer = hsnr;
        this.straat = strt;
        this.woonplaats = wnplts;
        this.reizigerId = rId;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setPostcode(String pc) {
        this.postcode = postcode;
    }
    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }
    public void setStraat(String straat) {
        this.straat = straat;
    }
    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }
    public void setReizigerId(int reizigerId) {
        this.reizigerId = reizigerId;
    }

    public int getId() {
        return id;
    }
    public String getPostcode() {
        return postcode;
    }
    public String getHuisnummer() {
        return huisnummer;
    }
    public String getStraat() {
        return straat;
    }
    public String getWoonplaats() {
        return woonplaats;
    }
    public int getReizigerId() {
        return reizigerId;
    }
    public Reiziger getReiziger() {
        return r;
    }

    public String toString() {
        return "{#" + id + ": " + postcode + ", " +
                huisnummer + ", " +
                straat + ", " +
                woonplaats + ", " +
                reizigerId + "}";
    }
}
