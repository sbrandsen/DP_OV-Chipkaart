package DP_P3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {
    Connection conn;
    AdresDAO adao;

    ReizigerDAOPsql(Connection connection){
        conn = connection;
        adao = new AdresDAOPsql(connection, true);
    }

    ReizigerDAOPsql(Connection connection, boolean skipConnection){
        conn = connection;
        if(!skipConnection){
            adao = new AdresDAOPsql(connection);
        }

    }
    @Override
    public boolean save(Reiziger reiziger) throws SQLException {
        try{
            PreparedStatement prepStatement = conn.prepareStatement("""
                                                                        INSERT INTO public.reiziger
                                                                        (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum)
                                                                        VALUES(?, ?, ?, ?, ?);
                                                                        """);
            prepStatement.setInt(1, reiziger.getId());
            prepStatement.setString(2, reiziger.getVoorletters());
            prepStatement.setString(3, reiziger.getTussenvoegsel());
            prepStatement.setString(4, reiziger.getAchternaam());
            prepStatement.setDate(5, reiziger.getGeboortedatum());

            prepStatement.execute();
            adao.save(reiziger.getAdres());

            return true;

        } catch(Exception ex) {
            System.out.println("Error - could not save reiziger\n" +reiziger.toString());
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) throws SQLException {
        try{
            PreparedStatement prepStatement = conn.prepareStatement("""
                                                                        UPDATE public.reiziger
                                                                        SET voorletters=?, tussenvoegsel=?, achternaam=?, geboortedatum=?
                                                                        WHERE reiziger_id=?;
                                                                        """);

            prepStatement.setString(1, reiziger.getVoorletters());
            prepStatement.setString(2, reiziger.getTussenvoegsel());
            prepStatement.setString(3, reiziger.getAchternaam());
            prepStatement.setDate(4, reiziger.getGeboortedatum());
            prepStatement.setInt(5, reiziger.getId());

            adao.update(reiziger.getAdres());

            return prepStatement.execute();

        } catch(Exception ex) {
            System.out.println("Error - could not update reiziger\n" +reiziger.toString());
            return false;
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) throws SQLException {
        try{
            PreparedStatement prepStatement = conn.prepareStatement("""
                                                                        DELETE FROM public.reiziger
                                                                        WHERE reiziger_id=?;
                                                                        """);

            prepStatement.setInt(1, reiziger.getId());
            adao.delete(reiziger.getAdres());

            return prepStatement.execute();

        } catch(Exception ex) {
            System.out.println("Error - could not delete reiziger\n" +reiziger.toString());
            return false;
        }
    }

    @Override
    public Reiziger findById(int id) throws SQLException {
        try{
            PreparedStatement prepStatement = conn.prepareStatement("""
                                                                        SELECT reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum
                                                                        FROM public.reiziger
                                                                        WHERE reiziger_id = ?;
                                                                        """);
            prepStatement.setInt(1, id);
            ResultSet rs = prepStatement.executeQuery();

            Reiziger reiziger = null;

            while (rs.next() ) {
                reiziger = new Reiziger(id, rs.getString("voorletters"), rs.getString("tussenvoegsel"),
                                        rs.getString("achternaam"), rs.getDate("geboortedatum"), null);
                reiziger.setAdres(adao.findbyReiziger(reiziger));
            }

            return reiziger;
        } catch(Exception ex) {
            System.out.println("Error - could not find reiziger by id: " + id);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reiziger> findByGbDatum(String Datum) {
        try{
            PreparedStatement prepStatement = conn. prepareStatement("""
                                                                        SELECT reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum
                                                                        FROM public.reiziger
                                                                        WHERE geboortedatum = ?;
                                                                        """);
            prepStatement.setDate(1, Date.valueOf(Datum));
            ResultSet rs = prepStatement.executeQuery();

            Reiziger reiziger = null;
            List<Reiziger> reizigerList = new ArrayList<Reiziger>();

            while (rs.next() ) {
                reiziger = new Reiziger(rs.getInt("reiziger_id"), rs.getString("voorletters"), rs.getString("tussenvoegsel"),
                        rs.getString("achternaam"), rs.getDate("geboortedatum"), null);
                reiziger.setAdres(adao.findbyReiziger(reiziger));
                reizigerList.add(reiziger);
            }

            return reizigerList;
        } catch(Exception ex) {
            System.out.println("Error - could not find reiziger by date: " + Datum);
            return null;
        }
    }

    @Override
    public List<Reiziger> findAll() {
        try{
            PreparedStatement prepStatement = conn.prepareStatement("""
                                                                        SELECT *
                                                                        FROM public.reiziger;
                                                                        """);

            ResultSet rs = prepStatement.executeQuery();

            Reiziger reiziger = null;
            List<Reiziger> reizigerList = new ArrayList<Reiziger>();

            while (rs.next() ) {
                reiziger = new Reiziger(rs.getInt("reiziger_id"), rs.getString("voorletters"), rs.getString("tussenvoegsel"),
                        rs.getString("achternaam"), rs.getDate("geboortedatum"), null);
                reiziger.setAdres(adao.findbyReiziger(reiziger));
                reizigerList.add(reiziger);
            }



            return reizigerList;
        } catch(Exception ex) {
            System.out.println("Error - could not find all reizigers");
            ex.printStackTrace();
            return null;
        }
    }
}
