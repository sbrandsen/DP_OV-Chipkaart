package DP_P2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {
    Connection conn;

    ReizigerDAOPsql(Connection connection){
        conn = connection;
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

            prepStatement.closeOnCompletion();

            boolean result = prepStatement.execute();
            prepStatement.close();

            return result;

        } catch (SQLException ex){
            System.out.println("SQLException - could not save reiziger");
            return false;
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

            boolean result = prepStatement.execute();
            prepStatement.close();

            return result;

        } catch (SQLException ex){
            System.out.println("SQLException - could not update reiziger\n" +reiziger.toString());
            return false;
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

            boolean result = prepStatement.execute();
            prepStatement.close();

            return result;

        } catch (SQLException ex) {
            System.out.println("SQLException - could not find delete reiziger\n" + reiziger.toString());
            return false;
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
                                        rs.getString("achternaam"), rs.getDate("geboortedatum"));
            }

            prepStatement.close();
            rs.close();

            return reiziger;
        } catch (SQLException ex){
            System.out.println("SQLException - could not find all reiziger by id " + id);
            return null;
        } catch(Exception ex) {
            System.out.println("Error - could not find reiziger by id: " + id);
            return null;
        }
    }

    @Override
    public List<Reiziger> findByGbDatum(Date Datum) {
        try{
            PreparedStatement prepStatement = conn. prepareStatement("""
                                                                        SELECT reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum
                                                                        FROM public.reiziger
                                                                        WHERE geboortedatum = ?;
                                                                        """);
            prepStatement.setDate(1, Datum);
            ResultSet rs = prepStatement.executeQuery();

            Reiziger reiziger = null;
            List<Reiziger> reizigerList = new ArrayList<Reiziger>();

            while (rs.next() ) {
                reiziger = new Reiziger(rs.getInt("reiziger_id"), rs.getString("voorletters"), rs.getString("tussenvoegsel"),
                        rs.getString("achternaam"), rs.getDate("geboortedatum"));
                reizigerList.add(reiziger);
            }

            prepStatement.close();
            rs.close();

            return reizigerList;
        } catch (SQLException ex){
            System.out.println("SQLException - could not find all reizigers " + Datum);
            return null;
        } catch(Exception ex) {
            System.out.println("Error - could not find reiziger by date: " + Datum);
            return null;
        }
    }

    @Override
    public List<Reiziger> findAll() {
        try {
            PreparedStatement prepStatement = conn.prepareStatement("""
                    SELECT *
                    FROM public.reiziger;
                    """);

            ResultSet rs = prepStatement.executeQuery();



            Reiziger reiziger = null;
            List<Reiziger> reizigerList = new ArrayList<Reiziger>();

            while (rs.next()) {
                reiziger = new Reiziger(rs.getInt("reiziger_id"), rs.getString("voorletters"), rs.getString("tussenvoegsel"),
                        rs.getString("achternaam"), rs.getDate("geboortedatum"));
                reizigerList.add(reiziger);
            }

            prepStatement.close();
            rs.close();

            return reizigerList;

        } catch (SQLException ex){
            System.out.println("SQLException - could not find all reizigers");
            return null;
        } catch(Exception ex) {
            System.out.println("Error - could not find all reizigers");
            return null;
        }
    }
}
