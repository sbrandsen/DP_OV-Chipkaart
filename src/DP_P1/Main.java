package DP_P1;

import java.sql.*;

public class Main {

    public static void main(String[] args){

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ovchip",
                                                                "stephanbrandsen", "long80")) {

            Statement statement = connection.createStatement();
            System.out.println("Alle reizigers:");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.reiziger");
            while (resultSet.next()) {
                System.out.printf("\t#%s %s. %s%s (%s)\n",
                        resultSet.getString("reiziger_id"),
                        resultSet.getString("voorletters"),
                        RemoveNull(resultSet.getString("tussenvoegsel")),
                        resultSet.getString("achternaam"),
                        resultSet.getString("geboortedatum"));
            }

        }  catch (SQLException e) {
            System.out.println("Failed to connect to ovchip database.");
        }
    }

    private static String RemoveNull(String str){
        if(str == null){
            return "";
        } else {
            return str + " ";
        }
    }

}

