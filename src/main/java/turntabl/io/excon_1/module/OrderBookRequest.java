package turntabl.io.excon_1.module;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderBookRequest {
    private String id;
    private String product;
    private String side;

    public OrderBookRequest() {
    }

    public OrderBookRequest(String id, String product, String side) {
        this.id = id;
        this.product = product;
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

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    @Override
    public String toString() {
        return "OrderBookRequest{" +
                "id='" + id + '\'' +
                ", product='" + product + '\'' +
                ", side='" + side + '\'' +
                '}';
    }
}
