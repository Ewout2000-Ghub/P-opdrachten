package Persistence;

import Model.Adres;
import Model.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql implements AdresDAO{

    Connection connection;
    ReizigerDAO rdao;
    Adres adres;

    public AdresDAOPsql(Connection conn) {
        this.connection = conn;
    }

    public boolean save(Adres adres) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO adres (adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id) " +
                            "VALUES (?, ?, ?, ?, ?, ?)");
            statement.setInt(1, adres.getId());
            statement.setString(2, adres.getPostcode());
            statement.setString(3, adres.getHuisnummer());
            statement.setString(4, adres.getStraat());
            statement.setString(5, adres.getWoonplaats());
            statement.setInt(6, adres.getReizigerId());

            statement.executeQuery();

            System.out.println("Save complete");

            return true;
        } catch (SQLException e) {
            System.out.println("Saving didn't work: " + e.getMessage());
            return false;
        }
    }

    public boolean update(Adres adres) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE adres SET " +
                            "adres_id = ?, " +
                            "postcode = ?, " +
                            "huisnummer = ?, " +
                            "straat = ?, " +
                            "woonplaats = ?, " +
                            "reiziger_id = ?");
            statement.setInt(1, adres.getId());
            statement.setString(2, adres.getPostcode());
            statement.setString(3, adres.getHuisnummer());
            statement.setString(4, adres.getStraat());
            statement.setString(5, adres.getWoonplaats());
            statement.setInt(6, adres.getReizigerId());

            statement.executeQuery();

            System.out.println("Update complete");
            return true;
        } catch (SQLException e) {
            System.out.println("Updating didn't work: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(Adres adres) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM reiziger WHERE (" +
                            "adres_id = ?, " +
                            "postcode = ?, " +
                            "huisnummer = ?, " +
                            "straat = ?, " +
                            "woonplaats = ?, " +
                            "reiziger_id = ?)");
            statement.setInt(1, adres.getId());
            statement.setString(2, adres.getPostcode());
            statement.setString(3, adres.getHuisnummer());
            statement.setString(4, adres.getStraat());
            statement.setString(5, adres.getWoonplaats());
            statement.setInt(6, adres.getReizigerId());

            statement.executeQuery();

            return true;
        } catch (SQLException e) {
            System.out.println("Deleting didn't work: " + e.getMessage());
            return false;
        }
    }

    public Adres findByReiziger(Reiziger reiziger) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM adres WHERE reiziger_id = ?");
            statement.setInt(1, reiziger.getId());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                adres = new Adres(
                        rs.getInt("adres_id"),
                        rs.getString("postcode"),
                        rs.getString("huisnummer"),
                        rs.getString("straat"),
                        rs.getString("woonplaats"),
                        rs.getInt("reiziger_id"));
            }
            return adres;

        } catch (SQLException e) {
            System.out.println("findByReiziger didn't work: " + e.getMessage());
            return null;
        }
    }

    public List<Adres> findAll() {
        try {
            List<Adres> adresList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM adres");

            ResultSet rs = statement.executeQuery();

            int id;
            String postcode;
            String huisnummer;
            String straat;
            String woonplaats;
            int reizigerId;

            while (rs.next()) {
                id = rs.getInt("adres_id");
                postcode = rs.getString("postcode");
                huisnummer = rs.getString("huisnummer");
                straat = rs.getString("straat");
                woonplaats = rs.getString("woonplaats");
                reizigerId = rs.getInt("reiziger_id");

                Adres adres = new Adres(id, postcode, huisnummer, straat, woonplaats, reizigerId);
                adresList.add(adres);
            }
            return adresList;

        } catch (SQLException e) {
            System.out.println("findAll didn't work: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
