package P5;

import Model.OVChipkaart;
import Model.Product;
import Model.Reiziger;
import Persistence.*;

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
        try {
            Connection conn = getConnection();
            ProductDAOPsql productDAO = new ProductDAOPsql(conn);
            ReizigerDAOPsql reizigerDAO = new ReizigerDAOPsql(conn);
            OVChipkaartDAOPsql ovChipdao = new OVChipkaartDAOPsql(conn);
            ovChipdao.setPdao(productDAO);
            testProductDAO(productDAO, reizigerDAO, ovChipdao);
            closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testProductDAO(ProductDAO pdao, ReizigerDAO rdao, OVChipkaartDAO ovChipDAO) throws SQLException {
        System.out.println("\n---------- Test ProductDAO -------------");
        Reiziger reiziger = new Reiziger(5829, "T", "van", "Baak", java.sql.Date.valueOf("1998-07-11"));
        Product product = new Product(9, "test card", "testbeschrijving", 0.00);
        OVChipkaart ovChipkaart = new OVChipkaart(54321, Date.valueOf("2012-05-31"), 2, 25.50, 2);

        // Test save()
        System.out.println("[Test] ProductDAO.save() geeft het volgende:");
        System.out.println(rdao.save(reiziger));

        System.out.println("[Test] OVChipkaartDAO.save() geeft het volgende:");
        System.out.println(ovChipDAO.save(ovChipkaart));

        product.getOvChipList().add(ovChipkaart);

        System.out.println("[Test] ProductDAO.save() geeft het volgende:");
        System.out.println(pdao.save(product));

        System.out.println(pdao.findAll());

        // Test update()
        System.out.println("voor update");
        System.out.println(pdao.findByOVChipkaart(ovChipkaart));

        product.setBeschrijving("update");
        System.out.println(pdao.update(product));

        System.out.println("na update");
        System.out.println(pdao.findByOVChipkaart(ovChipkaart));

        // Test delete()
        System.out.println("[Test] ProductDAO.delete() geeft het volgende:");
        System.out.println(pdao.delete(product));

        System.out.println(pdao.findByOVChipkaart(ovChipkaart));
        System.out.println(pdao.findAll());

    }
}
