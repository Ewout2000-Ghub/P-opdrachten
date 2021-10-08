package P5;

import Model.OVChipkaart;
import Model.Product;
import Persistence.ProductDAO;
import Persistence.ProductDAOPsql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static Connection conn;

    private static Connection getConnection() throws SQLException {
        if(conn == null) {
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5435/OV-Chipkaart",
                    "postgres",
                    "admin");
        }
        return conn;
    }

    private static Connection closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        ProductDAOPsql productDAO = new ProductDAOPsql(getConnection());

        try {
            testProductDAO(productDAO);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testProductDAO(ProductDAO pdao) throws SQLException {
        System.out.println("\n---------- Test ProductDAO -------------");
        Product product = new Product(8, "Business card", "Eerste klas reizen op kosten van het bedrijf", 0.00);
        OVChipkaart ovChipkaart = new OVChipkaart(35283, Date.valueOf("2018-05-31"), 2, 25.50, 2);

        // Test save()
        System.out.println("[Test] ProductDAO.save() geeft het volgende:");
        System.out.println(pdao.save(product));

        // Test update()
        System.out.println("[Test] ProductDAO.update() geeft het volgende:");
        System.out.println(pdao.update(product));

        // Test delete()
        System.out.println("[Test] ProductDAO.delete() geeft het volgende:");
        System.out.println(pdao.delete(product));

        // Test findByOVChipkaart()
        System.out.println("[Test] ProductDAO.findByOVChipkaart() geeft het volgende:");
        System.out.println(pdao.findByOVChipkaart(ovChipkaart));
    }
}
