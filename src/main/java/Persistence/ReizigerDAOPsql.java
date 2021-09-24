package Persistence;

import Model.Adres;
import Model.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO{

    private Connection connection;
    private AdresDAO adresDAO;

    public ReizigerDAOPsql(Connection conn) throws SQLException {
        this.connection = conn;
    }

    public ReizigerDAOPsql(Connection conn, AdresDAO adrD) {
        this.connection = conn;
        this.adresDAO = adrD;
    }

    public boolean save(Reiziger r) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) " +
                            "VALUES (?, ?, ?, ?, ?)");
            statement.setInt(1, r.getId());
            statement.setString(2, r.getVoorletters());
            statement.setString(3, r.getTussenvoegsel());
            statement.setString(4, r.getAchternaam());
            statement.setDate(5, r.getGeboortedatum());

            statement.executeQuery();

            if (r.getAdres() != null) {
                adresDAO.save(r.getAdres());
            }

            System.out.println("Save complete");

            return true;
        } catch (SQLException e) {
            System.out.println("Saving didn't work: " + e.getMessage());
            return false;
        }
    }

    public boolean update(Reiziger r) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE reiziger SET " +
                            "reiziger_id = ?, " +
                            "voorletters = ?, " +
                            "tussenvoegsel = ?, " +
                            "achternaam = ?, " +
                            "geboortedatum = ?");
            statement.setInt(1, r.getId());
            statement.setString(2, r.getVoorletters());
            statement.setString(3, r.getTussenvoegsel());
            statement.setString(4, r.getAchternaam());
            statement.setDate(5, r.getGeboortedatum());

            statement.executeQuery();

            System.out.println("Update complete");
            return true;
        } catch (SQLException e) {
            System.out.println("Updating didn't work: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(Reiziger r) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM reiziger WHERE (" +
                            "reiziger_id = ?, " +
                            "voorletters = ?, " +
                            "tussenvoegsel = ?, " +
                            "achternaam = ?, " +
                            "geboortedatum = ?)");
            statement.setInt(1, r.getId());
            statement.setString(2, r.getVoorletters());
            statement.setString(3, r.getTussenvoegsel());
            statement.setString(4, r.getAchternaam());
            statement.setDate(5, r.getGeboortedatum());

            statement.executeQuery();

            System.out.println("Delete complete");
            return true;
        } catch (SQLException e) {
            System.out.println("Deleting didn't work: " + e.getMessage());
            return false;
        }
    }
    public Reiziger findById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM reiziger WHERE reiziger_id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            Reiziger reiziger = new Reiziger(
                    rs.getInt("reiziger_id"),
                    rs.getString("voorletters"),
                    rs.getString("tussenvoegsel"),
                    rs.getString("achternaam"),
                    rs.getDate("geboortedatum"));

            return reiziger;

        } catch (SQLException e) {
            System.out.println("findById didn't work: " + e.getMessage());
            return null;
        }
    }

    public List<Reiziger> findByGbdatum(String datum) {
        try {
            List<Reiziger> reizigerList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM reiziger WHERE geboortedatum = ?");
            statement.setString(1, datum);

            ResultSet rs = statement.executeQuery();

//            Reiziger reiziger = new Reiziger(rs.getInt("reiziger_id"), rs.getString("voorletters"), rs.getString("tussenvoegsel"), rs.getString("achternaam"), rs.getDate("geboortedatum"));

            int id;
            String voorletters;
            String tussenvoegsel;
            String achternaam;
            Date gbdatum;

            while (rs.next()) {
                id = rs.getInt("reiziger_id");
                voorletters = rs.getString("voorletters");
                tussenvoegsel = rs.getString("tussenvoegsel");
                achternaam = rs.getString("achternaam");
                gbdatum = rs.getDate("geboortedatum");

                Reiziger reiziger = new Reiziger(id, voorletters, tussenvoegsel, achternaam, gbdatum);
                reizigerList.add(reiziger);
            }
            return reizigerList;

        } catch (SQLException e) {
            System.out.println("findByGbdatum didn't work");
            return null;
        }
    }

    public List<Reiziger> findAll() {
        try {
            List<Reiziger> reizigerList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reiziger");

            ResultSet rs = statement.executeQuery();

            int id;
            String voorletters;
            String tussenvoegsel;
            String achternaam;
            Date gbdatum;

            while (rs.next()) {
                id = rs.getInt("reiziger_id");
                voorletters = rs.getString("voorletters");
                tussenvoegsel = rs.getString("tussenvoegsel");
                achternaam = rs.getString("achternaam");
                gbdatum = rs.getDate("geboortedatum");

                Reiziger reiziger = new Reiziger(id, voorletters, tussenvoegsel, achternaam, gbdatum);
                Adres adres = adresDAO.findByReiziger(reiziger);
                reiziger.setAdres(adres);
                reizigerList.add(reiziger);
            }
            return reizigerList;

        } catch (SQLException e) {
            System.out.println("findAll didn't work: " + e.getMessage());
            return null;
        }
    }
}
