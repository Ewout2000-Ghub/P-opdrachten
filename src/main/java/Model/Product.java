package Model;

import java.util.List;

public class Product {

    private int productNummer;
    private String naam;
    private String beschrijving;
    private int prijs;
    public List<OVChipkaart> ovChipList;

    public Product(int pNum, String naam, String bschr, int prijs) {
        this.productNummer = pNum;
        this.naam = naam;
        this.beschrijving = bschr;
        this.prijs = prijs;
    }

    public void setProductNummer(int productNummer) {
        this.productNummer = productNummer;
    }
    public void setNaam(String naam) {
        this.naam = naam;
    }
    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }
    public void setPrijs(int prijs) {
        this.prijs = prijs;
    }

    public int getProductNummer() {
        return productNummer;
    }
    public String getNaam() {
        return naam;
    }
    public String getBeschrijving() {
        return beschrijving;
    }
    public int getPrijs() {
        return prijs;
    }
    public List<OVChipkaart> getOvChipList() {
        return ovChipList;
    }

    public void removeOVChipkaart(OVChipkaart ovChipkaart) {
        if (ovChipList.contains(ovChipkaart)) {
            ovChipList.remove(ovChipkaart);
        }
    }

    public void addOVChipkaart (OVChipkaart ovChipkaart) {
        if (!ovChipList.contains(ovChipkaart)) {
            ovChipList.add(ovChipkaart);
        }
    }

    public String toString() {
        return "Product {#" + productNummer + ", " +
                naam + ", " +
                beschrijving + ", " +
                prijs + ", " + "}";
    }
}
