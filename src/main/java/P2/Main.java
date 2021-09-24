package P2;

import Model.Reiziger;
import Persistence.AdresDAO;
import Persistence.AdresDAOPsql;
import Persistence.ReizigerDAO;
import Persistence.ReizigerDAOPsql;

import java.sql.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5435/OV-Chipkaart", "postgres", "admin");
        AdresDAO adresDAO = new AdresDAOPsql(conn);
        ReizigerDAOPsql reizigerDao = new ReizigerDAOPsql(conn, adresDAO);

        Reiziger reiziger = new Reiziger(6, "H", null, "JANSEN", java.sql.Date.valueOf("1990-01-12"));

        try {
            testReizigerDAO(reizigerDao);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * P2. Reiziger DAO: persistentie van een klasse
     *
     * Deze methode test de CRUD-functionaliteit van de Reiziger DAO
     *
     * @throws SQLException
     */
    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.
    }
}
