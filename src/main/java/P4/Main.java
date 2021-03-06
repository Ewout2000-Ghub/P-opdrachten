package P4;

import Persistence.OVChipkaartDAO;
import Persistence.OVChipkaartDAOPsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    private static Connection closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        OVChipkaartDAOPsql ovChipDao = new OVChipkaartDAOPsql(getConnection());

    }
}
