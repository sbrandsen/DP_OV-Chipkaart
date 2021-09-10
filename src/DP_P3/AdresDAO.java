package DP_P3;

import java.sql.SQLException;
import java.util.List;

public interface AdresDAO {
    boolean save(Adres adres) throws SQLException;
    boolean update(Adres adres) throws SQLException;
    boolean delete(Adres adres) throws SQLException;

    Adres findbyId(int id);
    Adres findbyReiziger(Reiziger reiziger);

    List<Adres> findAll();
}
