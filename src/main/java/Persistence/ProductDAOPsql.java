package Persistence;

import Model.Product;

import java.sql.*;
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

            System.out.println("Save complete");

            return true;
        } catch (SQLException e) {
            System.out.println("Saving didn't work: " + e.getMessage());
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
            System.out.println("Updating didn't work: " + e.getMessage());
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

            return true;
        } catch (SQLException e) {
            System.out.println("Deleting didn't work: " + e.getMessage());
            return false;
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
