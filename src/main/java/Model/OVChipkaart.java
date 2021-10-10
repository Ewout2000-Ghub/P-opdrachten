package Model;

import java.sql.Date;
import java.util.List;

public class OVChipkaart {

    private int kaartNummer;
    private Date geldigTot;
    private int klasse;
    private double saldo;
    private int reizigerId;
    private Reiziger r;
    public List<Product> productList;

    public OVChipkaart(int kaartNr, Date gldTot, int klasse, double saldo, int rId) {
        this.kaartNummer = kaartNr;
        this.geldigTot = gldTot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reizigerId = rId;
    }

    public OVChipkaart(int kaartNr, Date gldTot, int klasse, double saldo, int rId, List<Product> pList) {
        this.kaartNummer = kaartNr;
        this.geldigTot = gldTot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reizigerId = rId;
        this.productList = pList;
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
    public void setSaldo(double saldo) {
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
    public double getSaldo() {
        return saldo;
    }
    public int getReizigerId() {
        return reizigerId;
    }
    public Reiziger getReiziger() {return r;}
    public List<Product> getProductList() {
        return productList;
    }

    public void removeProduct(Product product) {
        if (productList.contains(product)) {
            productList.remove(product);
        }
    }

    public void addProduct (Product product) {
        if (!productList.contains(product)) {
            productList.add(product);
        }
    }

    public String toString() {
        return "OVChipkaart {#" + kaartNummer + ": " + geldigTot + ", " +
                klasse + ", " +
                saldo + ", " +
                reizigerId +
                "" + "}";
    }
}
