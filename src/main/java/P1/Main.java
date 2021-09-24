package ChipkaartApplication;


import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5435/OV-Chipkaart", "postgres", "admin");

            Statement st =  conn.createStatement();
            ResultSet rslt = st.executeQuery("SELECT * FROM reiziger");

            System.out.println("Alle reizigers:");

            while (rslt.next()) {
                String tussenvoegsel = rslt.getString("tussenvoegsel");
                if (tussenvoegsel == null) {
                    tussenvoegsel = "";
                }
                System.out.println("#" + rslt.getString("reiziger_id") + ": " + rslt.getString("voorletters") + ". " + tussenvoegsel + " " + rslt.getString("achternaam") + " (" + rslt.getString("geboortedatum") + ")");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
