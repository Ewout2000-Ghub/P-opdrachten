package Persistence;

import Model.OVChipkaart;
import Model.Product;
import Model.Reiziger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOPsql implements ProductDAO {

    Connection connection;

    public ProductDAOPsql(Connection conn) {
        this.connection = conn;
    }

    public boolean save(Product product) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO product (product_nummer, naam, beschrijving, prijs) " +
                            "VALUES (?, ?, ?, ?)");
            statement.setInt(1, product.getProductNummer());
            statement.setString(2, product.getNaam());
            statement.setString(3, product.getBeschrijving());
            statement.setInt(4, product.getPrijs());

            statement.executeQuery();

            PreparedStatement statement2 = connection.prepareStatement(
                    "INSERT INTO ov_chipkaart_product (kaart_nummer, product_nummer, status, last_update) " +
                            "VALUES (?, ?, ?, ?)");
            for (OVChipkaart ovChipkaart : product.ovChipList) {
                statement2.setInt(1, ovChipkaart.getKaartNummer());
                statement2.setInt(2, product.getProductNummer());
                statement2.setString(3, "gekocht");
                statement2.setDate(4, Date.valueOf(LocalDate.now()));
            }

            statement2.executeQuery();

            System.out.println("Save complete");

            return true;
        } catch (SQLException e) {
            System.out.println("Saving product didn't work: " + e.getMessage());
            return false;
        }
    }

    public boolean update(Product product) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE product SET " +
                            "product_nummer = ?, " +
                            "naam = ?, " +
                            "beschrijving = ?, " +
                            "prijs = ?");
            statement.setInt(1, product.getProductNummer());
            statement.setString(2, product.getNaam());
            statement.setString(3, product.getBeschrijving());
            statement.setInt(4, product.getPrijs());

            statement.executeQuery();

            System.out.println("Update complete");
            return true;
        } catch (SQLException e) {
            System.out.println("Updating product didn't work: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(Product product) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM product WHERE (" +
                            "product_nummer = ?, " +
                            "naam = ?, " +
                            "beschrijving = ?, " +
                            "prijs = ?)");
            statement.setInt(1, product.getProductNummer());
            statement.setString(2, product.getNaam());
            statement.setString(3, product.getBeschrijving());
            statement.setInt(4, product.getPrijs());

            statement.executeQuery();

            PreparedStatement statement2 = connection.prepareStatement(
                    "DELETE FROM ov_chipkaart_product WHERE (" +
                            "kaart_nummer = ?, " +
                            "product_nummer = ?, " +
                            "status = ?, " +
                            "last_update = ?)");
            for (OVChipkaart ovChipkaart : product.ovChipList) {
                statement2.setInt(1, ovChipkaart.getKaartNummer());
                statement2.setInt(2, product.getProductNummer());
                statement2.setString(3, "gekocht");
                statement2.setDate(4, Date.valueOf(LocalDate.now()));
            }

            statement2.executeQuery();

            return true;
        } catch (SQLException e) {
            System.out.println("Deleting product didn't work: " + e.getMessage());
            return false;
        }
    }

    public List<Product> findByOVChipkaart(OVChipkaart ovChipkaart) {
        try {
            List<Product> productList = new ArrayList<>();

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT product.product_nummer, product.naam, product.beschrijving, product.prijs " +
                            "FROM product " +
                            "INNER JOIN ov_chipkaart_product ON product.product_nummer = ov_chipkaart_product.product_nummer " +
                            "WHERE ov_chipkaart_product.kaart_nummer = ?");
            statement.setInt(1, ovChipkaart.getKaartNummer());
            ResultSet rs = statement.executeQuery();

            int productNummer;
            String naam;
            String beschrijving;
            int prijs;

            while (rs.next()) {
                productNummer = rs.getInt("product_nummer");
                naam = rs.getString("naam");
                beschrijving = rs.getString("beschrijving");
                prijs = rs.getInt("prijs");

                Product product = new Product(productNummer, naam, beschrijving, prijs);
                productList.add(product);
            }
            return productList;

        } catch (SQLException e) {
            System.out.println("findByOVChipkaart didn't work: " + e.getMessage());
            return null;
        }
    }

    public List<Product> findAll() {
        try {
            List<Product> productList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM product");
            ResultSet rs = statement.executeQuery();

            int productNummer;
            String naam;
            String beschrijving;
            int prijs;

            while (rs.next()) {
                productNummer = rs.getInt("product_nummer");
                naam = rs.getString("naam");
                beschrijving = rs.getString("beschrijving");
                prijs = rs.getInt("prijs");

                Product product = new Product(productNummer, naam, beschrijving, prijs);
                productList.add(product);
            }
            return productList;

        } catch (SQLException e) {
            System.out.println("findAll didn't work: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
