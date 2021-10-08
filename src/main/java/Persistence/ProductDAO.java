package Persistence;

import Model.OVChipkaart;
import Model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {

    public boolean save(Product product) throws SQLException;
    public boolean update(Product product);
    public boolean delete(Product product);
    public List<Product> findByOVChipkaart(OVChipkaart ovChipkaart);
    public List<Product> findAll();
}
