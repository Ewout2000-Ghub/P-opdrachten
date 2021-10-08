package Model;

import java.sql.Date;

public class OVChipkaart {

    private int kaartNummer;
    private Date geldigTot;
    private int klasse;
    private int saldo;
    private int reizigerId;
    private Reiziger r;

    public OVChipkaart(int kaartNr, Date gldTot, int klasse, int saldo, int rId) {
        this.kaartNummer = kaartNr;
        this.geldigTot = gldTot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reizigerId = rId;
    }

    public void setKaartNummer(int kaartNummer) {
        this.kaartNummer = kaartNummer;
    }
    public void setGeldigTot(Date geldigTot) {
        this.geldigTot = geldigTot;
    }
    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }
    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
    public void setReizigerId(int reizigerId) {
        this.reizigerId = reizigerId;
    }


    public int getKaartNummer() {
        return kaartNummer;
    }
    public Date getGeldigTot() {
        return geldigTot;
    }
    public int getKlasse() {
        return klasse;
    }
    public int getSaldo() {
        return saldo;
    }
    public int getReizigerId() {
        return reizigerId;
    }
    public Reiziger getReiziger() {return r;}

    public String toString() {
        return "OVChipkaart {#" + kaartNummer + ": " + geldigTot + ", " +
                klasse + ", " +
                saldo + ", " +
                reizigerId + "}";
    }
}
