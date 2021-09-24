package Model;

public class Adres {

    private int id;
    private String postcode;
    private int huisnummer;
    private String straat;
    private String woonplaats;
    private Reiziger reiziger;

    public Adres(int id, String ptc, int hsnr, String strt, String wnplts) {
        this.id = id;
        this.postcode = ptc;
        this.huisnummer = hsnr;
        this.straat = strt;
        this.woonplaats = wnplts;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setPostcode(String pc) {
        this.postcode = postcode;
    }
    public void setHuisnummer(int huisnummer) {
        this.huisnummer = huisnummer;
    }
    public void setStraat(String straat) {
        this.straat = straat;
    }
    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }
    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public int getId() {
        return id;
    }
    public String getPostcode() {
        return postcode;
    }
    public int getHuisnummer() {
        return huisnummer;
    }
    public String getStraat() {
        return straat;
    }
    public String getWoonplaats() {
        return woonplaats;
    }
    public Reiziger getReiziger() {
        return reiziger;
    }

    public String toString() {
        return "#" + id + ": " + postcode + ", " +
                huisnummer + ", " +
                straat + ", " +
                woonplaats + ", " +
                reiziger.getId();
    }
}
