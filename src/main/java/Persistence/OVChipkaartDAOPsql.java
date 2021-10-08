package Persistence;

import Model.OVChipkaart;
import Model.Reiziger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO {

    Connection connection;

    public OVChipkaartDAOPsql(Connection conn) {
        this.connection = conn;
    }

    public boolean save(OVChipkaart ovChipkaart) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO ov_chipkaart (kaart_nummer, geldig_tot, klasse, saldo, reiziger_id) " +
                            "VALUES (?, ?, ?, ?, ?)");
            statement.setInt(1, ovChipkaart.getKaartNummer());
            statement.setDate(2, ovChipkaart.getGeldigTot());
            statement.setInt(3, ovChipkaart.getKlasse());
            statement.setInt(4, ovChipkaart.getKlasse());
            statement.setInt(5, ovChipkaart.getReizigerId());

            statement.executeQuery();

            System.out.println("Save complete");

            return true;
        } catch (SQLException e) {
            System.out.println("Saving didn't work: " + e.getMessage());
            return false;
        }
    }

    public boolean update(OVChipkaart ovChipkaart) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE ov_chipkaart SET " +
                            "kaart_nummer = ?, " +
                            "geldig_tot = ?, " +
                            "klasse = ?, " +
                            "saldo = ?, " +
                            "reiziger_id = ?");
            statement.setInt(1, ovChipkaart.getKaartNummer());
            statement.setDate(2, ovChipkaart.getGeldigTot());
            statement.setInt(3, ovChipkaart.getKlasse());
            statement.setInt(4, ovChipkaart.getKlasse());
            statement.setInt(5, ovChipkaart.getReizigerId());

            statement.executeQuery();

            System.out.println("Update complete");
            return true;
        } catch (SQLException e) {
            System.out.println("Updating didn't work: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(OVChipkaart ovChipkaart) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM ov_chipkaart WHERE (" +
                            "kaart_nummer = ?, " +
                            "geldig_tot = ?, " +
                            "klasse = ?, " +
                            "saldo = ?, " +
                            "reiziger_id = ?)");
            statement.setInt(1, ovChipkaart.getKaartNummer());
            statement.setDate(2, ovChipkaart.getGeldigTot());
            statement.setInt(3, ovChipkaart.getKlasse());
            statement.setInt(4, ovChipkaart.getKlasse());
            statement.setInt(5, ovChipkaart.getReizigerId());

            statement.executeQuery();

            return true;
        } catch (SQLException e) {
            System.out.println("Deleting didn't work: " + e.getMessage());
            return false;
        }
    }

    public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
        try {
            List<OVChipkaart> ovChipList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM ov_chipkaart WHERE reiziger_id = ?");
            statement.setInt(1, reiziger.getId());
            ResultSet rs = statement.executeQuery();

            int kaartnummer;
            Date geldig_tot;
            int klasse;
            int saldo;
            int reizigerId;

            while (rs.next()) {
                kaartnummer = rs.getInt("kaart_nummer");
                geldig_tot = rs.getDate("geldig_tot");
                klasse = rs.getInt("klasse");
                saldo = rs.getInt("saldo");
                reizigerId = rs.getInt("reiziger_id");

                OVChipkaart ovChipkaart = new OVChipkaart(kaartnummer, geldig_tot, klasse, saldo, reizigerId);
                ovChipList.add(ovChipkaart);
            }

            return ovChipList;

        } catch (SQLException e) {
            System.out.println("findByReiziger didn't work: " + e.getMessage());
            return null;
        }
    }

    public List<OVChipkaart> findAll() {
        try {
            List<OVChipkaart> ovChipList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ov_chipkaart");

            ResultSet rs = statement.executeQuery();

            int kaartNummer;
            Date geldig_tot;
            int klasse;
            int saldo;
            int reizigerId;

            while (rs.next()) {
                kaartNummer = rs.getInt("kaart_nummer");
                geldig_tot = rs.getDate("geldig_tot");
                klasse = rs.getInt("klasse");
                saldo = rs.getInt("saldo");
                reizigerId = rs.getInt("reiziger_id");

                OVChipkaart ovChipkaart = new OVChipkaart(kaartNummer, geldig_tot, klasse, saldo, reizigerId);
                ovChipList.add(ovChipkaart);
            }
            return ovChipList;

        } catch (SQLException e) {
            System.out.println("findAll didn't work: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
