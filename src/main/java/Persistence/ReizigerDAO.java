package Persistence;

import Model.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface ReizigerDAO {

    public boolean save(Reiziger reiziger) throws SQLException;
    public boolean update(Reiziger reiziger);
    public boolean delete(Reiziger reiziger);
    public Reiziger findById(int id);
    public List<Reiziger> findByGbdatum(String datum);
    public List<Reiziger> findAll();
}
