package Model;

import java.sql.Date;

public class Reiziger {

    private int id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;
    private Adres adres;

    public Reiziger(int id, String vrltr, String tsvg, String atnm, Date gbdtm) {
        this.id = id;
        this.voorletters = vrltr;
        this.tussenvoegsel = tsvg;
        this.achternaam = atnm;
        this.geboortedatum = gbdtm;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }
    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }
    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }
    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }
    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public int getId() {
        return id;
    }
    public String getVoorletters() {
        return voorletters;
    }
    public String getTussenvoegsel() {
        return tussenvoegsel;
    }
    public String getAchternaam() {
        return achternaam;
    }
    public Date getGeboortedatum() {
        return geboortedatum;
    }
    public Adres getAdres() {
        return adres;
    }

    public String toString() {
        return "Reiziger {#" + id + ": " + voorletters + ". " +
                tussenvoegsel + " " +
                achternaam + " (" +
                geboortedatum + "), Adres " + adres.toString() + "}";
    }
}
