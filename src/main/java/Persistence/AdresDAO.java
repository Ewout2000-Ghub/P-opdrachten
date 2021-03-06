package Persistence;

import Model.Adres;
import Model.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface AdresDAO {

    public boolean save(Adres adres) throws SQLException;
    public boolean update(Adres adres);
    public boolean delete(Adres adres);
    public Adres findByReiziger(Reiziger reiziger);
    public List<Adres> findAll();
}
