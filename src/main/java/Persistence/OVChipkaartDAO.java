package Persistence;

import Model.OVChipkaart;
import Model.Reiziger;

import java.util.List;

public interface OVChipkaartDAO {

    public List<OVChipkaart> findByReiziger(Reiziger reiziger);
}