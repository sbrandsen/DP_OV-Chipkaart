package DP_P3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDAOpsql implements ProductDAO{
    Connection conn;

    public ProductDAOpsql(Connection connection) {
        conn = connection;
    }

    @Override
    public boolean save(Product product) throws SQLException {
        try{
            PreparedStatement prepStatement = conn.prepareStatement("""
                                                                        INSERT INTO public.product
                                                                        (product_nummer, naam, beschrijving, prijs)
                                                                        VALUES(?, ?, ?, ?);
                                                                        """);
            prepStatement.setInt(1, product.getProduct_nummer());
            prepStatement.setString(2, product.getNaam());
            prepStatement.setString(3, product.getBeschrijving());
            prepStatement.setDouble(4, product.getPrijs());

            boolean complete = prepStatement.execute();
            prepStatement.close();

            return complete;

        } catch(SQLException ex) {
            System.out.println("SQL Error - could not add product\n" +product.toString());
            ex.printStackTrace();
            return false;

        } catch(Exception ex) {
            System.out.println("Error - could not add product\n" +product.toString());
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Product product) throws SQLException {
        try{
            PreparedStatement prepStatement = conn.prepareStatement("""
                                                                        UPDATE public.product
                                                                        SET naam=?, beschrijving=?, prijs=?
                                                                        WHERE product_nummer=0;
                                                                        """);


            prepStatement.setString(1, product.getNaam());
            prepStatement.setString(2, product.getBeschrijving());
            prepStatement.setDouble(3, product.getPrijs());
            prepStatement.setInt(4, product.getProduct_nummer());

            boolean complete = prepStatement.execute();
            prepStatement.close();

            return complete;

        } catch(SQLException ex) {
            System.out.println("SQL Error - could not update product\n" +product.toString());
            ex.printStackTrace();
            return false;

        } catch(Exception ex) {
            System.out.println("Error - could not update product\n" +product.toString());
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Product product) throws SQLException {
        try{
            PreparedStatement prepStatement = conn.prepareStatement("""
                                                                        DELETE FROM public.product
                                                                        WHERE product_nummer=?;
                                                                        """);

            prepStatement.setInt(1, product.getProduct_nummer());

            boolean complete = prepStatement.execute();
            prepStatement.close();

            return complete;

        } catch(SQLException ex) {
            System.out.println("SQL Error - could not delete product\n" +product.toString());
            ex.printStackTrace();
            return false;

        } catch(Exception ex) {
            System.out.println("Error - could not delete product\n" +product.toString());
            ex.printStackTrace();
            return false;
        }
    }
}
