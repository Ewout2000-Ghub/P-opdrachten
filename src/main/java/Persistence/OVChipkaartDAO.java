package Persistence;

import Model.OVChipkaart;
import Model.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface OVChipkaartDAO {

    public List<OVChipkaart> findByReiziger(Reiziger reiziger);
    public boolean save(OVChipkaart ovChipkaart) throws SQLException;
    public boolean update(OVChipkaart ovChipkaart);
    public boolean delete(OVChipkaart ovChipkaart);
    public List<OVChipkaart> findAll();
}