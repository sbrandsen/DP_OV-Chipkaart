package DP_P2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    private static Connection connection;

    public static void main(String[] args) throws SQLException {
    getConnection();
    ReizigerDAO rdao = new ReizigerDAOPsql(connection);
    testReizigerDAO(rdao);
    closeConnection();
    }

    private static void getConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ovchip",
                    "stephanbrandsen", "long80");


        } catch (Exception ex) {
            System.out.println("Could not connect to Database");
            ex.printStackTrace();
        }

    }

    private static void closeConnection(){
        try {
            connection.close();
        } catch (Exception ex){
            System.out.println("Could not close connection");
        }
    }

    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        //Delete nieuwe reiziger
        System.out.println("[Test] Delete nieuwe reiziger");
        rdao.delete(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Vind reiziger van bepaalde ID
        Reiziger GvanRijn = rdao.findById(1);
        System.out.println("[Test] " + GvanRijn + "\nMoet G. van Rijn zijn\n");

        // Vind reiziger(s) van bepaalde datum
        String testdatum = "2002-12-03";
        reizigers = rdao.findByGbDatum(testdatum);;
        System.out.println("[Test] ReizigerDAO.findByGbDatum() geeft de volgende reizigers bij datum " + testdatum + ":");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        //update bepaalde reiziger
        Reiziger BvanRijn = rdao.findById(2);
        System.out.println("[Test] B. van Rijn achternaam veranderen naar van Duuren");
        System.out.println(BvanRijn);
        BvanRijn.setAchternaam("Duuren");
        rdao.update(BvanRijn);

        System.out.println(rdao.findById(2));
        BvanRijn.setAchternaam("Rijn");
        rdao.update(BvanRijn); //reset

    }
}
