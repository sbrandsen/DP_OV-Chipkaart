package DP_P3;

import java.sql.SQLException;

public interface OV_Chipkaart_ProductDAO {
    boolean save(OV_Chipkaart_Product kaartproduct) throws SQLException;
    boolean update(OV_Chipkaart_Product kaartproduct) throws SQLException;
    boolean updateProduct(Product product, OVChipkaart chipkaart) throws SQLException;
    boolean updateChipkaart(OVChipkaart chipkaart, Product product) throws SQLException;

    boolean delete(OV_Chipkaart_Product kaartproduct) throws SQLException;
    boolean deleteProduct(Product product) throws SQLException;
    boolean deleteChipkaart(OVChipkaart chipkaart) throws SQLException;

}
