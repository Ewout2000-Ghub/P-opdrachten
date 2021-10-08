package Model;

public class Product {

    private int productNummer;
    private String naam;
    private String beschrijving;
    private int prijs;

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

    public String toString() {
        return "Product {#" + productNummer + ", " +
                naam + ", " +
                beschrijving + ", " +
                prijs + ", " + "}";
    }
}
