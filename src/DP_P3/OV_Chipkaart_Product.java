package DP_P3;

public class OV_Chipkaart_Product {
    private int kaart_nummer;
    private int product_nummer;

    public OV_Chipkaart_Product(Product product, OVChipkaart ovChipkaart){
        kaart_nummer = ovChipkaart.getKaart_nummer();
        product_nummer = product.getProduct_nummer();
    }

    public int getKaart_nummer() {
        return kaart_nummer;
    }

    public void setKaart_nummer(int kaart_nummer) {
        this.kaart_nummer = kaart_nummer;
    }

    public int getProduct_nummer() {
        return product_nummer;
    }

    public void setProduct_nummer(int product_nummer) {
        this.product_nummer = product_nummer;
    }

    @Override
    public String toString() {
        return "ov_chipkaart_product{" +
                "kaart_nummer=" + kaart_nummer +
                ", product_nummer=" + product_nummer +
                '}';
    }
}
