package P3;

import Model.Adres;
import Model.Reiziger;
import Persistence.AdresDAO;
import Persistence.AdresDAOPsql;
import Persistence.ReizigerDAO;
import Persistence.ReizigerDAOPsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {

    private static Connection conn = null;

    private static Connection getConnection() throws SQLException {
        if(conn == null) {
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5435/OV-Chipkaart",
                    "postgres",
                    "admin");
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        AdresDAOPsql adresDao = new AdresDAOPsql(conn);

        try {
            testAdresDAO(adresDao);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testAdresDAO(AdresDAO adao) throws SQLException {
        System.out.println("\n---------- Test AdresDAO -------------");
        Reiziger reiziger = new Reiziger(6, "H", null, "JANSEN", java.sql.Date.valueOf("1990-01-12"));
        Adres adres = new Adres(6, "3824BR", "21", "Bosappelgaarde", "Amersfoort", 6);

        // Haal alle reizigers op uit de database
        List<Adres> adressen = adao.findAll();
        System.out.println("[Test] AdresDAO.findAll() geeft de volgende adressen:");
        for (Adres a : adressen) {
            System.out.println(a);
        }
        System.out.println();

        // Test findByReiziger()
        System.out.println("[Test] AdresDAO.findByReiziger() geeft het volgende adres:");

        System.out.println(adao.findByReiziger(reiziger));

        // Test save()
        System.out.println("[Test] AdresDAO.save() geeft het volgende:");
        System.out.println(adao.save(adres));
    }
}
