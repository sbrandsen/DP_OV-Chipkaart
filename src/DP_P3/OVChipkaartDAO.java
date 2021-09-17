package DP_P3;

import java.sql.SQLException;
import java.util.List;

public interface OVChipkaartDAO {
    boolean save(OVChipkaart ovchipkaart) throws SQLException;
    boolean update(OVChipkaart ovchipkaart) throws SQLException;
    boolean delete(OVChipkaart ovchipkaart) throws SQLException;

    List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException;
    OVChipkaart findById(int id) throws SQLException;

    List<OVChipkaart> findAll();
}
