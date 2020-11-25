package turntabl.io.excon_1.module;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientOrder {
    private String id ;
    private String product;
    private double price;
    private int quantity;
    private String side;

    public ClientOrder() {
    }

    public ClientOrder(String id, String product, double price, int quantity, String side) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.side = side;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    @Override
    public String toString() {
        return "ClientOrder{" +
                "id='" + id + '\'' +
                ", product='" + product + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", side='" + side + '\'' +
                '}';
    }
}
