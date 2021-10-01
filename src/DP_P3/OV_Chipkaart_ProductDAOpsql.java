package DP_P3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OV_Chipkaart_ProductDAOpsql implements OV_Chipkaart_ProductDAO{
    Connection conn;
    public OV_Chipkaart_ProductDAOpsql(Connection connection) {
        conn = connection;
    }

    @Override
    public boolean save(OV_Chipkaart_Product kaartproduct) throws SQLException {
        try{
            PreparedStatement prepStatement = conn.prepareStatement("""
                                                                        INSERT INTO public.ov_chipkaart_product
                                                                        (kaart_nummer, product_nummer)
                                                                        VALUES(?, ?);
                                                                        """);

            prepStatement.setInt(1, kaartproduct.getKaart_nummer());
            prepStatement.setInt(2, kaartproduct.getProduct_nummer());


            boolean complete = prepStatement.execute();
            prepStatement.close();

            return complete;

        } catch(SQLException ex) {
            System.out.println("SQL Error - could not save kaartproduct\n" +kaartproduct.toString());
            ex.printStackTrace();
            return false;

        } catch(Exception ex) {
            System.out.println("Error - could not save kaartproduct\n" +kaartproduct.toString());
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(OV_Chipkaart_Product kaartproduct) throws SQLException {
        return false;
    }

    @Override
    public boolean updateProduct(Product product, OVChipkaart chipkaart) throws SQLException {
        try{
            PreparedStatement prepStatement = conn.prepareStatement("""
                                                                        UPDATE public.ov_chipkaart_product
                                                                        SET product_nummer = ?
                                                                        WHERE kaart_nummer = ?;
                                                                        """);

            prepStatement.setInt(1, product.getProduct_nummer());
            prepStatement.setInt(2, chipkaart.getKaart_nummer());

            boolean complete = prepStatement.execute();
            prepStatement.close();

            return complete;

        } catch(SQLException ex) {
            System.out.println("SQL Error");
            ex.printStackTrace();
            return false;

        } catch(Exception ex) {
            System.out.println("Error");
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateChipkaart(OVChipkaart chipkaart, Product product) throws SQLException {
        try{
            PreparedStatement prepStatement = conn.prepareStatement("""
                                                                        UPDATE public.ov_chipkaart_product
                                                                        SET kaart_nummer = ?
                                                                        WHERE product_nummer= ?;
                                                                        """);

            prepStatement.setInt(1, chipkaart.getKaart_nummer());
            prepStatement.setInt(2, product.getProduct_nummer());


            boolean complete = prepStatement.execute();
            prepStatement.close();

            return complete;

        } catch(SQLException ex) {
            System.out.println("SQL Error");
            ex.printStackTrace();
            return false;

        } catch(Exception ex) {
            System.out.println("Error");
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(OV_Chipkaart_Product kaartproduct) throws SQLException {
        try{
            PreparedStatement prepStatement = conn.prepareStatement("""
                                                                        DELETE FROM public.ov_chipkaart_product
                                                                        WHERE kaart_nummer=0 AND product_nummer=0;
                                                                        """);

            prepStatement.setInt(1, kaartproduct.getKaart_nummer());
            prepStatement.setInt(2, kaartproduct.getProduct_nummer());


            boolean complete = prepStatement.execute();
            prepStatement.close();

            return complete;

        } catch(SQLException ex) {
            System.out.println("SQL Error - could not delete kaartproduct\n" +kaartproduct.toString());
            ex.printStackTrace();
            return false;

        } catch(Exception ex) {
            System.out.println("Error - could not delete kaartproduct\n" +kaartproduct.toString());
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteProduct(Product product) throws SQLException {
        try{
            PreparedStatement prepStatement = conn.prepareStatement("""
                                                                        DELETE FROM public.ov_chipkaart_product
                                                                        WHERE product_nummer=?;
                                                                        """);

            prepStatement.setInt(1, product.getProduct_nummer());


            boolean complete = prepStatement.execute();
            prepStatement.close();

            return complete;

        } catch(SQLException ex) {
            System.out.println("SQL Error");
            ex.printStackTrace();
            return false;

        } catch(Exception ex) {
            System.out.println("Error");
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteChipkaart(OVChipkaart chipkaart) throws SQLException {
        try{
            PreparedStatement prepStatement = conn.prepareStatement("""
                                                                        DELETE FROM public.ov_chipkaart_product
                                                                        WHERE kaart_nummer=?;
                                                                        """);

            prepStatement.setInt(1, chipkaart.getKaart_nummer());

            boolean complete = prepStatement.execute();
            prepStatement.close();

            return complete;

        } catch(SQLException ex) {
            System.out.println("SQL Error");
            ex.printStackTrace();
            return false;

        } catch(Exception ex) {
            System.out.println("Error");
            ex.printStackTrace();
            return false;
        }
    }
}
